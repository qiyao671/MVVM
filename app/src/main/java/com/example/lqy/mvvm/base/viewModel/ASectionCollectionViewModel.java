package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;

import com.example.lqy.mvvm.base.other.ViewBindingRes;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/2/3.
 */

public abstract class ASectionCollectionViewModel<H, T> extends ACollectionViewModel<T> {
    public static final String TYPE_SECTION_HEADER = "TYPE_SECTION_HEADER";
    private ViewBindingRes sectionHeaderRes;
    private HashMap<H, List<T>> sectionedItems = new HashMap<>();

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
//
//    //获得某section中第index个item的ViewModel
//    protected abstract IItemViewModel itemViewModelAtIndexInSection(int index, int section);
//
//    //获得某section中header的ViewModel
//    protected abstract IItemViewModel headerViewModelOfSection(int section);

//    //设置数据源
//    protected abstract void setupDataSource(ArrayList<T> items);

    //设置item的layout和对应的binding variable
    protected abstract ViewBindingRes getSectionItemRes(int position, IItemViewModel item);

    //创建sectionHeader的ItemViewModel
    protected abstract IItemViewModel newSectionHeaderItemViewModel(H header);

    protected abstract int compareToHeaders(H h, H h2);

    protected abstract int compareToItems(T h, T h2);

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        if (isSectionHeader(position, item)) {
            return sectionHeaderRes;
        } else {
            return getItemRes(position, item);
        }
    }

    public HashMap<H, List<T>> getSectionedItems() {
        return sectionedItems;
    }

    /*    //对items进行分组
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
    }*/

    /*protected ArrayList<IItemViewModel> generateItemViewModelList() {
        ArrayList<IItemViewModel> itemViewModels = new ArrayList<>();
        for (int section = 0; section < numberOfSections(); section++) {
            itemViewModels.add(headerViewModelOfSection(section));
            for (int index = 0; index < numberOfItemsInSection(section); index++) {
                itemViewModels.add(itemViewModelAtIndexInSection(index, section));
            }
        }
        return itemViewModels;
    }*/

    /**
     * 第一种情况：可以添加数据，默认每次加载的必定是整个section且section的header不会重复
     * 第二种情况：不可添加数据，请override updateItemViewModels函数
     */
    protected abstract class ASectionTask extends APagingTask {
        private List<IItemViewModel> newItemViewModels;

        public ASectionTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        public void execute() {
            getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .flatMap(Observable::from)
                    .groupBy(ASectionCollectionViewModel.this::headerForSectionOfItem)
                    .toSortedList(this::compareTo)
                    .flatMap(Observable::from)
                    .subscribe(this::addSectionItems, this::onError, this::onCompleted);
        }

        @Override
        public void onCompleted() {
            updateItemViewModels();
            super.onCompleted();
        }

        protected void updateItemViewModels() {
            addListToItemViewModels(mode, newItemViewModels);
        }

        private int compareTo(GroupedObservable<H, T> go, GroupedObservable<H, T> go2 ) {
            return ASectionCollectionViewModel.this.compareToHeaders(go.getKey(), go2.getKey());
        }

        private void addSectionItems(GroupedObservable<H, T> groupedObservable) {
            H header = groupedObservable.getKey();
            itemViewModels.add(newSectionHeaderItemViewModel(header));
            groupedObservable.toSortedList(ASectionCollectionViewModel.this::compareToItems)
                    .flatMap(ts -> {
                        sectionedItems.put(header, ts);
                        return Observable.from(ts);
                    })
                    .map(ASectionCollectionViewModel.this::newItemViewModel)
                    .toList()
                    .subscribe(iItemViewModels -> {
                        if (newItemViewModels == null) {
                            newItemViewModels = iItemViewModels;
                        }
                        newItemViewModels.addAll(iItemViewModels);
                    }, this::onError);
        }
    }
}
