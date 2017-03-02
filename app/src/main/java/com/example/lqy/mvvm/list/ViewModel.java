package com.example.lqy.mvvm.list;

import android.app.Fragment;

import com.example.lqy.mvvm.base.other.ViewBindingRes;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.User;

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
//    public final ItemView itemBinding = ItemView.of(BR.itemViewModel, R.layout.item_list);


    //child viewModel
//    public final ObservableArrayList<ItemViewModel> itemViewModels = new ObservableArrayList<>();

    //command


    public ViewModel(Fragment fragment) {
        super(fragment);
        this.fragment = fragment;

    }


//    @Override
//    protected ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<User> items) {
//        ArrayList<IItemViewModel> list = new ArrayList<>();
//        for (User item : items) {
//            list.add(new ItemViewModel(fragment.getActivity(), item));
//        }
//        return list;
//    }

//    @Override
//    protected ArrayList<User> obtainDataSource(RefreshMode refreshMode) {
//        ArrayList<User> list = new ArrayList<>();
////        list.add(new ItemViewModel(fragment.getActivity(), new User("name1", "pwd1")));
////        list.add(new ItemViewModel(fragment.getActivity(), new User("name2", "pwd2")));
////        list.add(new ItemViewModel(fragment.getActivity(), new User("name3", "pwd3")));
////        list.add(new ItemViewModel(fragment.getActivity(), new User("name4", "pwd4")));
//        list.add(new User("name1", "pwd1"));
//        list.add(new User("name2", "pwd2"));
//        list.add(new User("name3", "pwd3"));
//        list.add(new User("name4", "pwd4"));
//        return list;
//    }

    @Override
    protected void requestData(RefreshMode refreshMode) {

    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return null;
    }

    @Override
    protected IItemViewModel newItemViewModel(User item) {
        return null;
    }
}
