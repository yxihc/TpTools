package com.taopao.rxjavaretrofitcutmvp.ui.activity.design;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.taopao.rxjavaretrofitcutmvp.R;

public class Design5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design5);
    }
    public void move(View view){
        ViewCompat.offsetTopAndBottom(view,9);
    }
}
