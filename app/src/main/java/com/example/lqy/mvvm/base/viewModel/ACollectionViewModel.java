package com.example.lqy.mvvm.base.viewModel;

import android.databinding.ObservableArrayList;

import com.example.lqy.mvvm.base.IItemViewBindingCreator;
import com.example.lqy.mvvm.list.ViewBindingRes;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:53.
 * 类描述：
 */

public abstract class ACollectionViewModel<T> implements IViewModel {
    private IItemViewBindingCreator headerViewBindingCreator;
    private IItemViewBindingCreator footerViewBindingCreator;

    public ACollectionViewModel() {
        initItemViewModelList();
        headerViewBindingCreator = createHeaderViewBindingHelper();
        footerViewBindingCreator = createFooterViewBindingHelper();
    }

    //data for presenter
    public final ItemViewArg itemView = createItemView();


    //child viewModel
    public final ObservableArrayList<IItemViewModel> itemViewModels = new ObservableArrayList<>();


    protected ItemViewArg createItemView(){
        return ItemViewArg.of(new ItemViewSelector<IItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, IItemViewModel item) {
                ViewBindingRes bindingRes = getBindingRes(position, item);
                itemView.set(bindingRes.getBindingVariableRes(), bindingRes.getLayoutRes());
            }

            @Override
            public int viewTypeCount() {
                return 2;
            }
        });
    }


    public ViewBindingRes getHeaderRes() {
        return headerViewBindingCreator == null ? null : headerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getFooterRes() {
        return footerViewBindingCreator == null ? null :footerViewBindingCreator.genViewBindingRes();
    }

    protected ViewBindingRes getBindingRes(int position, IItemViewModel item) {
        if (position == 0 && headerViewBindingCreator != null) {
            return getHeaderRes();
        } else if (position == itemViewModels.size() - 1 && footerViewBindingCreator != null) {
            return getFooterRes();
        } else {
            return getItemRes();
        }
    }

    private void addListToItemViewModels(ArrayList<IItemViewModel> itemViewModelArrayList) {
        itemViewModels.addAll(getHeaderRes() == null ? 0 : 1, itemViewModelArrayList);
    }

    private void initItemViewModelList() {
        //添加header view model
        if (headerViewBindingCreator != null) {
            itemViewModels.add(headerViewBindingCreator.genItemViewModel());
        }

        ArrayList<T> items = obtainDataSource();
        ArrayList<IItemViewModel> itemViewModelArrayList = generateItemViewModelList(items);
        addListToItemViewModels(itemViewModelArrayList);

        //添加footer view model
        if (footerViewBindingCreator != null) {
            itemViewModels.add(footerViewBindingCreator.genItemViewModel());
        }
    }

    protected IItemViewBindingCreator createHeaderViewBindingHelper() {
        return null;
    }

    protected IItemViewBindingCreator createFooterViewBindingHelper() {
        return null;
    }

    protected abstract ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<T> items);

    protected abstract ArrayList<T> obtainDataSource();

    protected abstract ViewBindingRes getItemRes();
}
