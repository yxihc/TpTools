package com.taopao.tptools.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.taopao.tptools.R;
import com.taopao.tpverificationcodeview.SoftInputUtils;
import com.taopao.tpverificationcodeview.VerificationCodeInputView;

public class VerificationCodeViewActivity extends AppCompatActivity implements VerificationCodeInputView.OnInputListener {

    private VerificationCodeInputView mVerificationCodeInputView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code_view);

        mVerificationCodeInputView1 = findViewById(R.id.vciv_code1);

//        SoftInputUtils.toggleSoftInput(this);

        mVerificationCodeInputView1.setOnInputListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(view -> {
            mVerificationCodeInputView1.clearCode();
            SoftInputUtils.showSoftInput(this,mVerificationCodeInputView1);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SoftInputUtils.showSoftInput(this,mVerificationCodeInputView1);
    }
    @Override
    public void onComplete(String code) {
        Toast.makeText(VerificationCodeViewActivity.this, code, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onInput() {

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}