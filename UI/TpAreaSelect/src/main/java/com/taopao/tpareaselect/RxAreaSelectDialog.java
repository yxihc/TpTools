package com.taopao.tpareaselect;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author： 淘跑
 * @Date: 2018/5/29 15:40
 * @Use： 专为地址选择器打照的Dialog
 */
public class RxAreaSelectDialog extends Dialog {
    ImageView mIvClose;
    TabLayout mTlTitle;
    RecyclerView mRv;
    private OnSelectedResultCallBack resultCallBack;

    private AreaAdapter mAdapter;
    private Map<Integer, AreaBean> currentMap = new TreeMap<>();
    private View mContentView;

    public RxAreaSelectDialog(Context context) {
        super(context, R.style.RxDialog);
        init(context);
    }

    private Context mContext;

    private void init(Context context) {
        mContext = context;
        mContentView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet_dialog, null);
        mIvClose = mContentView.findViewById(R.id.iv_close);
        mTlTitle = mContentView.findViewById(R.id.tl_title);
        mRv = mContentView.findViewById(R.id.rv);
        setContentView(mContentView);
        initView();
        setAnimation(R.style.Dialog_ChooseAddress_Animation);
    }


    public void setAnimation(int id) {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = DensityUtils.dp2px(context, 400);
        window.setAttributes(params);
        window.setWindowAnimations(id);
        window.setGravity(Gravity.BOTTOM);
    }

    private void initView() {
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mAdapter = new AreaAdapter(mContext);
        mAdapter.setOnSelectedListener(new AreaAdapter.OnSelectedListener() {
            @Override
            public void onSelected(Map<Integer, AreaBean> map, int pos) {
                if (pos >= 2) {
                    if (resultCallBack != null) {
                        String names = currentMap.get(pos).getNames();
                        String[] split = names.split(",");
                        if (split.length > 2) {
                            resultCallBack.onResult(currentMap.get(pos).getNames().replace(",", ""), split[0], split[1], split[2]);
                        }
                    }
                    dismiss();
                } else {
                    currentMap = map;
                    mTlTitle.removeAllTabs();
                    for (Integer in : map.keySet()) {
                        mTlTitle.addTab(
                                mTlTitle.newTab().setText(map.get(in).getName()));
                    }
                    addChooseTab();
                }
            }
        });
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(manager);
        mRv.addItemDecoration(new LineAreaItemDecoration(getContext(), 2));
        mRv.setAdapter(mAdapter);

        mTlTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTlTitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 0) {
                    mAdapter.setData(pos, AreaParser.getInstance(mContext).getProvinceList());
                } else {
                    mAdapter.setData(pos, AreaParser.getInstance(mContext).getChildList(currentMap.get(pos - 1).getTid()));
                }
                //移动到指定位置
                mAdapter.moveToPosition(manager);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        addChooseTab();
    }

    private void addChooseTab() {
        mTlTitle.addTab(mTlTitle.newTab().setText("请选择"), true);
        TabLayoutUtil.reflex(mContext, mTlTitle);
    }

    public RxAreaSelectDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public RxAreaSelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public void setResultCallBack(OnSelectedResultCallBack resultCallBack) {
        this.resultCallBack = resultCallBack;
    }

    public interface OnSelectedResultCallBack {

        void onResult(String result, String province, String city, String area);
    }
}
