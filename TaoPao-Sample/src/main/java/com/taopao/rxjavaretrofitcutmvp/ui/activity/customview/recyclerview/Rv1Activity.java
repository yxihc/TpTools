package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taopao.rxjavaretrofitcutmvp.R;
import java.util.ArrayList;
import java.util.List;

public class Rv1Activity extends AppCompatActivity {

    private RecyclerView mRv_context;
    final int[] picResId = new int[]{R.mipmap.p5, R.mipmap.p33,R.mipmap.p6, R.mipmap.p7,R.mipmap.p8,R.mipmap.p12,R.mipmap.p12};
    private ArrayList<Integer> mInts;
    private RvAdapter mAdapter;
    private View mInflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv1);

        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new GridLayoutManager(this, 2));
        mInflate = View.inflate(this, R.layout.activity_zc1, null);

        mInts = new ArrayList<>();

        mAdapter = new RvAdapter(R.layout.item_rv, mInts);
        mAdapter.setEmptyView(mInflate);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Rv1Activity.this, "onItemClick:"+position, Toast.LENGTH_SHORT).show();
            }
        });



        mInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < picResId.length; i++) {
                    mInts.add(picResId[i]);
                }
                mRv_context.setAdapter(mAdapter);
            }
        });

    }

    public class RvAdapter extends BaseQuickAdapter<Integer,BaseViewHolder>{
        public RvAdapter(int layoutResId, @Nullable List<Integer> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            helper.setImageResource(R.id.ivPic,item);
        }
    }
}
