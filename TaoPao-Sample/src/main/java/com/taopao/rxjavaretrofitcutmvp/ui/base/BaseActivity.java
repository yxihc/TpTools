package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.interfaces.INetEvent;
import com.taopao.rxjavaretrofitcutmvp.widget.CustomDialog;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 21:36
 * @Use:  activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements INetEvent {
    // 管理运行的所有的activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();
    private CompositeDisposable mCompositeDisposable;
    private Toolbar mToolbar;
    public static INetEvent mINetEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            initParam(getIntent().getExtras());
        }
        initToolBar();
        setContentView();
        ButterKnife.bind(this);
        //初始化网络状态的监听
        mINetEvent=this;
        //初始化toolbar

        initStatusBar();
        init();
        initView();
        initData();
    }


    /**
     * activity之间跳转传参获取
     * @param bundle
     */
    protected void initParam(Bundle bundle) {

    }
    /**
     * Activity跳转
     *
     * @param clz 要跳转的Activity的类名
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * Activity携带数据的跳转
     *
     * @param clz 要跳转的Activity的类名
     * @param bundle  bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 防止快速点击
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showInputMethod(){
        if (getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
        }
    }

    /**
     * 设置布局文件
     */
    protected  abstract void setContentView();
    /**
     * 初始化控件
     */
    protected abstract void initView();
    /**
     * 初始化数据
     */
    protected  abstract  void initData();
    /**
     * 全局检测网络广播的回调 处理网络变化
     * 注:在程序第一次启动的时候,没网并不会回调,需要自己在启动页面,或者主页自己再判断一次
     * @param netWorkState 网络状态    -1:没网络 0:移动网络 1:WiFi网络
     */
    public abstract void onNetChanged(int netWorkState);



    public void setToolBar(){}

    /**
     * 初始化presenter和view
     */
    private void init() {
        synchronized (mActivities) {
            mActivities.add(this);
        }

    }

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
        setSupportActionBar(getToolBar());
    }

    /**
     * 得到toolbar的实例
     * @return Toolbar的实例
     */
    public Toolbar getToolBar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_base);
        return mToolbar;
    }

}
