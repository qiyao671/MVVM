package com.example.lqy.mvvm.net;


import com.example.lqy.mvvm.bean.DailyListBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by qiyao on 2017/2/15.
 */

public interface ZhiHuApiService {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */

    @GET("news/latest")
    Observable<DailyListBean> getDailyList();
}
