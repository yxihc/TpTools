package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.CustomViewMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.design.ImmersiveActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.NetMainActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MDFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MyGithubFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.NetFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.TabLayoutFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.UiFragment;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.bottom_navigation_bar)
    BottomBar mBottomBar;
    private RelativeLayout mRl_netstate;
    private RecyclerView mRv_context;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFragments = new ArrayList<>();
        mFragments.add(NetFragment.getInstance("网络相关"));
        mFragments.add(UiFragment.getInstance("网络相关"));
        mFragments.add(MDFragment.getInstance("ui"));
        mFragments.add(MyGithubFragment.getInstance("https://github.com/404NotFuond"));


        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_net:
                        mVpHome.setCurrentItem(0);
                        break;
                    case R.id.tab_ui:
                        mVpHome.setCurrentItem(1);
                        break;
                    case R.id.tab_md:
                        mVpHome.setCurrentItem(2);
                        break;
                    case R.id.tab_f:
                        mVpHome.setCurrentItem(3);
                        break;

                }
            }
        });


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
                        case 0:
                            startActivity(new Intent(MainActivity.this, NetMainActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this, CustomViewMainActivity.class));
                            break;
                        case 2:
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
