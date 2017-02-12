package com.example.lqy.mvvm.base.viewModel.itemViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/10 22:18.
 * 类描述：
 */

public class StaticItemViewModel implements IItemViewModel {
    private int type;
    public StaticItemViewModel(int type) {
        this.type = type;
    }

    public static final int TYPE_HEADER = 1000;
    public static final int TYPE_FOOTER = 1001;
    public static final int TYPE_LOAD_MORE = 1002;
    public static final int TYPE_EMPTY = 1003;

    @Override
    public int getItemViewType() {
        return type;
    }
}
