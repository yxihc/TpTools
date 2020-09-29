package com.taopao.tptools.tab.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.taopao.tptools.R;
import com.taopao.tptools.databinding.FragmentHomeBinding;
import com.taopao.tptools.ui.JDRefreshActivity;
import com.taopao.tptools.ui.VerificationCodeViewActivity;
import com.taopao.tptools.ui.refresh.MeiTuanRefreshActivity;


public class HomeFragment extends Fragment {

    private com.taopao.tptools.databinding.FragmentHomeBinding mBind;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mBind = FragmentHomeBinding.bind(root);
        return mBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {

        mBind.jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JDRefreshActivity.class));
            }
        });
        mBind.mt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeiTuanRefreshActivity.class));
            }
        });
        mBind.inputcode.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), VerificationCodeViewActivity.class));
        });
    }

}
