package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.IItemViewBindingCreator;
import com.example.lqy.mvvm.base.listener.OnLoadMoreListener;
import com.example.lqy.mvvm.list.ViewBindingRes;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemBinding;
import me.tatarka.bindingcollectionadapter.OnItemBind;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:53.
 * 类描述：
 */

public abstract class ACollectionViewModel<T> implements IViewModel, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private Context context;
    private IItemViewBindingCreator<Object> headerViewBindingCreator;
    private IItemViewBindingCreator<Object> footerViewBindingCreator;

    //下拉刷新
    public final ObservableField<SwipeRefreshLayout.OnRefreshListener> onRefreshListener = new ObservableField<>();
    public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
    public final ObservableBoolean isRefreshEnable = new ObservableBoolean(false);

    //加载更多
    private boolean isNextLoadEnable = false;
    private boolean isLoadMoreEnable = false;
    private boolean isLoading = false;
    private ViewBindingRes loadMoreRes = new ViewBindingRes(R.layout.item_load_more, BR.)

    //data for presenter
    private final ItemBinding itemBinding = ItemBinding.of(createOnItemBind());

    //child viewModel
    public final ObservableArrayList<IItemViewModel> itemViewModels = new ObservableArrayList<>();

    public ACollectionViewModel(Context context) {
        this.context = context;
        headerViewBindingCreator = createHeaderViewBindingHelper();
        footerViewBindingCreator = createFooterViewBindingHelper();
        initItemViewModelList();

        onRefreshListener.set(this);
    }

    @Override
    public void onRefresh() {
        if (isRefreshEnable.get()) {
            isRefreshing.set(true);
            requestData(RefreshMode.refresh);
        }
    }

    @Override
    public void onLoadMore() {
        if (isLoadMoreEnable && !isLoading && isNextLoadEnable) {
            isLoading = true;
            requestData(RefreshMode.load_more);
        }
    }

    //创建OnItemBind用于创建ItemBinding
    private OnItemBind<IItemViewModel> createOnItemBind(){
        return new OnItemBind<IItemViewModel>() {
            @Override
            public void onItemBind(ItemBinding itemBinding, int position, IItemViewModel item) {
                setupItemView(itemBinding, position, item);
            }
        };
    }

    //设置position上的item的itemBinding 用于绑定item与它的view
    protected void setupItemView(ItemBinding itemBinding, int position, IItemViewModel item) {
        ViewBindingRes viewBindingRes = getBindingRes(position, item);
        itemBinding.set(viewBindingRes.getBindingVariableRes(), viewBindingRes.getLayoutRes());
    }

    //itemBinding.set
    public void setItemBinding(ItemBinding itemBinding, ViewBindingRes viewBindingRes) {
        itemBinding.set(viewBindingRes.getBindingVariableRes(), viewBindingRes.getLayoutRes());
    }

    public ViewBindingRes getHeaderRes() {
        return headerViewBindingCreator == null ? null : headerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getFooterRes() {
        return footerViewBindingCreator == null ? null :footerViewBindingCreator.genViewBindingRes();
    }

    public ItemBinding getItemBinding() {
        return itemBinding;
    }

    private ViewBindingRes getBindingRes(int position, IItemViewModel item) {
        if (position == 0 && headerViewBindingCreator != null) {
            return getHeaderRes();
        } else if (position == itemViewModels.size() - 1 && footerViewBindingCreator != null) {
            return getFooterRes();
        } else {
            return getItemRes(position, item);
        }
    }

    protected void addListToItemViewModels(RefreshMode mode, ArrayList<IItemViewModel> itemViewModelArrayList) {
        if (mode == RefreshMode.load_more) {
            addListAtFootOfItemViewModels(itemViewModelArrayList);
        } else if (mode == RefreshMode.reset) {
            itemViewModels.clear();
            addHeaderAndFooter();
            addListAtHeadOfItemViewModels(itemViewModelArrayList);
        } else if (mode == RefreshMode.refresh) {
            addListAtHeadOfItemViewModels(itemViewModelArrayList);
        }
    }

    private void addListAtHeadOfItemViewModels(ArrayList<IItemViewModel> itemViewModelArrayList) {
        itemViewModels.addAll(getHeaderRes() == null ? 0 : 1, itemViewModelArrayList);
    }

    private void addListAtFootOfItemViewModels(ArrayList<IItemViewModel> itemViewModelArrayList) {
        int index = getFooterRes() == null ? itemViewModels.size() - 1 : itemViewModels.size() - 2;
        itemViewModels.addAll(index, itemViewModelArrayList);
    }

    private void initItemViewModelList() {
        requestData(RefreshMode.reset);
    }

    private void addHeaderAndFooter() {
        //添加header view model
        if (headerViewBindingCreator != null) {
            itemViewModels.add(headerViewBindingCreator.genItemViewModel(null));
        }

        //添加footer view model
        if (footerViewBindingCreator != null) {
            itemViewModels.add(footerViewBindingCreator.genItemViewModel(null));
        }
    }

    protected void requestData(RefreshMode refreshMode) {
        ArrayList<T> items = obtainDataSource(refreshMode);
        ArrayList<IItemViewModel> itemViewModelArrayList = generateItemViewModelList(items);
        addListToItemViewModels(refreshMode, itemViewModelArrayList);
    }

    protected IItemViewBindingCreator<Object> createHeaderViewBindingHelper() {
        return null;
    }

    protected IItemViewBindingCreator<Object> createFooterViewBindingHelper() {
        return null;
    }

    protected abstract ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<T> items);

    protected abstract ArrayList<T> obtainDataSource(RefreshMode refreshMode);

    protected abstract ViewBindingRes getItemRes(int position, IItemViewModel item);

    public Context getContext() {
        return context;
    }


    public enum RefreshMode {
        /**
         * 重设数据
         */
        reset,
        /**
         * 上拉，加载更多
         */
        load_more,
        /**
         * 下拉，刷新最新
         */
        refresh
    }
}
