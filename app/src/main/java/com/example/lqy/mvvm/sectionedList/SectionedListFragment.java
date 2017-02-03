package com.example.lqy.mvvm.sectionedList;

import com.example.lqy.mvvm.User;
import com.example.lqy.mvvm.base.fragment.ARecyclerViewFragment;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;

import java.util.ArrayList;

/**
 * Created by qiyao on 2017/2/3.
 */

public class SectionedListFragment extends ARecyclerViewFragment {
    @Override
    protected ACollectionViewModel createViewModel() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("a123", "112"));
        users.add(new User("a333", "112"));
        users.add(new User("c233", "112"));
        users.add(new User("b123", "112"));
        users.add(new User("b1223", "112"));
        return new SectionViewModel(this, users);
    }
}
