package com.taopao.rxjavaretrofitcutmvp.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.MDFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.NetFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.RxJavaFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.UiFragment;
import com.taopao.rxjavaretrofitcutmvp.ui.fragment.UtilsFragment;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 12:05
 * @Use: 程序入口
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, EasyPermissions.PermissionCallbacks {
    //Tab 文字
    private final int[] TAB_TITLES = new int[]{R.string.tab_net, R.string.tab_ui, R.string.tab_md, R.string.tab_utils};
    //Tab 图片
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_net_selector, R.drawable.tab_ui_selector, R.drawable.tab_md_selector, R.drawable.tab_utils_selector};
    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.toolbar_base_title)
    TextView mToolbarTitle;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.rb_net)
    RadioButton mRbNet;
    @BindView(R.id.rb_ui)
    RadioButton mRbUi;
    @BindView(R.id.rb_md)
    RadioButton mRbMd;
    @BindView(R.id.rb_utils)
    RadioButton mRbUtils;
    @BindView(R.id.rb_rxava)
    RadioButton mRbRxava;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPermission();
    }

    @Override
    protected void initView() {
        getToolBar().setNavigationIcon(null);
        mFragments = new ArrayList<>();
        mFragments.add(NetFragment.getInstance("网络相关"));
        mFragments.add(UiFragment.getInstance("UI"));
        mFragments.add(MDFragment.getInstance("MD"));
        mFragments.add(UtilsFragment.getInstance("工具类"));
        mFragments.add(RxJavaFragment.getInstance("RxJava"));
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
        mRadioGroup.setOnCheckedChangeListener(this);
        mVpHome.setOnPageChangeListener(this);
        mToolbarTitle.setText(R.string.tab_net);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    private void getPermission() {
        //动态申请SD卡读写权限
        EasyPermissions.requestPermissions(
                new PermissionRequest
                        .Builder(this, 0, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setRationale("提示")
                        .setNegativeButtonText("申请SD卡读取权限")
                        .build()
        );
    }

    @Override
    public void onNetChanged(int netState) {
        switch (netState) {
            case NetUtils.NETWORK_NONE:
                UIUtils.showToast("没有网络");
                break;
            case NetUtils.NETWORK_MOBILE:
                UIUtils.showToast("移动网络");
                break;
            case NetUtils.NETWORK_WIFI:
                UIUtils.showToast("WiFi网络");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_net:
                mVpHome.setCurrentItem(0);
                mToolbarTitle.setText(R.string.tab_net);
                break;
            case R.id.rb_ui:
                mVpHome.setCurrentItem(1);
                mToolbarTitle.setText(R.string.tab_ui);
                break;
            case R.id.rb_md:
                mVpHome.setCurrentItem(2);
                mToolbarTitle.setText(R.string.tab_md);
                break;
            case R.id.rb_utils:
                mVpHome.setCurrentItem(3);
                mToolbarTitle.setText(R.string.tab_utils);
                break;
            case R.id.rb_rxava:
                mVpHome.setCurrentItem(4);
                mToolbarTitle.setText(R.string.tab_rxjava);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRbNet.setChecked(true);
                break;
            case 1:
                mRbUi.setChecked(true);
                break;
            case 2:
                mRbMd.setChecked(true);
                break;
            case 3:
                mRbUtils.setChecked(true);
                break;
            case 4:
                mRbRxava.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //处理权限名字字符串
        StringBuffer sb = new StringBuffer();
        for (String str : perms) {
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


    @Override
    public void onBackPressed() {
        //不退出程序 进入后台
        moveTaskToBack(true);
    }
}
