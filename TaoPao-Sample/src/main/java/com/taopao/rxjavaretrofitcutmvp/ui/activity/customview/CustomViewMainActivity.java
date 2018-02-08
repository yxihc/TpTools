package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.MainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ImmersiveActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.NetMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

public class CustomViewMainActivity extends BaseActivity {

    private RecyclerView mRv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_main);
        getToolBar().setTitle("UI相关");
        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new GridLayoutManager(this, 2));
        mRv_context.setAdapter(new GridAdapter());
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
    public void onNetChanged(int netWorkState) {

    }



    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

        final int[] picResId = new int[]{R.mipmap.p5, R.mipmap.p6, R.mipmap.p7,R.mipmap.p8,R.mipmap.p12,R.mipmap.p40};
        final String[] mTitle = new String[]{"随机显示TextView", "Dialog",
                "仿微信朋友圈下拉刷新","仿微信带进度条网页","RecyclerView拖动","Glide使用"};

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
                    switch (position) {
                        case 0:
                            startActivity(new Intent(CustomViewMainActivity.this, RandomTextActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(CustomViewMainActivity.this, DialogActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(CustomViewMainActivity.this, FrActivity.class));
                            break;
                        case 3:
                            WebViewActivity.loadUrl(CustomViewMainActivity.this, "https://github.com/404NotFuond/RxJava-Retrofit-CutMvp");
                            break;
                        case 4:
                            startActivity(new Intent(CustomViewMainActivity.this, DragItemActivity.class));
                            break;
                        case 5:
                            startActivity(new Intent(CustomViewMainActivity.this, GlideActivity.class));
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


}
