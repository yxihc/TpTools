package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.utils.SnackbarUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    public void snake(View view){
//        //需要design包
//        final Snackbar make = Snackbar.make(view, "您确定要开启加速?", Snackbar.LENGTH_SHORT);
//        make.setAction("确定", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                make.dismiss();
//            }
//        });
//        make.show();

        SnackbarUtils.ShortSnackbar(view,"您确定要开启加速",SnackbarUtils.Info).show();
    }

    public void customdia(View view){
        showWaitingDialog("正在加载...");
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hideWaitingDialog();
            }
        }.start();
    }
    public void m(View view){
        showMaterialDialog("提示", "您确定要开启加速", "取消", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMaterialDialog();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("666");
                hideMaterialDialog();
            }
        });
    }
}
