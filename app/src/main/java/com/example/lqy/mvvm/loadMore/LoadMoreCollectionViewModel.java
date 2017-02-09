package com.example.lqy.mvvm.loadMore;

import android.app.Fragment;

import com.example.lqy.mvvm.User;
import com.example.lqy.mvvm.base.viewModel.ALoadMoreCollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.IItemViewModel;
import com.example.lqy.mvvm.list.ItemViewModel;
import com.example.lqy.mvvm.list.ViewBindingRes;

import java.util.ArrayList;

/**
 * Created by qiyao on 2017/2/6.
 */

public class LoadMoreCollectionViewModel extends ALoadMoreCollectionViewModel<User> {
    private Fragment fragment;

    public LoadMoreCollectionViewModel(Fragment fragment) {
        super(fragment.getActivity());
        this.fragment = fragment;
    }

    @Override
    protected void onSwipeRefresh() {
        requestData(RefreshMode.update);
    }

    protected ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<User> items) {
        ArrayList<IItemViewModel> itemViewModels = new ArrayList<>();
        for (User item : items) {
            itemViewModels.add(new ItemViewModel(getContext(), item));
        }
        return itemViewModels;
    }

    @Override
    protected ArrayList<User> obtainDataSource(RefreshMode refreshMode) {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(new User("aaa", "123"));
        userArrayList.add(new User("aaa", "123"));
        userArrayList.add(new User("aaa", "123"));
        userArrayList.add(new User("aaa", "123"));
        return userArrayList;
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return null;
    }

    @Override
    public void onLoadMore() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("swipe", "pwd"));
        addListAtFootOfItemViewModels(generateItemViewModelList(list));
    }
}
