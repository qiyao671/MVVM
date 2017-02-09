package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;
import android.databinding.ObservableBoolean;

import com.example.lqy.mvvm.R;

/**
 * Created by qiyao on 2017/2/6.
 */

public class LoadMoreViewModel implements IItemViewModel {
    //context
    private Context context;

    private ObservableBoolean isEnd;


    public LoadMoreViewModel(Context context, ObservableBoolean isEnd) {
        this.context = context;
        this.isEnd = isEnd;
    }

    public String text() {
        return context.getString(isEnd.get() ? R.string.no_more : R.string.is_loading);
    }
}
