package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;

import com.example.lqy.mvvm.list.ViewBindingRes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import me.tatarka.bindingcollectionadapter.ItemBinding;

/**
 * Created by qiyao on 2017/2/3.
 */

public abstract class ASectionCollectionViewModel<H, T> extends ACollectionViewModel<T> {
    private ViewBindingRes sectionHeaderRes;

    public ASectionCollectionViewModel(Context context,  ViewBindingRes sectionHeaderRes) {
        super(context);
        this.sectionHeaderRes = sectionHeaderRes;
    }

    protected abstract boolean isSectionHeader(int position, IItemViewModel itemViewModel);

    // FIXME: 2017/2/3 是否需要这个函数
    protected abstract H headerOfSection(int section);

    //获得item所在section的header
    protected abstract H headerForSectionOfItem(T item);

    //获得Section的数量
    protected abstract int numberOfSections();

    //获得某section中item的数量
    protected abstract int numberOfItemsInSection(int section);

    //获得某section中第index个item的ViewModel
    protected abstract IItemViewModel itemViewModelAtIndexInSection(int index, int section);

    //获得某section中header的ViewModel
    protected abstract IItemViewModel headerViewModelOfSection(int section);

    //设置数据源
    protected abstract void setupDataSource(ArrayList<T> items);

    //设置item的layout和对应的binding variable
    protected abstract ViewBindingRes getSectionItemRes(int position, IItemViewModel item);

    //对items进行分组
    protected HashMap<H, ArrayList<T>> groupItems(Collection<T> items) {
        HashMap<H, ArrayList<T>> sectionedItems = new HashMap<>();
        for (T item : items) {
            H header = headerForSectionOfItem(item);
            if (sectionedItems.containsKey(header)) {
                sectionedItems.get(header).add(item);
            } else {
                ArrayList<T> list = new ArrayList<>();
                list.add(item);
                sectionedItems.put(header, list);
            }
        }
        return sectionedItems;
    }

    @Override
    protected ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<T> items) {
        setupDataSource(items);
        ArrayList<IItemViewModel> itemViewModels = new ArrayList<>();
        for (int section = 0; section < numberOfSections(); section++) {
            itemViewModels.add(headerViewModelOfSection(section));
            for (int index = 0; index < numberOfItemsInSection(section); index++) {
                itemViewModels.add(itemViewModelAtIndexInSection(index, section));
            }
        }
        return itemViewModels;
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        if (isSectionHeader(position, item)) {
            return sectionHeaderRes;
        } else {
            return getItemRes(position, item);
        }
    }
}
