package com.example.lqy.mvvm.sectionedList;

import android.app.Fragment;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.bean.User;
import com.example.lqy.mvvm.base.other.IItemViewBindingCreator;
import com.example.lqy.mvvm.base.viewModel.ASectionCollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.list.ItemViewModel;
import com.example.lqy.mvvm.base.other.ViewBindingRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import me.tatarka.bindingcollectionadapter.ItemBinding;


/**
 * Created by qiyao on 2017/2/3.
 */

class SectionViewModel extends ASectionCollectionViewModel<String, User> {
    private Fragment fragment;

    //models
    private String[] sections;
    private HashMap<String, ArrayList<User>> sectionedUsers;

    //presenters for view
    SectionViewModel(Fragment fragment) {
        super(fragment.getActivity(), new ViewBindingRes(R.layout.section_header_sectioned_list, BR.sectionHeader));
        this.fragment = fragment;
    }

    @Override
    protected boolean isSectionHeader(int position, IItemViewModel itemViewModel) {
        return itemViewModel instanceof SectionHeaderViewModel;
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
    protected int numberOfSections() {
        return sections.length;
    }

    @Override
    protected int numberOfItemsInSection(int section) {
        String header = sections[section];
        return sectionedUsers.get(header).size();
    }

    @Override
    protected IItemViewModel itemViewModelAtIndexInSection(int index, int section) {
        String header = sections[section];
        User user = sectionedUsers.get(header).get(index);
        return new ItemViewModel(getContext(), user);
    }

    @Override
    protected IItemViewModel headerViewModelOfSection(int section) {
        return new SectionHeaderViewModel(getContext(), sections[section]);
    }

    @Override
    protected void setupDataSource(ArrayList<User> users) {
        sectionedUsers = groupItems(users);
        //排序
        ArrayList<String> headerList = new ArrayList<>(sectionedUsers.keySet());
        sort(headerList);
        sections = headerList.toArray(new String[headerList.size()]);
    }

    @Override
    protected ViewBindingRes getSectionItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_list, BR.itemViewModel);
    }

    //按首字母从小到大排序
    private void sort(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char c1 = o1.charAt(0);
                char c2 = o2.charAt(0);
                if (c1 < c2) {
                    return -1;
                } else if (c1 == c2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
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

    }

    @Override
    protected IItemViewModel newItemViewModel(User item) {
        return null;
    }
}
