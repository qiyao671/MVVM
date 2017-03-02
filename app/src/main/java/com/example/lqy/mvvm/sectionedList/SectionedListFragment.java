package com.example.lqy.mvvm.sectionedList;

import com.example.lqy.mvvm.base.fragment.ARecyclerViewFragment;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;

/**
 * Created by qiyao on 2017/2/3.
 */

public class SectionedListFragment extends ARecyclerViewFragment {
    @Override
    protected ACollectionViewModel createViewModel() {
        return new SectionViewModel(this);
    }
}
