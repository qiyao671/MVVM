package com.example.lqy.mvvm.list;

import android.app.Fragment;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.User;
import com.example.lqy.mvvm.base.viewModel.IItemViewModel;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:40.
 * 类描述：
 */

public class ViewModel extends ACollectionViewModel<User> {
    //context
    private Fragment fragment;

    //model

    //data for presenter
//    public final ItemView itemView = ItemView.of(BR.itemViewModel, R.layout.item_list);


    //child viewModel
//    public final ObservableArrayList<ItemViewModel> itemViewModels = new ObservableArrayList<>();

    //command


    public ViewModel(Fragment fragment) {
        super();
        this.fragment = fragment;

    }

    @Override
    public ViewBindingRes getItemRes() {
        return null;
    }

    @Override
    protected ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<User> items) {
        ArrayList<IItemViewModel> list = new ArrayList<>();
        for (User item : items) {
            list.add(new ItemViewModel(fragment.getActivity(), item));
        }
        return list;
    }

    @Override
    protected ArrayList<User> obtainDataSource() {
        ArrayList<User> list = new ArrayList<>();
//        list.add(new ItemViewModel(fragment.getActivity(), new User("name1", "pwd1")));
//        list.add(new ItemViewModel(fragment.getActivity(), new User("name2", "pwd2")));
//        list.add(new ItemViewModel(fragment.getActivity(), new User("name3", "pwd3")));
//        list.add(new ItemViewModel(fragment.getActivity(), new User("name4", "pwd4")));
        list.add(new User("name1", "pwd1"));
        list.add(new User("name2", "pwd2"));
        list.add(new User("name3", "pwd3"));
        list.add(new User("name4", "pwd4"));
        return list;
    }
}
