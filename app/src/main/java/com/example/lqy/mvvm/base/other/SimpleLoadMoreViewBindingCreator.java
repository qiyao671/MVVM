package com.example.lqy.mvvm.base.other;

import android.content.Context;

import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.SimpleLoadMoreViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/10 21:46.
 * 类描述：
 */

public class SimpleLoadMoreViewBindingCreator implements IItemViewBindingCreator<Object> {
    private Context context;
    public SimpleLoadMoreViewBindingCreator(Context context) {
        this.context = context;
    }

    @Override
    public ViewBindingRes genViewBindingRes() {
        return new ViewBindingRes(R.layout.item_simple_load_more, com.example.lqy.mvvm.BR.viewModel);
    }

    @Override
    public SimpleLoadMoreViewModel genItemViewModel(Object item) {
        return new SimpleLoadMoreViewModel(context);
    }

}
