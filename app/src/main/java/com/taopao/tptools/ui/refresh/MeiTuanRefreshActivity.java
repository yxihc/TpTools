package com.taopao.tptools.ui.refresh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.taopao.tptools.R;
import com.taopao.tptools.databinding.ActivityMeiTuanRefreshBinding;

public class MeiTuanRefreshActivity extends AppCompatActivity {
    private com.taopao.tptools.databinding.ActivityMeiTuanRefreshBinding mInflate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflate = ActivityMeiTuanRefreshBinding.inflate(LayoutInflater.from(this));
        setContentView(mInflate.getRoot());
    }
}
