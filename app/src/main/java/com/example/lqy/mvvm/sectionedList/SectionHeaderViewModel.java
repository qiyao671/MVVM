package com.example.lqy.mvvm.sectionedList;

import android.content.Context;
import android.databinding.ObservableField;

import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;

/**
 * Created by qiyao on 2017/2/3.
 */

public class SectionHeaderViewModel implements IItemViewModel {
    //context
    private Context context;

    //model
    private String titleModel;

    //presenter for view
    public ObservableField<String> title = new ObservableField<>();

    public SectionHeaderViewModel(Context context, String titleModel) {
        this.context = context;
        this.titleModel = titleModel;
        this.title.set(titleModel);
    }

    @Override
    public int getItemViewType() {
        return 0;
    }
}
