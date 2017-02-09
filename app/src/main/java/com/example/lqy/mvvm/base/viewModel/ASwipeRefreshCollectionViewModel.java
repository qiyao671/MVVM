package com.example.lqy.mvvm.base.viewModel;

import android.app.Fragment;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.lqy.mvvm.list.ViewBindingRes;

import java.util.ArrayList;

/**
 * Created by qiyao on 2017/2/4.
 */

public abstract class ASwipeRefreshCollectionViewModel<T> extends ACollectionViewModel<T> {
    //model

    //presenter for view
    public final ObservableField<SwipeRefreshLayout.OnRefreshListener> onRefreshListener = new ObservableField<>();

    //style
    public final ViewStyle viewStyle = new ViewStyle();

    public static class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
    }

    public ASwipeRefreshCollectionViewModel(Context context) {
        super(context);
        onRefreshListener.set(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewStyle.isRefreshing.set(true);
                onSwipeRefresh();
            }
        });
    }

    protected abstract void onSwipeRefresh();

}
