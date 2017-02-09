package com.example.lqy.mvvm.base.bindingAdapter;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by qiyao on 2017/2/4.
 */

public class SwipeRefreshLayoutBindingAdapters {
    @BindingAdapter(value = "onRefresh")
    public void onRefresh(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        if (listener != null) {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setOnRefreshListener(listener);
        } else {
            swipeRefreshLayout.setEnabled(false);
        }
    }

    @BindingAdapter(value = "refreshing")
    public void refreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
