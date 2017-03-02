package com.example.lqy.mvvm.zhihu;

import com.example.lqy.mvvm.base.fragment.ARecyclerViewFragment;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/15 22:20.
 * 类描述：
 */

public class DailyListFragment extends ARecyclerViewFragment {
    @Override
    protected ACollectionViewModel createViewModel() {
        return new DailyListViewModel(this);
    }
}
