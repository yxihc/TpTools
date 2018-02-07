package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.MainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.CustomViewMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.SwipeBackActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.NetMainActivity;
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
    private RecyclerView mRv_context;

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







        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new GridLayoutManager(this, 2));
        mRv_context.setAdapter(new GridAdapter());

    }
    public void changecolor(View view){
        Random random = new Random();
        mStatusBarColor = 0xff000000 | random.nextInt(0xffffff);
        toolbar.setBackgroundColor(mStatusBarColor);
        StatusBarUtil.setColor(ImmersiveActivity.this, mStatusBarColor, mAlpha);
    }
    public void zc1(View view){

		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mImageView, "iv");
        Intent intent = new Intent(this, ZC1Activity.class);
        startActivity(intent, optionsCompat.toBundle());
    }



    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

        final int[] picResId = new int[]{R.mipmap.p22, R.mipmap.p22, R.mipmap.p22,R.mipmap.p22, R.mipmap.p22, R.mipmap.p22,R.mipmap.p22,R.mipmap.p22,R.mipmap.p22
        };
        final String[] mTitle = new String[]{"侧滑关闭", "下拉显示图片",
                "文字跟随下拉","Toolbar上滑消失","Toolbar文字居中",
                "跟随移动","SearchView","TabLayout"
                ,"向上滑动新页面"
        };

        @Override
        public GridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
            return new GridAdapter.GridViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GridAdapter.GridViewHolder holder, final int position) {
            holder.mIvPic.setImageResource(picResId[position]);
            holder.mTvTitle.setText(mTitle[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (position) {
                        case  0:
                             intent = new Intent(ImmersiveActivity.this, SwipeBackActivity.class);
                            intent.putExtra(SwipeBackActivity.EXTRA_IS_TRANSPARENT, false);
                            startActivity(intent);
                            break;
                        case  1:
                             intent = new Intent(ImmersiveActivity.this, Design1Activity.class);
                            startActivity(intent);
                            break;
                        case  2:
                             intent = new Intent(ImmersiveActivity.this, Design2Activity.class);
                            startActivity(intent);
                            break;
                        case  3:
                             intent = new Intent(ImmersiveActivity.this, Design3Activity.class);
                            startActivity(intent);
                            break;
                        case  4:
                             intent = new Intent(ImmersiveActivity.this, Design4Activity.class);
                            startActivity(intent);
                            break;
                        case  5:
                             intent = new Intent(ImmersiveActivity.this, Design5Activity.class);
                            startActivity(intent);
                            break;
                        case  6:
                             intent = new Intent(ImmersiveActivity.this, SearchViewActivity.class);
                            startActivity(intent);
                            break;
                        case  7:
                            intent = new Intent(ImmersiveActivity.this, TabLayoutActivity.class);
                            startActivity(intent);
                            break;
                        case  8:
                            intent = new Intent(ImmersiveActivity.this, BottomBehaviorActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return picResId.length;
        }

        class GridViewHolder extends RecyclerView.ViewHolder {
            ImageView mIvPic;
            TextView mTvTitle;

            public GridViewHolder(View itemView) {
                super(itemView);
                mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
                mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
