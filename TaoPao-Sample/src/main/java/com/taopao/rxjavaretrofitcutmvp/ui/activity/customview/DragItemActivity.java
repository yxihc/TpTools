package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.model.Cheeses;
import com.taopao.rxjavaretrofitcutmvp.ui.adapter.ItemTouchHelperAdapter;
import com.taopao.rxjavaretrofitcutmvp.widget.MyItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class DragItemActivity extends AppCompatActivity implements ItemTouchHelperAdapter.ItemDragListener {

    private RecyclerView mRv_context;
    private List<String> mData = new ArrayList<>();
    private ItemTouchHelperAdapter mAdapter;
    private MyItemTouchHelperCallback mCallback;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_item);

        mRv_context = (RecyclerView) findViewById(R.id.rv_context);
        mRv_context.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < Cheeses.NAMES.length; i++) {
            mData.add(Cheeses.NAMES[i]);
        }

        mAdapter = new ItemTouchHelperAdapter(mData, this);
        mRv_context.setAdapter(mAdapter);

        mCallback = new MyItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRv_context);
    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
