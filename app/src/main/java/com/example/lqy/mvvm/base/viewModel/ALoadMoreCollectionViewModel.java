package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;
import android.databinding.ObservableBoolean;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.IItemViewBindingCreator;
import com.example.lqy.mvvm.list.ViewBindingRes;

/**
 * Created by qiyao on 2017/2/6.
 */

public abstract class ALoadMoreCollectionViewModel<T> extends ASwipeRefreshCollectionViewModel<T> implements ALoadMoreCollectionViewModel.OnLoadMoreListener{
    private final ObservableBoolean isLoadingEnd = new ObservableBoolean(false);

    public ALoadMoreCollectionViewModel(Context context) {
        super(context);
    }

    @Override
    protected IItemViewBindingCreator<Object> createFooterViewBindingHelper() {
        return new IItemViewBindingCreator<Object>() {
            @Override
            public ViewBindingRes genViewBindingRes() {
                return new ViewBindingRes(R.layout.item_load_more, BR.viewModel);
            }

            @Override
            public IItemViewModel genItemViewModel(Object item) {
                return new LoadMoreViewModel(getContext(), isLoadingEnd);
            }
        };
    }

    public boolean isLoadingEnd() {
        return isLoadingEnd.get();
    }

    public void setLoadingEnd(boolean isLoadingEnd) {
        this.isLoadingEnd.set(isLoadingEnd);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
