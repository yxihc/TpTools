package com.taopao.rxjavaretrofitcutmvp.ui.activity.utils;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class PermissionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_camera) void onClick() {
        Toast.makeText(PermissionActivity.this, "ass", Toast.LENGTH_SHORT).show();
        EasyPermissions.requestPermissions(
                new PermissionRequest
                        .Builder(this, 0, Manifest.permission.CAMERA)
                        .setRationale("提示")
                        .setNegativeButtonText("")
                        .build()

        );

//        EasyPermissions.requestPermissions(this, "申请权限",
//                0, Manifest.permission.CAMERA);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
