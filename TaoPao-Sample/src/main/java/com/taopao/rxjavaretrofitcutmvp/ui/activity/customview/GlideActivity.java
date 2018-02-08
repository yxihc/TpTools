package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.taopao.rxjavaretrofitcutmvp.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView iv;
    private ImageView iv_2;
    private ImageView iv_3;
    private ImageView iv_4;
    private ImageView iv_5;
    private ImageView iv_6;
    private ImageView iv_7;
    private ImageView iv_8;
    private ImageView iv_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initView();

        initImage();
    }

    private void initImage() {

        //加载动态图
        String gifUrl="http://photocdn.sohu.com/20150821/mp28610278_1440132843784_4.gif";

        Glide.with(this)
                .load(gifUrl)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//必须设置这个缓存 不然出问题(不显示动态图)
                .into(iv);

        final ObjectAnimator anim = ObjectAnimator.ofInt(iv_2, "ImageLevel", 0, 10000);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();

        Glide.with(this)
                .load(gifUrl)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//必须设置这个缓存 不然出问题(不显示动态图)
                .placeholder(R.drawable.loading_rotate)
                .crossFade()
                .listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        anim.cancel();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        anim.cancel();
                        return false;
                    }
                })
                .into(iv_2);
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
        iv_4 = (ImageView) findViewById(R.id.iv_4);
        iv_5 = (ImageView) findViewById(R.id.iv_5);
        iv_6 = (ImageView) findViewById(R.id.iv_6);
        iv_7 = (ImageView) findViewById(R.id.iv_7);
        iv_8 = (ImageView) findViewById(R.id.iv_8);
        iv_9 = (ImageView) findViewById(R.id.iv_9);
    }
}
