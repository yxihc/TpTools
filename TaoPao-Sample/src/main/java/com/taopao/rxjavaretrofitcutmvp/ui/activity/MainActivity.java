package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

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
 * @Data: 2018/1/29 12:05
 * @Use: 程序入口
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);

        setToolBar();
    }

    @Override
    public void setToolBar() {
        getToolBar().setNavigationIcon(null);
        getToolBar().setTitle("标题");
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    public void weixinweb(View view){
        WebViewActivity.loadUrl(this,"https://github.com/404NotFuond/RxJava-Retrofit-CutMvp");
    }
    public void mvp(View view){
        startActivity(new Intent(this,CutMvpActivity.class));
    }

    public void fr(View view){
        startActivity(new Intent(this,FrActivity.class));
    }
    public void dialog(View view){
        startActivity(new Intent(this,DialogActivity.class));
    }
    public void random(View view){
        startActivity(new Intent(this,RandomTextActivity.class));
    }
    public void mmersive(View view){
        startActivity(new Intent(this,ImmersiveActivity.class));
    }

    public void downloadapk(View view){
        startActivity(new Intent(this,DownLoadApkActivity.class));
    }
}
