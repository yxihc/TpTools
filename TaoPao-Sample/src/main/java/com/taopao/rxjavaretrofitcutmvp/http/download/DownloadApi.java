package com.taopao.rxjavaretrofitcutmvp.http.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: 淘跑
 * @Data: 2018/2/2 14:20
 * @Use:
 */
public interface DownloadApi {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
