package com.taopao.rxjavaretrofitcutmvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.activity.net.CutMvpActivity;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　     ┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Author: 淘跑
 * @Data: 2018/2/7 22:45
 * @Use:
 */

public class StaggerdAdapter extends RecyclerView.Adapter<StaggerdAdapter.StaggerdHolder> {
    private ImgListInfo mImgListInfo;

    private Context mContext;

    public StaggerdAdapter(Context context, ImgListInfo imgListInfo) {
        this.mImgListInfo = imgListInfo;
        this.mContext = context;
    }

    @Override
    public StaggerdAdapter.StaggerdHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_pubu, parent, false);
        return new StaggerdAdapter.StaggerdHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StaggerdAdapter.StaggerdHolder holder, final int position) {
        Glide.with(mContext)
                .load(mImgListInfo.getResults().get(position).getUrl())
                .into(holder.mIvPic);
        holder.mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemOnClickListener.onItemClick(mImgListInfo.getResults().get(position),position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImgListInfo.getResults().size();
    }


    class StaggerdHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;

        public StaggerdHolder(View itemView) {
            super(itemView);
            mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    private onItemOnClickListener mOnItemOnClickListener;

    public void setOnItemOnClickListener(onItemOnClickListener mOnItemOnClickListener) {
        this.mOnItemOnClickListener = mOnItemOnClickListener;
    }

    public interface onItemOnClickListener {
        void onItemClick(ImgListInfo.ResultsBean resultsBean, int position,View view);
    }
}
