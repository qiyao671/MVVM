package com.example.lqy.mvvm.net;

import com.example.lqy.mvvm.bean.DailyListBean;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/2/15.
 */

public class HttpMethods extends HttpFactory {
    private static HttpMethods INSTANCE;
    private ZhiHuApiService zhihuApiService = null;

    HttpMethods() {
        super();
        zhihuApiService = getApiService(ZhiHuApiService.HOST, ZhiHuApiService.class);

    }

    public static HttpMethods getInstance() {
        if (INSTANCE == null) {
            return new HttpMethods();
        } else {
            return INSTANCE;
        }
    }


    public Observable<List<DailyListBean.StoriesBean>> getDailyList() {
        return zhihuApiService.getDailyList()
                .map(DailyListBean::getStories);
    }
}
