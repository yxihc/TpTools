package com.taopao.rxareaselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author： 淘跑
 * @Date: 2018/5/29 12:21
 * @Use： 地址选择器Adapter
 */
public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private int tab_province = 0;
    private int tab_city = 1;
    private int tab_district = 2;
    private AreaBean selectItem;
    private int currentTabPos;
    private Map<Integer, AreaBean> map;
    private OnSelectedListener onSelectedListener;

    public void setData(int tabPos, List<AreaBean> data) {
        currentTabPos = tabPos;
        this.mData = data == null ? new ArrayList<AreaBean>() : data;
        notifyDataSetChanged();
    }

    protected void convert(ViewHolder helper, final AreaBean item) {
        if (item.isCheck()) {
            helper.mIvCheck.setVisibility(View.VISIBLE);
        } else {
            helper.mIvCheck.setVisibility(View.GONE);
        }
        helper.mTvName.setText(item.getName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(item);
            }
        });
    }

    private void selectItem(AreaBean bean) {
        selectItem = bean;
        if (currentTabPos == tab_province) {
            map.clear();
        } else if (currentTabPos == tab_city) {
            map.remove(tab_district);
        }
        map.put(currentTabPos, selectItem);
        for (AreaBean areaBean : getData()) {
            if (selectItem.getIds().equals(areaBean.getIds())) {
                areaBean.setCheck(true);
            } else {
                areaBean.setCheck(false);
            }
        }
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(map, currentTabPos);
        }
    }


    public void moveToPosition(LinearLayoutManager manager) {
        manager.scrollToPositionWithOffset(AreaParser.getInstance(mContext).getChoosePos(getData()), 0);
        manager.setStackFromEnd(true);
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }


    public interface OnSelectedListener {
        void onSelected(Map<Integer, AreaBean> map, int pos);
    }

    //////////////////////////////////////////adapter基本使用/////////////////////////////////////////////////////
    List<AreaBean> mData;

    private Context mContext;

    public AreaAdapter(Context context) {
        mContext = context;
        map = new TreeMap<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private List<AreaBean> getData() {
        return mData;
    }

    public AreaBean getItem(@IntRange(from = 0) int position) {
        if (position < mData.size()) {
            return mData.get(position);
        } else {
            return null;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        convert(holder, getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvCheck;
        private TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvCheck = itemView.findViewById(R.id.iv_check);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
