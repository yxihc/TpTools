package com.taopao.rxjavaretrofitcutmvp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.app.App;
import com.taopao.rxjavaretrofitcutmvp.http.Cooker.ClearableCookieJar;
import com.taopao.rxjavaretrofitcutmvp.http.Cooker.PersistentCookieJar;
import com.taopao.rxjavaretrofitcutmvp.http.Cooker.cache.SetCookieCache;
import com.taopao.rxjavaretrofitcutmvp.http.Cooker.persistence.SharedPrefsCookiePersistor;
import com.taopao.rxjavaretrofitcutmvp.utils.LogUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.NetUtils;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @Author: 淘跑
 * @Data: 2018/1/28 21:32
 * @Use:  配置Retrofit（配置网络缓存cache、配置持久cookie免登录）
 */

public class ApiRetrofit {
    private static ApiRetrofit mApiRetrofit;
    private static Api mApi;
    private static OkHttpClient mClient;
    public ApiRetrofit() {
        mApi = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(getClient())
                .addConverterFactory(buildGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    /**
     * 得到实例化的api
     * @return Api
     */
    public Api getApi(){
        return mApi;
    }
    public static Api getInstance() {
        //单利模式获取retrofit
        if (mApiRetrofit == null) {
            synchronized (ApiRetrofit.class) {
                mApiRetrofit=new ApiRetrofit();
            }
        }
        return mApiRetrofit.getApi();
    }

    /**
     * 实例化OkHttpClient
     * @return OkHttpClient
     */
    public static OkHttpClient getClient() {
        mClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true) // 自动重连
                .connectTimeout(15, TimeUnit.SECONDS) // 15秒连接超时
                .readTimeout(20, TimeUnit.SECONDS)    // 20秒读取超时
                .writeTimeout(20, TimeUnit.SECONDS)   // 20秒写入超时
                .addInterceptor(buildLoggingInterceptor())
                .addInterceptor(buildHeaderInterceptor())
                .addInterceptor(buildCacheInterceptor())
                .cache(getCache())
                .cookieJar(getCookie())
                .build();
        return mClient;
    }

    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    private static ClearableCookieJar getCookie() {
        //cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext()));
        return cookieJar;
    }


    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    private static Cache getCache() {
        //cache  获取缓存目标,SD卡
        File httpCacheDir = new File(App.getContext().getCacheDir(), UIUtils.getString(R.string.app_name));
        //最大缓存50m
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        // 创建缓存对象,
        return new Cache(httpCacheDir, cacheSize);
    }


    /**
     * 构建GSON转换器
     *
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();
        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }

    /**
     * 构建请求头拦截器
     *
     * @return Interceptor
     */
    private static Interceptor buildHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cookie", "add cookies here")
                        .build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 构建log拦截器
     *
     * @return Interceptor
     */
    private static Interceptor buildLoggingInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {


                //--------------------------------------第一种log输出方式------------------------------------//

//                //这个chain里面包含了request和response，所以你要什么都可以从这里拿
//                Request request = chain.request();
//                long t1 = System.nanoTime();//请求发起的时间
//                LogUtils.f("-----------------------------------Start-----------------------------------");
//                LogUtils.f(String.format("发送请求  %s %n%s%n%s",
//                        request.url(), "connection: " + chain.connection(), "headers: " + request.headers()));
//                Response response = chain.proceed(request);
//                long t2 = System.nanoTime();//收到响应的时间
//                //这里不能直接使用response.body().string()的方式输出日志
//                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
//                //个新的response给应用层处理
//                ResponseBody responseBody = response.peekBody(1024 * 1024);
//                LogUtils.f(String.format("接收响应: %s %n返回JSON: %s%n请求时长: %.1fms%n%s",
//                        response.request().url(),
//                        responseBody.string(),
//                        (t2 - t1) / 1e6d,
//                        response.headers()));
//                LogUtils.f("-----------------------------------End:"+(t2 - t1) / 1e6d+"毫秒--------------------------");
//                LogUtils.f(String.format("%s",responseBody.string()));




                //-------------------------------第二种log输出方式:格式化JSON 狂拽酷炫吊炸天------------------------------------//



                //这个chain里面包含了request和response，所以你要什么都可以从这里拿
                Request request = chain.request();
                long t1 = System.nanoTime();//请求发起的时间
                LogUtils.f("╔══════════════════════Start═════════════════════════════════════════════════════════════════════════════════════════════");
                LogUtils.f(String.format("║ 发送请求  %s %n║ %s%n║ %s",
                        request.url(), "connection: " + chain.connection(), "headers: " + request.headers()));
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();//收到响应的时间
                //这里不能直接使用response.body().string()的方式输出日志
                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
                //个新的response给应用层处理
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                String json = responseBody.string();
                LogUtils.f(String.format("║ 接收响应: %s %n║ 请求时长: %.1fms%n║ 返回JSON: %s%n",
                        response.request().url(),
                        (t2 - t1) / 1e6d,json
                        ));
//                LogUtils.printJson(json,response.headers().toString());
                LogUtils.logFormatJson(json,"");
                LogUtils.f("╚══════════════════════End:"+(t2 - t1) / 1e6d+"毫秒═════════════════════════════════════════════════════════════════════════════════");
                LogUtils.f(String.format("%s",responseBody.string()));



                return response;
            }
        };
    }

    /**
     * 构建缓存拦截器
     *
     * @return Interceptor
     */
    private static Interceptor buildCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //通过 CacheControl 控制缓存数据
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(365, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
                cacheBuilder.maxStale(365, TimeUnit.DAYS);//这个是控制缓存的过时时间
                CacheControl cacheControl = cacheBuilder.build();                // 响应内容处理
                Request request = chain.request();

                // 无网络连接时请求从缓存中读取
                if (!NetUtils.isNetworkAvailable(App.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                // 在线时缓存5分钟
                // 离线时缓存4周
                Response response = chain.proceed(request);
                if (NetUtils.isNetworkAvailable(App.getContext())) {
                    int maxAge = 300;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }


//                //通过 CacheControl 控制缓存数据
//                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//                cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
//                cacheBuilder.maxStale(365, TimeUnit.DAYS);//这个是控制缓存的过时时间
//                CacheControl cacheControl = cacheBuilder.build();
//
//                //设置拦截器
//                Request request = chain.request();
//                if (!NetUtils.isNetworkAvailable(BaseApp.getContext())) {
//                    request = request.newBuilder()
//                            .cacheControl(cacheControl)
//                            .build();
//                }
//
//                Response originalResponse = chain.proceed(request);
//                if (NetUtils.isNetworkAvailable(BaseApp.getContext())) {
//                    int maxAge = 0;//read from cache
//                    return originalResponse.newBuilder()
//                            .removeHeader("Pragma")
//                            .header("Cache-Control", "public ,max-age=" + maxAge)
//                            .build();
//                } else {
//                    int maxStale = 60 * 60 * 24 * 28;//tolerate 4-weeks stale
//                    return originalResponse.newBuilder()
//                            .removeHeader("Prama")
//                            .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
//                            .build();
//                }
//
//            }
        };
    }


}
