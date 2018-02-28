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
