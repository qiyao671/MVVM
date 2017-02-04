package com.example.lqy.mvvm.base;

import com.android.annotations.NonNull;
import com.example.lqy.mvvm.base.viewModel.IItemViewModel;
import com.example.lqy.mvvm.list.ViewBindingRes;

/**
 * Created by qiyao on 2017/2/4.
 */

public interface IItemViewBindingCreator {
    @NonNull
    ViewBindingRes genViewBindingRes();

    @NonNull
    IItemViewModel genItemViewModel();
}
