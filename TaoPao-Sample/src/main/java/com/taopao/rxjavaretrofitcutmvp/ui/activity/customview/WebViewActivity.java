package com.taopao.rxjavaretrofitcutmvp.ui.activity.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import com.taopao.rxjavaretrofitcutmvp.widget.ProgressWebView;

public class WebViewActivity extends BaseActivity {

    private ProgressWebView mWv;
    private boolean isLoading = false;
    private String mUrl = "";
    private String mTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getData();
        initWebView();
    }

    @Override
    public void setToolBar() {
        getToolBar().setNavigationIcon(null);
    }

    @Override
    public void onNetChanged(int netState) {

    }

    private void getData(){
        if (getIntent().hasExtra("mUrl")) {
            mUrl = getIntent().getExtras().getString("mUrl", "");
        }
        if (getIntent().hasExtra("mTitle")) {
            mTitle = getIntent().getExtras().getString("mTitle", "");
        }
    }
    private void initWebView() {
        mWv = (ProgressWebView) findViewById(R.id.wv);
        //设置webView
        WebSettings settings = mWv.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumLogicalFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        mWv.setVerticalScrollbarOverlay(true);
        mWv.setWebViewClient(new MyWebViewClient());
        mWv.loadUrl(mUrl);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在自己浏览器中跳转
            mWv.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isLoading = true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            isLoading = false;
        }
    }


    /**
     * 打开网页:加上title
     * @param mContext 上下文
     * @param mUrl     要加载的网页url
     * @param mTitle   title
     */
    public static void loadUrl(Context mContext, String mUrl, String mTitle) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("mUrl", mUrl);
        intent.putExtra("mTitle", mTitle);
        mContext.startActivity(intent);
    }
    /**
     * 打开网页:不加title
     * @param mContext 上下文
     * @param mUrl     要加载的网页url
     */
    public static void loadUrl(Context mContext, String mUrl) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("mUrl", mUrl);
        mContext.startActivity(intent);
    }
}