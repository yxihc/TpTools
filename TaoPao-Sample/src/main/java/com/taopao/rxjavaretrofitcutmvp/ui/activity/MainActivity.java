package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;
import com.jaeger.library.StatusBarUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MDFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MyGithubFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.NetFragment;
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
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getToolBar().setNavigationIcon(null);

        mFragments = new ArrayList<>();
        mFragments.add(NetFragment.getInstance("网络相关"));
        mFragments.add(UiFragment.getInstance("ui"));
        mFragments.add(MDFragment.getInstance("md"));
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
                mBottomBar.selectTabAtPosition(position, true);
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
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.main_net), 0);
                        getToolBar().setBackgroundColor(getResources().getColor(R.color.main_net));
                        mToolbarTitle.setText("网络相关");
                        break;
                    case R.id.tab_ui:
                        mVpHome.setCurrentItem(1);
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.main_ui), 0);
                        getToolBar().setBackgroundColor(getResources().getColor(R.color.main_ui));
                        mToolbarTitle.setText("UI相关");
                        break;
                    case R.id.tab_md:
                        mVpHome.setCurrentItem(2);
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.main_md), 0);
                        getToolBar().setBackgroundColor(getResources().getColor(R.color.main_md));
                        mToolbarTitle.setText("Material Design");
                        break;
                    case R.id.tab_f:
                        mVpHome.setCurrentItem(3);
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.main_mygit), 0);
                        getToolBar().setBackgroundColor(getResources().getColor(R.color.main_mygit));
                        mToolbarTitle.setText("我的GitHub");
                        break;
                }
            }
        });

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
                break;
            case NetUtils.NETWORK_MOBILE:
                Toast.makeText(MainActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                break;
            case NetUtils.NETWORK_WIFI:
                Toast.makeText(MainActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
