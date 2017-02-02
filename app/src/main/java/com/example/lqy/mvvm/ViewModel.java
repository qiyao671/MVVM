package com.example.lqy.mvvm;

import android.app.Fragment;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:40.
 * 类描述：
 */

public class ViewModel extends ACollectionViewModel {
    //context
    private Fragment fragment;

    //model

    //data for presenter
//    public final ItemView itemView = ItemView.of(BR.itemViewModel, R.layout.item_list);


    //child viewModel
//    public final ObservableArrayList<ItemViewModel> itemViewModels = new ObservableArrayList<>();

    //command


    public ViewModel(Fragment fragment) {
        this.fragment = fragment;
        itemViewModels.add(new ItemViewModel(fragment.getActivity(), new User("name1", "pwd1")));
        itemViewModels.add(new ItemViewModel(fragment.getActivity(), new User("name2", "pwd2")));
        itemViewModels.add(new ItemViewModel(fragment.getActivity(), new User("name3", "pwd3")));
        itemViewModels.add(new ItemViewModel(fragment.getActivity(), new User("name4", "pwd4")));
    }

    @Override
    public ItemViewArg createItemView() {
        ItemView itemView = ItemView.of(BR.itemViewModel, R.layout.item_list);
        return ItemViewArg.of(itemView);
    }
}
