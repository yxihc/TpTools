package com.taopao.rxjavaretrofitcutmvp.ui.activity.net;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.http.download.DownLoadRetrofit;
import com.taopao.rxjavaretrofitcutmvp.http.download.DownloadProgressListener;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.presenter.DownLoadFilePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.view.DownLoadFileView;
import com.taopao.rxjavaretrofitcutmvp.utils.StringUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * _________
 * /\        \
 * \_| Topic |
 * |       |
 * |   ____|_
 * \_/______/
 *
 * @Author: 淘跑
 * @Data: 2018/2/24 15:18
 * @Use:
 */

public class DownLoadFileActivity extends BaseActivity<DownLoadFilePresenter, DownLoadFileView> implements DownLoadFileView {


    private SeekBar cb;
    private File mFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);
        initView();

    }

    @Override
    public DownLoadFilePresenter createPresenter() {
        return new DownLoadFilePresenter(this);
    }

    @Override
    public DownLoadFileView createView() {
        return this;
    }

    @Override
    public void onNetChanged(int netWorkState) {

    }

    private String apkUrl="http://openbox.mobilem.360.cn/index/d/sid/3647654";

    //开始下载
    public void downloadFile(View view) {

        DownloadProgressListener listener = new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int progress = (int) ((bytesRead * 100) / contentLength);
                cb.setProgress(progress);
            }
        };
        mFile = new File(Environment.getExternalStorageDirectory() + "/TaoPaoSample", "updata.apk");
        if (mFile.exists()) {
            mFile.delete();
        }

        String baseUrl = StringUtils.getHostName(apkUrl);
        new DownLoadRetrofit(baseUrl, listener).downloadAPK(apkUrl, Environment.getExternalStorageDirectory() + "/TaoPaoSample/updata.apk", new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
//                downloadCompleted();
                Toast.makeText(getApplicationContext() , "下载失败!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(DownLoadFileActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        cb = (SeekBar) findViewById(R.id.cb);
    }
}
