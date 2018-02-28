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
