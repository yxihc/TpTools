package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

import java.util.Random;

public class ImmersiveActivity extends BaseActivity {

    private CheckBox chb_translucent;
    private Toolbar toolbar;
    private SeekBar sb_change_alpha;
    private int mColor=0;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
        mStatusBarColor = getResources().getColor(R.color.colorPrimary);

        initView();
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

    @Override
    public void onNetChanged(int netState) {

    }

    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
    int  mStatusBarColor = 0;
    private void initView() {

        mImageView = (ImageView) findViewById(R.id.imageView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        sb_change_alpha = (SeekBar) findViewById(R.id.sb_change_alpha);
        chb_translucent = (CheckBox) findViewById(R.id.chb_translucent);

        //设置toolbar透明
        chb_translucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chb_translucent.isChecked()) {
                    StatusBarUtil.setTranslucent(ImmersiveActivity.this);
                    toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                } else {
                    chb_translucent.setBackgroundDrawable(null);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    StatusBarUtil.setColor(ImmersiveActivity.this,
                            getResources().getColor(R.color.colorPrimary));
                }
            }
        });


        sb_change_alpha.setMax(255);
        sb_change_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                if (chb_translucent.isChecked()) {
                    StatusBarUtil.setTranslucent(ImmersiveActivity.this, mAlpha);
                } else {
                    StatusBarUtil.setColor(ImmersiveActivity.this, mStatusBarColor, mAlpha);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_change_alpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);

    }

    public void changecolor(View view){
        Random random = new Random();
        mStatusBarColor = 0xff000000 | random.nextInt(0xffffff);
        toolbar.setBackgroundColor(mStatusBarColor);
        StatusBarUtil.setColor(ImmersiveActivity.this, mStatusBarColor, mAlpha);
    }
    public void swip(View view){
        Intent intent = new Intent(this, SwipeBackActivity.class);
        intent.putExtra(SwipeBackActivity.EXTRA_IS_TRANSPARENT, false);
        startActivity(intent);
    }
    public void design1(View view){
        Intent intent = new Intent(this, Design1Activity.class);
        startActivity(intent);
    }
    public void design2(View view){
        Intent intent = new Intent(this, Design2Activity.class);
        startActivity(intent);
    }

    public void design3(View view){
        Intent intent = new Intent(this, Design3Activity.class);
        startActivity(intent);
    }
    public void design4(View view){
        Intent intent = new Intent(this, Design4Activity.class);
        startActivity(intent);
    }

    public void design5(View view){
        Intent intent = new Intent(this, Design5Activity.class);
        startActivity(intent);
    }
    public void searchview1(View view){
        Intent intent = new Intent(this, SearchViewActivity.class);
        startActivity(intent);
    }
    public void zc1(View view){

		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mImageView, "iv");
        Intent intent = new Intent(this, ZC1Activity.class);
        startActivity(intent, optionsCompat.toBundle());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
