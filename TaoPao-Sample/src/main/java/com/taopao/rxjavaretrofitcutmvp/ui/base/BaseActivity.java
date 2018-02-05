package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.interfaces.INetEvent;
import com.taopao.rxjavaretrofitcutmvp.widget.CustomDialog;
import java.util.LinkedList;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　     ┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Author: 淘跑
 * @Data: 2018/1/29 21:36
 * @Use:  activity基类
 */

public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView> extends AppCompatActivity implements INetEvent {
    // 管理运行的所有的activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();
    private CompositeDisposable mCompositeDisposable;
    public P mPresenter;
    public Toolbar mToolbar;
    public static INetEvent mINetEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        //初始化网络状态的监听
        mINetEvent=this;
        //初始化toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        initStatusBar();
        initToolBar();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.detachView();
        }

        //取消订阅者
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }


    /**
     * 初始化presenter和view
     */
    private void init() {
        mPresenter = createPresenter();
        //判断是否非空 以防子类并没有使用此架构
        if(mPresenter!=null) {
            mPresenter.attachView(createView());
        }
        synchronized (mActivities) {
            mActivities.add(this);
        }


    }


    public void setToolBar(){}

    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 用于创建Presenter(由子类实现)
     * @return Presenter
     */
    public abstract P createPresenter();

    /**用于创建view(由子类实现)
     * @return view
     */
    public abstract V createView();


    /**
     * 添加activity里的订阅者 对订阅者统一管理
     * @param disposable
     */
    protected void addActivityDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }



    private CustomDialog mDialogWaiting;
    private MaterialDialog mMaterialDialog;
    /**
     * 显示等待提示框
     */
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(this, view, R.style.MyDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }
    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }
    /**
     * 显示MaterialDialog
     */
    public MaterialDialog showMaterialDialog(String title, String message, String positiveText, String negativeText, View.OnClickListener positiveButtonClickListener, View.OnClickListener negativeButtonClickListener) {
        hideMaterialDialog();
        mMaterialDialog = new MaterialDialog(this);
        if (!TextUtils.isEmpty(title)) {
            mMaterialDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mMaterialDialog.setMessage(message);
        }
        if (!TextUtils.isEmpty(positiveText)) {
            mMaterialDialog.setPositiveButton(positiveText, positiveButtonClickListener);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            mMaterialDialog.setNegativeButton(negativeText, negativeButtonClickListener);
        }
        mMaterialDialog.show();
        return mMaterialDialog;
    }

    /**
     * 隐藏MaterialDialog
     */
    public void hideMaterialDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    public void killAll() {
        // 复制了一份mActivities 集合Å
        List<AppCompatActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (AppCompatActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 全局检测网络广播的回调 处理网络变化
     * 注:在程序第一次启动的时候,没网并不会回调,需要自己在启动页面,或者主页自己再判断一次
     * @param netWorkState 网络状态    -1:没网络 0:移动网络 1:WiFi网络
     */
    public abstract void onNetChanged(int netWorkState);
    @Override
    public void onNetChange(int netWorkState) {
        onNetChanged(netWorkState);
    }

    //////////////////////////////沉浸式相关////////////////////////////////////
    /**
     * 透明状态栏沉浸式 要改的话 子类重写此方法
     */
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
//        StatusBarUtil.setTranslucent(this, 0);
    }
    /**
     * 设置ToolBar 要改的话 子类重写此方法
     */
    protected void initToolBar() {
        setSupportActionBar(mToolbar);
    }

    /**
     * 得到toolbar的实例
     * @return Toolbar的实例
     */
    public Toolbar getToolBar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        return mToolbar;
    }


}
