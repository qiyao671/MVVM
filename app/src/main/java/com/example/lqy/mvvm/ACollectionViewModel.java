package com.example.lqy.mvvm;

import android.databinding.ObservableArrayList;

import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:53.
 * 类描述：
 */

public abstract class ACollectionViewModel implements IViewModel {

    //data for presenter
    public final ItemViewArg itemView = createItemView();


    //child viewModel
    public final ObservableArrayList<ItemViewModel> itemViewModels = new ObservableArrayList<>();


    public abstract ItemViewArg createItemView();

}
