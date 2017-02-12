package com.example.lqy.mvvm.base.other;

import com.android.annotations.NonNull;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;

/**
 * Created by qiyao on 2017/2/4.
 */

public interface IItemViewBindingCreator<T> {
    @NonNull ViewBindingRes genViewBindingRes();


    @NonNull IItemViewModel genItemViewModel(T item);
}
