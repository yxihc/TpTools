package com.taopao.rxjavaretrofitcutmvp.http.download;
/**
 * @Author: 淘跑
 * @Data: 2018/2/2 13:40
 * @Use:  下载进度listener
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
