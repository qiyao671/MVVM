package com.example.lqy.mvvm.sectionedList;

import android.app.Fragment;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.other.IItemViewBindingCreator;
import com.example.lqy.mvvm.base.other.ViewBindingRes;
import com.example.lqy.mvvm.base.viewModel.ASectionCollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.User;
import com.example.lqy.mvvm.list.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemBinding;
import rx.Observable;


/**
 * Created by qiyao on 2017/2/3.
 */

class SectionViewModel extends ASectionCollectionViewModel<String, User> {

    //models
//    private String[] sections;
//    private HashMap<String, ArrayList<User>> sectionedUsers;

    //presenters for view
    SectionViewModel(Fragment fragment) {
        super(fragment);
    }

    @Override
    protected boolean isSectionHeader(int position, IItemViewModel itemViewModel) {
        return itemViewModel.getItemViewType().equals(ASectionCollectionViewModel.TYPE_SECTION_HEADER);
    }

    @Override
    protected String headerOfSection(int section) {
        return null;
    }

    @Override
    protected String headerForSectionOfItem(User user) {
        return String.valueOf(user.getName().charAt(0));
    }

    @Override
    protected ViewBindingRes getSectionItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_list, BR.itemViewModel);
    }

    @Override
    protected ViewBindingRes getSectionHeaderRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.section_header_sectioned_list, BR.sectionHeader);
    }

//    @Override
//    protected int numberOfSections() {
//        return sections.length;
//    }
//
//    @Override
//    protected int numberOfItemsInSection(int section) {
//        String header = sections[section];
//        return sectionedUsers.get(header).size();
//    }

//    @Override
//    protected IItemViewModel itemViewModelAtIndexInSection(int index, int section) {
//        String header = sections[section];
//        User user = sectionedUsers.get(header).get(index);
//        return new ItemViewModel(getContext(), user);
//    }
//
//    @Override
//    protected IItemViewModel headerViewModelOfSection(int section) {
//        return new SectionHeaderViewModel(getContext(), sections[section]);
//    }
//
//    @Override
//    protected void setupDataSource(ArrayList<User> users) {
//        sectionedUsers = groupItems(users);
//        //排序
//        ArrayList<String> headerList = new ArrayList<>(sectionedUsers.keySet());
//        sort(headerList);
//        sections = headerList.toArray(new String[headerList.size()]);
//    }


    @Override
    protected IItemViewModel newSectionHeaderItemViewModel(String header) {
        return new SectionHeaderViewModel(getFragment().getActivity(), header) ;
    }

    @Override
    protected int compareToHeaders(String s, String s2) {
        return sort(s, s2);
    }

    @Override
    protected int compareToItems(User h, User h2) {
        return sort(h.getName().substring(1), h2.getName().substring(1));
    }

    //按首字母从小到大排序
    private int sort(String s, String s2) {
        char c1 = s.charAt(0);
        char c2 = s2.charAt(0);
        if (c1 < c2) {
            return -1;
        } else if (c1 == c2) {
            return 0;
        } else {
            return 1;
        }
    }
/*
    @Override
    protected ArrayList<User> obtainDataSource(RefreshMode refreshMode) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("a123", "112"));
        users.add(new User("a333", "112"));
        users.add(new User("c233", "112"));
        users.add(new User("b123", "112"));
        users.add(new User("b1223", "112"));
        return users;
    }*/

    @Override
    protected IItemViewBindingCreator<Object> createHeaderViewBindingHelper() {
        return new IItemViewBindingCreator<Object>() {
            @Override
            public ViewBindingRes genViewBindingRes() {
                return new ViewBindingRes(R.layout.item_header, ItemBinding.VAR_NONE);
            }

            @Override
            public IItemViewModel genItemViewModel(Object item) {
                return null;
            }
        };
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new SectionTask(refreshMode).execute();
    }

    @Override
    protected IItemViewModel newItemViewModel(User item) {
        return new ItemViewModel(getFragment().getActivity(), item);
    }

    private class SectionTask extends ASectionTask {

        SectionTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected Observable<List<User>> getData(RefreshMode mode) {
            if (mode == RefreshMode.refresh || mode == RefreshMode.reset) {
                ArrayList<User> users = new ArrayList<>();
                users.add(new User("a123", "112"));
                users.add(new User("a333", "112"));
                users.add(new User("c233", "112"));
                users.add(new User("b123", "112"));
                users.add(new User("b1223", "112"));
                return Observable.just(users);
            } else {
                return Observable.just(null);
            }
        }

        @Override
        protected void handleNewItemViewModels(RefreshMode mode, List<IItemViewModel> newItemViewModels) {
            super.handleNewItemViewModels(mode, newItemViewModels);
            clearItemViewModels();
        }
    }
}
