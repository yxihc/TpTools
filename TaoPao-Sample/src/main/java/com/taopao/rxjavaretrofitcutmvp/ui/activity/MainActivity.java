package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.CustomViewMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.DialogActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.FrActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.RandomTextActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.WebViewActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ImmersiveActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.NetMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;

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

    private RelativeLayout mRl_netstate;
    private RecyclerView mRv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRl_netstate = (RelativeLayout) findViewById(R.id.rl_netstate);

        mRl_netstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtils.openSetting(MainActivity.this);
            }
        });
        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new GridLayoutManager(this, 2));
        mRv_context.setAdapter(new GridAdapter());
        setToolBar();
    }

    @Override
    public void setToolBar() {
        getToolBar().setNavigationIcon(null);
        getToolBar().setTitle("主页");
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

        switch (netState) {
            case NetUtils.NETWORK_NONE:
                Toast.makeText(MainActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                mRl_netstate.setVisibility(View.VISIBLE);
                break;
            case NetUtils.NETWORK_MOBILE:
                Toast.makeText(MainActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                mRl_netstate.setVisibility(View.GONE);
                break;
            case NetUtils.NETWORK_WIFI:
                Toast.makeText(MainActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
                mRl_netstate.setVisibility(View.GONE);
                break;
        }
    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

        final int[] picResId = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3
        };
        final String[] mTitle = new String[]{"网络相关", "UI相关", "Material Design"
        };

        @Override
        public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
            return new GridViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GridViewHolder holder, final int position) {
            holder.mIvPic.setImageResource(picResId[position]);
            holder.mTvTitle.setText(mTitle[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case  0:
                            startActivity(new Intent(MainActivity.this, NetMainActivity.class));
                            break;
                        case  1:
                            startActivity(new Intent(MainActivity.this, CustomViewMainActivity.class));
                            break;
                        case  2:
                            startActivity(new Intent(MainActivity.this, ImmersiveActivity.class));
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
