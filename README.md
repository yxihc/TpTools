# RxJava-Retrofit-CutMvp
RxJava+Retrofit+CutMvp框架实践
# 带进度条下载
~~~
apkUrl="http://openbox.mobilem.360.cn/index/d/sid/3647654";
        DownloadProgressListener listener = new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int progress = (int) ((bytesRead * 100) / contentLength);
                cb.setProgress(progress);
            }
        };
        String baseUrl = StringUtils.getHostName(apkUrl);
        String filePath = Environment.getExternalStorageDirectory() + "/TaoPaoSample/updata.apk";
        new DownLoadRetrofit(baseUrl, listener).downloadAPK(apkUrl, filePath, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext() , "下载失败!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(DownLoadFileActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }
        });

~~~
# Log输出方式
~~~
╔══════════════════════Start═════════════════════════════════════════════════════════════════════════════════════════════
║ 发送请求  http://116.62.161.77/mkm/api/common/focus?location=2 
║ connection: null
║ headers:  ║ 接收响应: http://116.62.161.77/mkm/api/common/focus?location=2 
║ 请求时长: 151.4ms
║ 返回JSON: {"data":[{"id":2,"img":"f87771e0fd2c4e40b46f2c5feab9d719","redirectContent":"","redirectType":0}],"errorCode":0,"errorMsg":""}
║════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
║ 
║ {
║     "data": [
║         {
║             "id": 2,
║             "img": "f87771e0fd2c4e40b46f2c5feab9d719",
║             "redirectContent": "",
║             "redirectType": 0
║         }
║     ],
║     "errorCode": 0,
║     "errorMsg": ""
║ }
║════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
╚══════════════════════End:151.431771毫秒═════════════════════════════════════════════════════════════════════════════════
~~~

