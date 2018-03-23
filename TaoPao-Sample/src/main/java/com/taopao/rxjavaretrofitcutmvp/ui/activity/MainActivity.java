package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;
import com.jaeger.library.StatusBarUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.app.App;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MDFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MyGithubFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.NetFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.UiFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.UtilsFragment;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 12:05
 * @Use: 程序入口
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

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
        //动态申请SD卡读写权限
        EasyPermissions.requestPermissions(
                new PermissionRequest
                        .Builder(this, 0, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setRationale("提示")
                        .setNegativeButtonText("申请SD卡读取权限")
                        .build()
        );

        getToolBar().setNavigationIcon(null);

        mFragments = new ArrayList<>();
        mFragments.add(NetFragment.getInstance("网络相关"));
        mFragments.add(UiFragment.getInstance("UI"));
        mFragments.add(MDFragment.getInstance("MD"));
        mFragments.add(UtilsFragment.getInstance("工具类"));

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
                        mToolbarTitle.setText("工具类");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//            MiPushClient.registerPush(MainActivity.this, App.APP_ID, App.APP_KEY);

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //处理权限名字字符串
        StringBuffer sb = new StringBuffer();
        for (String str : perms){
            sb.append(str);
            sb.append("\n");
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show();
        }
    }

}
