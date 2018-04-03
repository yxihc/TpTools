package com.taopao.rxjavaretrofitcutmvp.ui.activity.net;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.http.download.DownLoadRetrofit;
import com.taopao.rxjavaretrofitcutmvp.http.download.DownloadProgressListener;
import com.taopao.rxjavaretrofitcutmvp.http.upload.ProgressUpLoadRequestBody;
import com.taopao.rxjavaretrofitcutmvp.http.upload.UploadProgressListener;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.UpLoadResult;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseMvpActivity;
import com.taopao.rxjavaretrofitcutmvp.ui.presenter.DownLoadFilePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.view.DownLoadFileView;
import com.taopao.rxjavaretrofitcutmvp.utils.StringUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.RxThreadFactory;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

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

public class DownLoadFileActivity extends BaseMvpActivity<DownLoadFilePresenter, DownLoadFileView> implements DownLoadFileView {

    private SeekBar cb;
    private SeekBar cb1;

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
//                downloadCompleted();
                Toast.makeText(getApplicationContext() , "下载失败!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(DownLoadFileActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //开始上传
    public void uploadFile(View view) {
        String filePath = Environment.getExternalStorageDirectory() + "/TaoPaoSample/h.png";
        File file=new File(filePath);
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
        MultipartBody.Part part= MultipartBody.Part.createFormData("file_name", file.getName(), new ProgressUpLoadRequestBody(requestBody,
                new UploadProgressListener() {
                    @Override
                    public void onProgress(long currentBytesCount, long totalBytesCount) {
                        cb1.setMax((int) totalBytesCount);
                        cb1.setProgress((int) currentBytesCount);
                    }
                }));

        ApiRetrofit.getInstance()
                .uploadImage(part)
                .compose(RxTransformer.<BaseResult<UpLoadResult>>switchSchedulers())
                .subscribe(new Consumer<BaseResult<UpLoadResult>>() {
                    @Override
                    public void accept(BaseResult<UpLoadResult> upLoadResultBaseResult) throws Exception {
                        Toast.makeText(DownLoadFileActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    protected void initView() {
        cb = (SeekBar) findViewById(R.id.cb);
        cb1 = (SeekBar) findViewById(R.id.cb1);
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initData() {

    }
}
