package com.example.lqy.mvvm;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:47.
 * 类描述：
 */

public class ListFragment extends ARecyclerViewFragment {
    @Override
    protected ACollectionViewModel createViewModel() {
        return new ViewModel(this);
    }
}