# 仿京东到家下拉刷新
公司的项目需要用到类似京东到家的下拉刷新,闲来无聊的时候研究一下,框架用的是 [SmartRefreshLayout](https://link.jianshu.com/?t=https%3A%2F%2Fgithub.com%2Fscwang90%2FSmartRefreshLayout)

先上图(录屏软件有点卡): 

![仿京东到家下拉刷新](https://upload-images.jianshu.io/upload_images/10151120-9adb8915e0c0ad3a.gif?imageMogr2/auto-orient/strip)

# 一、分析

京东到家的下拉加载动画初看挺简单的，一共有三个状态。
* 1、刚开始下拉的时候，小车从小变大的过程。
* 2、下拉到一定程度松手,小车开始显示不停抖动的动画
* 3、刷新结束的前400毫秒，小车要从左往右移动出去。

# 二、反编译App看实现原理
 反编译虽然源码看不太清,但是我们可以拿到他用到的图片资源
![图片动画资源](https://upload-images.jianshu.io/upload_images/10151120-29e963262c3b0c5b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

看到图片后知道原来它用的是最普通的帧动画啊，也不是太复杂。
拿到资源图片，知道实现原理，就开工吧！
# 三、实现动画效果
首先自定义View ``` MyRefreshHeader ```继承自 ```LinearLayout```，并实现 ```SmartRefreshLayout``` 的 ```RefreshHeader``` 接口。
然后主要就是重写  ```RefreshHeader``` 接口中的方法，里面提供了下拉刷新时不同阶段的回调，找到对应的方法码代码就好。

逻辑主要在 ```onStateChanged()``` 和 ```onPullingDown()``` 方法里，代码中注释写的很详细。
切换状态原理是每次都给 ```ImageView``` 设置对应的资源图片或动画文件，然后得到 ```AnimationDrawable``` 开启动画
##### 需要注意的是:当我们刷新完成的时候,需要延时400毫秒关闭刷新界面来显示小车平移的动画,这就需要在```onFinish```方法的返回值``` return 400;//延时400毫秒关闭下拉刷新```,由于我们使用的是补间动画,平移动画结束的时候还会回到最初的位置,为了不让用户看到,我们设置平移动画的时长比刷新界面关闭的时长多```100毫秒```,这样用户就看不到这个变化,优化用户体验(京东到家就有此问题,不得不吐槽一下,大厂也不长点心啊)

#代码如下:
```
/**
 * @Author：淘跑
 * @Date: 2018/4/1 09:35
 * @Use： SmartRefreshLayout 的自定义下拉刷新 Header
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public class MyRefreshHeader extends LinearLayout implements RefreshHeader {
    private ImageView mImage;
    private AnimationDrawable refreshingAnim;
    private float mCurTranslationX;
    public MyRefreshHeader(Context context) {
        this(context, null, 0);
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.widget_my_refresh_header, this);
        mImage = (ImageView) view.findViewById(R.id.iv_refresh_header);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    /**
     * 状态改变时调用。
     * @param refreshLayout
     * @param oldState
     * @param newState
     */
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh: //下拉刷新开始。正在下拉还没松手时调用
                //每次重新下拉时，将图片资源重置为小车
                mImage.setImageResource(R.drawable.pull_to_refresh_people_0);
                break;
            case Refreshing: //正在刷新。只调用一次
                //状态切换为正在刷新状态时，设置图片资源为小车的动画并开始执行
                mImage.setImageResource(R.drawable.anim_mypull_refreshing);
                refreshingAnim = (AnimationDrawable) mImage.getDrawable();
                refreshingAnim.start();
                break;
            case RefreshFinish://刷新结束
                //刷新结束时调用该动画
                mCurTranslationX = mImage.getTranslationX();
                Animation translateAnimation = new TranslateAnimation(mCurTranslationX,1000,0,0);
                translateAnimation.setDuration(500);
                mImage.startAnimation(translateAnimation);
                break;
        }
    }

    /**
     * 动画结束后调用
     */
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        // 结束动画
        if (refreshingAnim != null && refreshingAnim.isRunning()) {
            refreshingAnim.stop();
        }
        return 400;//延时400毫秒关闭下拉刷新
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    /**
     * 下拉过程中不断调用此方法。第一阶段从小变大的小人头动画，和第二阶段翻跟头动画都在这里设置
     */

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        // 下拉的百分比小于100%时，不断调用 setScale 方法改变图片大小
        if (percent < 1) {
            mImage.setScaleX(percent);
            mImage.setScaleY(percent);
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


}

```
贴出资源布局文件：

```activity_jdrefresh```
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:fitsSystemWindows="true"
    android:layout_height="match_parent"
    tools:context="com.taopao.rxjavaretrofitcutmvp.ui.activity.customview.refresh.JDRefreshActivity">
    >
    <com.scwang.smartrefresh.layout.SmartRefreshLayout
        android:id="@+id/smart_mian"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.taopao.rxjavaretrofitcutmvp.widget.refresh.MyRefreshHeader
            android:layout_width="match_parent"
            android:layout_height="wrap_content"></com.taopao.rxjavaretrofitcutmvp.widget.refresh.MyRefreshHeader>

        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。诚宜开张圣听，以光先帝遗德，恢弘志士之气，不宜妄自菲薄，引喻失义，以塞忠谏之路也。
宫中府中，俱为一体，陟罚臧否，不宜异同。若有作奸犯科及为忠善者，宜付有司论其刑赏，以昭陛下平明之理，不宜偏私，使内外异法也。
侍中、侍郎郭攸之、费祎、董允等，此皆良实，志虑忠纯，是以先帝简拔以遗陛下。愚以为宫中之事，事无大小，悉以咨之，然后施行，必能裨补阙漏，有所广益。"
                android:textSize="30dp" />
        </ScrollView>
    </com.scwang.smartrefresh.layout.SmartRefreshLayout>
</LinearLayout>
```

```widget_my_refresh_header```
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/white"
    android:padding="5dp">

    <ImageView
        android:layout_marginLeft="10dp"
        android:id="@+id/iv_refresh_header"
        android:layout_width="90dp"
        android:layout_height="80dp"
        android:scaleX="0"
        android:scaleY="0"
        android:translationY="0dp" />
    <ImageView
        android:layout_centerInParent="true"
        android:src="@drawable/pull_to_refresh_text_jddj"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
         />
</RelativeLayout>
```
```anim_mypull_refreshing```
```
<?xml version="1.0" encoding="utf-8"?>
<animation-list android:oneshot="false"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_0" />
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_1" />
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_2" />
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_3" />
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_4" />
    <item android:duration="50" android:drawable="@drawable/pull_to_refresh_people_5" />
</animation-list>
```
代码中调用
```
 private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSmartMian.finishRefresh();
        }
  };
  //主要代码
  mSmartMian.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //模拟加载过程,延时关闭刷新
                mHandler.sendEmptyMessageDelayed(10, 1300);
            }
   });
```

好啦，以上就是仿京东到家下拉刷新自定义动画的实现过程。
