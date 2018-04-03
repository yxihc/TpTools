package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;
import com.taopao.rxjavaretrofitcutmvp.widget.randomtext.ShakeListener;
import com.taopao.rxjavaretrofitcutmvp.widget.randomtext.StellarMap;

import java.util.ArrayList;
import java.util.Random;

public class RandomTextActivity extends BaseMvpActivity {
    private FrameLayout fl_root;
    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_text);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        data = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            data.add("我随心你随意" + i);
        }
        fl_root = (FrameLayout) findViewById(R.id.fl_root);

        final StellarMap stellar = new StellarMap(UIUtils.getContext());
        stellar.setAdapter(new RecommendAdapter());

        // 随机方式, 将控件划分为9行6列的的格子, 然后在格子中随机展示
        stellar.setRegularity(6, 9);

        // 设置内边距10dp
        int padding = UIUtils.dip2px(10);
        stellar.setInnerPadding(padding, padding, padding, padding);

        // 设置默认页面, 第一组数据
        stellar.setGroup(0, true);

        ShakeListener shake = new ShakeListener(UIUtils.getContext());

        // 摇晃进入下一页
        shake.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellar.zoomIn();// 跳到下一页数据
            }
        });

        fl_root.addView(stellar);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setContentView() {

    }


    @Override
    protected void initMvpData() {

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
        switch (netState) {
            case  NetUtils.NETWORK_NONE:
                Toast.makeText(RandomTextActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                break;
            case  NetUtils.NETWORK_MOBILE:
                Toast.makeText(RandomTextActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                break;
            case  NetUtils.NETWORK_WIFI:
                Toast.makeText(RandomTextActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class RecommendAdapter implements StellarMap.Adapter {

        // 返回组的个数
        @Override
        public int getGroupCount() {
            return 5;
        }

        // 返回某组的item个数
        @Override
        public int getCount(int group) {
            int count = data.size() / getGroupCount();
            if (group == getGroupCount() - 1) {
                // 最后一页, 将除不尽,余下来的数量追加在最后一页, 保证数据完整不丢失
                count += data.size() % getGroupCount();
            }
            return count;
        }

        // 初始化布局
        @Override
        public View getView(int group, int position, View convertView) {
            // 因为position每组都会从0开始计数, 所以需要将前面几组数据的个数加起来,才能确定当前组获取数据的角标位置
            position += (group) * getCount(group - 1);

            // System.out.println("pos:" + position);

            final String keyword = data.get(position);

            TextView view = new TextView(UIUtils.getContext());
            view.setText(keyword);

            Random random = new Random();
            // 随机大小, 16-25
            int size = 16 + random.nextInt(10);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

            // 随机颜色
            // r g b, 0-255 -> 30-230, 颜色值不能太小或太大, 从而避免整体颜色过亮或者过暗
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);

            view.setTextColor(Color.rgb(r, g, b));

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), keyword,
                            Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

        // 返回下一组的id
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            System.out.println("isZoomIn:" + isZoomIn);
            if (isZoomIn) {
                // 往下滑加载上一页
                if (group > 0) {
                    group--;
                } else {
                    // 跳到最后一页
                    group = getGroupCount() - 1;
                }
            } else {
                // 往上滑加载下一页
                if (group < getGroupCount() - 1) {
                    group++;
                } else {
                    // 跳到第一页
                    group = 0;
                }
            }
            return group;
        }

    }



}
