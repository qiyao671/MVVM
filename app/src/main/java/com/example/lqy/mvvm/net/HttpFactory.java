package com.example.lqy.mvvm.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/22 21:57.
 * 类描述：
 */

public class HttpFactory {
    private OkHttpClient okHttpClient = null;

    private void init() {
        initOkHttp();
    }

    HttpFactory() {
        init();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (BuildConfig.DEBUG) {
//            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            builder.addInterceptor(loggingInterceptor);
//        }
//        // 缓存 http://www.jianshu.com/p/93153b34310e
//        File cacheFile = new File(BaseApplication.getAppCacheDir(), "/NetCache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//        Interceptor cacheInterceptor = chain -> {
//            Request request = chain.request();
//            if (!Util.isNetworkConnected(BaseApplication.getAppContext())) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//            }
//            Response response = chain.proceed(request);
//            Response.Builder newBuilder = response.newBuilder();
//            if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
//                int maxAge = 0;
//                // 有网络时 设置缓存超时时间0个小时
//                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
//            } else {
//                // 无网络时，设置超时为4周
//                int maxStale = 60 * 60 * 24 * 28;
//                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
//            }
//            return newBuilder.build();
//        };
//        builder.cache(cache).addInterceptor(cacheInterceptor);
//        //设置超时
//        builder.connectTimeout(15, TimeUnit.SECONDS);
//        builder.readTimeout(20, TimeUnit.SECONDS);
//        builder.writeTimeout(20, TimeUnit.SECONDS);
//        //错误重连
//        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    protected <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    protected <T> Observable<T> handleResult(Observable<HttpResult<T>> httpResult) {
        return httpResult.map(new HttpResultFunc<>());
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (!httpResult.isSuccess()) {
//                throw new ApiException(httpResult.getResultCode());
                throw new RuntimeException("error");
            }
            return httpResult.getData();
        }
    }
}
