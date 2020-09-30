package com.taopao.simple;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.taopao.rxareaselect.RxAreaSelectDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbar_base_title;
    private Toolbar toolbar_base;
    private EditText et_city_name;
    private LinearLayout ll_city;
    private LinearLayout ll_root;
    private RxAreaSelectDialog mDialog;
    private TextView tv_s;
    private TextView tv_c;
    private TextView tv_q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar_base_title = (TextView) findViewById(R.id.toolbar_base_title);
        toolbar_base = (Toolbar) findViewById(R.id.toolbar_base);
        et_city_name = (EditText) findViewById(R.id.et_city_name);
        ll_city = (LinearLayout) findViewById(R.id.ll_city);
        ll_city.setOnClickListener(this);
        et_city_name.setOnClickListener(this);

        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar_base);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        tv_s = (TextView) findViewById(R.id.tv_s);
        tv_c = (TextView) findViewById(R.id.tv_c);
        tv_q = (TextView) findViewById(R.id.tv_q);
    }

    @Override
    public void onClick(View v) {
        ll_root.animate()
                .scaleX(0.85f)
                .scaleY(0.85f)
                .setDuration(300).start();
        mDialog = new RxAreaSelectDialog(this);
//        mDialog.setAnimation(R.style.Dialog_Up_Animation);
        mDialog.setResultCallBack(new RxAreaSelectDialog.OnSelectedResultCallBack() {
            @Override
            public void onResult(String result, String province, String city, String area) {
                et_city_name.setText(result);
                tv_s.setText(province);
                tv_c.setText(city);
                tv_q.setText(area);

                Log.d("RxAreaSelect", "省市区: " + result);
                Log.d("RxAreaSelect", "省: " + province);
                Log.d("RxAreaSelect", "市: " + city);
                Log.d("RxAreaSelect", "区: " + area);

            }
        });
        mDialog.show();
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ll_root.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(300).start();
            }
        });
    }
}
