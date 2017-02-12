package com.example.lqy.mvvm.base.viewModel.itemViewModel;

import android.content.Context;
import android.databinding.ObservableField;

import com.example.lqy.mvvm.R;

/**
 * Created by qiyao on 2017/2/6.
 */

public class SimpleLoadMoreViewModel implements IItemViewModel {
    //context
    private Context context;
    private final ObservableField<String> text = new ObservableField<>();


    public SimpleLoadMoreViewModel(Context context) {
        this.context = context;
        isLoading();
    }

    public void noMore() {
        text.set(getString(R.string.no_more));
    }

    public void isLoading() {
        text.set(getString(R.string.is_loading));
    }

    public void loadFailed() {
        text.set(getString(R.string.load_failed));
    }

    private String getString(int strRes) {
        return context.getString(R.string.no_more);
    }
}
