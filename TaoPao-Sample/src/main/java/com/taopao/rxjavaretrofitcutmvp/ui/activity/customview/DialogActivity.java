package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.utils.SnackbarUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;
import com.taopao.shapeloading.ShapeLoadingDialog;

public class DialogActivity extends BaseMvpActivity {

    private RecyclerView mRv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new GridLayoutManager(this, 2));
        mRv_context.setAdapter(new GridAdapter());
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setToolBar() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void onNetChanged(int netState) {

    }


    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

        final int[] picResId = new int[]{R.mipmap.p22, R.mipmap.p22, R.mipmap.p22, R.mipmap.p22
        };
        final String[] mTitle = new String[]{"SnakeBar", "Custom Dalog", "MaterialDialog"
                ,"仿58同城加载动画"
        };

        @Override
        public GridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
            return new GridAdapter.GridViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GridAdapter.GridViewHolder holder, final int position) {
            holder.mIvPic.setImageResource(picResId[position]);
            holder.mTvTitle.setText(mTitle[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case  0:
                            SnackbarUtils.ShortSnackbar(v,"您确定要开启加速",SnackbarUtils.Info).show();
                            break;
                        case  1:
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    try {
                                        sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        case  2:
                            break;
                        case  3:
                            new ShapeLoadingDialog.Builder(DialogActivity.this)
                                    .loadText("加载中")
                                    .show();
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return picResId.length;
        }

        class GridViewHolder extends RecyclerView.ViewHolder {
            ImageView mIvPic;
            TextView mTvTitle;

            public GridViewHolder(View itemView) {
                super(itemView);
                mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
                mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            }
        }
    }
}
