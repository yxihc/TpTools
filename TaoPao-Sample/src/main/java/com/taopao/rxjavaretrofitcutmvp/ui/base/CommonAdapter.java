package com.taopao.rxjavaretrofitcutmvp.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.taopao.rxjavaretrofitcutmvp.app.App;
import java.util.ArrayList;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected ArrayList<T> mDatas;
    protected final int mItemLayoutId;
    boolean isUseConvertView;

    public CommonAdapter(Context context, ArrayList<T> mDatas, int itemLayoutId) {
        if (context != null) {
            this.mContext = context;
        } else {
            this.mContext = App.getContext();
        }
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonAdapter(Context context, ArrayList<T> mDatas, int itemLayoutId, boolean isUseConvertView) {
        if (context != null) {
            this.mContext = context;
        } else {
            this.mContext =App.getContext();
        }
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
        this.isUseConvertView = isUseConvertView;
    }



    public void onDateChange(ArrayList<T> result) {
        this.mDatas = result;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        T t = null;
        try {
            t = mDatas.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//		if(this.mContext)
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        try {
            convert(viewHolder, getItem(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position, isUseConvertView);
    }

}
