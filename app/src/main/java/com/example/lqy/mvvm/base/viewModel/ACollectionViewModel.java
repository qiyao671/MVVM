package com.example.lqy.mvvm.base.viewModel;

import android.databinding.ObservableArrayList;

import com.example.lqy.mvvm.list.ViewBindingRes;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:53.
 * 类描述：
 */

public abstract class ACollectionViewModel implements IViewModel {
    private ViewBindingRes itemRes;
    private ViewBindingRes headerRes;
    private ViewBindingRes footerRes;

    public ACollectionViewModel(ViewBindingRes itemRes, ViewBindingRes headerRes, ViewBindingRes footerRes) {
        this.itemRes = itemRes;
        this.headerRes = headerRes;
        this.footerRes = footerRes;
    }

    //data for presenter
    public final ItemViewArg itemView = createItemView();


    //child viewModel
    public final ObservableArrayList<IItemViewModel> itemViewModels = new ObservableArrayList<>();


    protected ItemViewArg createItemView(){
        return ItemViewArg.of(new ItemViewSelector() {
            @Override
            public void select(ItemView itemView, int position, Object item) {
                ViewBindingRes bindingRes = getBindingRes(position, item);
                itemView.set(bindingRes.getBindingVariableRes(), bindingRes.getLayoutRes());
            }

            @Override
            public int viewTypeCount() {
                return 2;
            }
        });
    }

    protected ViewBindingRes getBindingRes(int position, Object item) {
        if (position == 0 && headerRes != null) {
            return headerRes;
        } else if (position == itemViewModels.size() - 1 && footerRes != null) {
            return footerRes;
        } else {
            return itemRes;
        }
    }

    public ViewBindingRes getItemRes() {
        return itemRes;
    }

    public ViewBindingRes getHeaderRes() {
        return headerRes;
    }

    public ViewBindingRes getFooterRes() {
        return footerRes;
    }
}
