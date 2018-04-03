package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaredrummler.android.widget.AnimatedSvgView;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.ModelSVG;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.presenter.SplashPresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.view.SplashView;
import com.taopao.rxjavaretrofitcutmvp.utils.LogUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseMvpActivity<SplashPresenter, SplashView> implements SplashView {

    public final static int JUMP_TO_ACTIVITY = 10;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case JUMP_TO_ACTIVITY:
                    //跳转页面
                    jump2Activity();
                    break;
            }
        }
    };
    @BindView(R.id.svg_view)
    AnimatedSvgView mSvgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启动页流程

        //第一步启动页2秒关闭
        mHandler.sendEmptyMessageDelayed(JUMP_TO_ACTIVITY, 1000);

        //第二步 获取广告图 并显示(广告可以点击)
//        initData();
        setSvg(ModelSVG.values()[4]);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    private void setSvg(ModelSVG modelSvg) {
        mSvgView.setGlyphStrings(modelSvg.glyphs);
        mSvgView.setFillColors(modelSvg.colors);
        mSvgView.setViewportSize(modelSvg.width, modelSvg.height);
        mSvgView.setTraceResidueColor(0x32000000);
        mSvgView.setTraceColors(modelSvg.colors);
        mSvgView.rebuildGlyphData();
        mSvgView.start();
    }

    /**
     * 跳转登录页面或者主页面
     */
    public void jump2Activity() {
        //跳转页面
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void initMvpData() {
        LogUtils.d("===="+1);
        int i = new Random().nextInt(10);
        mPresenter.getImagePage(i + "");
    }

    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public SplashView createView() {
        return this;
    }

    @Override
    public void onNetChanged(int netWorkState) {

    }

    @Override
    public void onGetImgListPageResult(ImgListInfo imgListInfo) {
        Random random = new Random();
        int i = random.nextInt(imgListInfo.getResults().size());
        Glide.with(this).load(imgListInfo.getResults().get(i).getUrl()).into(iv_bg);


        //第三步显示完跳转主页或登录页面
        //清除掉消息
        mHandler.removeMessages(JUMP_TO_ACTIVITY);
        //启动页广告图如果显示的话,重新计时跳转页面
        mHandler.sendEmptyMessageDelayed(JUMP_TO_ACTIVITY, 1000);
    }

    @Override
    public void onGetImgListPageError(Throwable e) {
        //判断是否有网
        //提示错误信息
        //弹出弹窗 或是 直接跳转页面
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(JUMP_TO_ACTIVITY);
    }
}
