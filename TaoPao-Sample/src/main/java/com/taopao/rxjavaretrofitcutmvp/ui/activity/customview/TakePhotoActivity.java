package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.CommonAdapter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.ViewHolder;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class TakePhotoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.btn_takephotos)
    Button mBtnTakephotos;
    @BindView(R.id.gv_photos)
    GridView mGvPhotos;
    private int REQUEST_CODE_CHOOSE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_take_photo);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onNetChanged(int netWorkState) {

    }

    @OnClick(R.id.btn_takephotos)
    public void onViewClicked() {

        //如果启用相机的话需要申请权限
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            getPermission();
        } else {
            takePhotos();
        }
    }

    private void takePhotos() {
        Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))//选择mime的类型
                .countable(true)//设置从1开始的数字
                .maxSelectable(10)//选择图片的最大数量限制
                .capture(true)//启用相机
                .captureStrategy(new CaptureStrategy(true, "com.taopao.rxjavaretrofitcutmvp.fileprovider"))//自定义FileProvider
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)//屏幕显示方向
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .theme(R.style.Matisse_Dracula) // 黑色背景
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }


    ArrayList<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = (ArrayList<Uri>) Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            setGvData();
        }
    }

    private void setGvData() {
        mGvPhotos.setAdapter(new CommonAdapter<Uri>(this, mSelected, R.layout.item_rv_pubu) {
            @Override
            public void convert(ViewHolder helper, Uri item) {
                ImageView ivPic = (ImageView) helper.getView(R.id.ivPic);
                Glide.with(TakePhotoActivity.this)
                        .load(item)
                        .into(ivPic);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        takePhotos();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要相机权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show();
        }
    }


    private void getPermission() {
        //动态申请相机权限
        EasyPermissions.requestPermissions(
                new PermissionRequest
                        .Builder(this, 0, Manifest.permission.CAMERA)
                        .setRationale("申请相机权限")
                        .setNegativeButtonText("没有此权限无法使用此功能")
                        .build()
        );
    }
}
