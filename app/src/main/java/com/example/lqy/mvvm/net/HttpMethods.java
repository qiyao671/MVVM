package com.example.lqy.mvvm.net;

import com.example.lqy.mvvm.BuildConfig;
import com.example.lqy.mvvm.bean.DailyListBean;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by qiyao on 2017/2/15.
 */

public class HttpMethods {
    private OkHttpClient okHttpClient = null;
    private ZhiHuApiService zhihuApiService = null;
    private static HttpMethods INSTANCE;

    private void init() {
//        initOkHttp();
        zhihuApiService = getApiService(ZhiHuApiService.HOST, ZhiHuApiService.class);
    }

    public HttpMethods() {
        init();
    }

//    private static void initOkHttp() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
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
//        sOkHttpClient = builder.build();
//    }

    public static HttpMethods getInstance() {
        if (INSTANCE == null) {
            return new HttpMethods();
        } else {
            return INSTANCE;
        }
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    public Observable<List<DailyListBean.StoriesBean>> getDailyList() {
        return zhihuApiService.getDailyList()
                .map(new Func1<DailyListBean, List<DailyListBean.StoriesBean>>() {
                    @Override
                    public List<DailyListBean.StoriesBean> call(DailyListBean dailyListBean) {
                        return null;
                    }
                });
    }
}
