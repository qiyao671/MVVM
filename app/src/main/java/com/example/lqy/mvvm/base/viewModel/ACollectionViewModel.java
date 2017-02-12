package com.example.lqy.mvvm.base.viewModel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.lqy.mvvm.base.other.IItemViewBindingCreator;
import com.example.lqy.mvvm.base.other.OnLoadMoreListener;
import com.example.lqy.mvvm.base.other.SimpleLoadMoreViewBindingCreator;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.StaticItemViewModel;
import com.example.lqy.mvvm.base.other.ViewBindingRes;

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
    private IItemViewBindingCreator<Object> headerViewBindingCreator = createHeaderViewBindingHelper();
    private IItemViewBindingCreator<Object> footerViewBindingCreator = createFooterViewBindingHelper();

    //下拉刷新
    public final ObservableField<SwipeRefreshLayout.OnRefreshListener> onRefreshListener = new ObservableField<>();
    public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
    public final ObservableBoolean isRefreshEnable = new ObservableBoolean(false);

    //加载更多
    private boolean isNextLoadEnable = false;
    private boolean isLoadMoreEnable = false;
    private boolean isLoading = false;
    public final ObservableField<OnLoadMoreListener> onLoadMoreListener = new ObservableField<>();
    private IItemViewBindingCreator<Object> loadMoreViewBindingCreator = createLoadMoreViewBindingHelper();

    //data for presenter
    private final ItemBinding itemBinding = ItemBinding.of(createOnItemBind());

    //child viewModel
    public final ObservableArrayList<IItemViewModel> itemViewModels = new ObservableArrayList<>();

    public ACollectionViewModel(Context context) {
        this.context = context;

        initItemViewModelList();

        onRefreshListener.set(this);
        onLoadMoreListener.set(this);
    }

    //刷新的回调
    @Override
    public void onRefresh() {
        if (isRefreshEnable.get()) {
            isRefreshing.set(true);
            requestData(RefreshMode.refresh);
        }
    }

    //加载更多的回调
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
        setItemBinding(itemBinding, viewBindingRes);
    }

    //itemBinding.set
    public void setItemBinding(ItemBinding itemBinding, ViewBindingRes viewBindingRes) {
        itemBinding.set(viewBindingRes.getBindingVariableRes(), viewBindingRes.getLayoutRes());
    }

    private ViewBindingRes getBindingRes(int position, IItemViewModel item) {
        switch (item.getItemViewType()) {
            case StaticItemViewModel.TYPE_HEADER:
                return getHeaderRes();
            case StaticItemViewModel.TYPE_FOOTER:
                return getFooterRes();
            case StaticItemViewModel.TYPE_LOAD_MORE:
                return getLoadMoreViewRes();
            default:
                return getItemRes(position, item);
        }
    }

    protected void addListToItemViewModels(RefreshMode mode, ArrayList<IItemViewModel> itemViewModelArrayList) {
        if (mode == RefreshMode.load_more) {
            addListAtFootOfItemViewModels(itemViewModelArrayList);
        } else if (mode == RefreshMode.reset) {
            itemViewModels.clear();
            addListAtHeadOfItemViewModels(itemViewModelArrayList);
            addStaticViewModel();
        } else if (mode == RefreshMode.refresh) {
            addListAtHeadOfItemViewModels(itemViewModelArrayList);
        }
    }

    private void addListAtHeadOfItemViewModels(ArrayList<IItemViewModel> itemViewModelArrayList) {
        itemViewModels.addAll(getHeaderViewCount(), itemViewModelArrayList);
    }

    private void addListAtFootOfItemViewModels(ArrayList<IItemViewModel> itemViewModelArrayList) {
        int index = itemViewModels.size() - getFooterViewCount() - getLoadMoreViewCount();
        itemViewModels.addAll(index, itemViewModelArrayList);
    }

    private void initItemViewModelList() {
        requestData(RefreshMode.reset);
    }

    private void addStaticViewModel() {
        //添加header view model
        if (getHeaderViewCount() > 0) {
            itemViewModels.add(0, headerViewBindingCreator.genItemViewModel(null));
        }

        //添加footer view model
        if (getFooterViewCount() > 0) {
            itemViewModels.add(footerViewBindingCreator.genItemViewModel(null));
        }

        //添加load more view model
        int itemCount = itemViewModels.size() - (getHeaderViewCount() + getFooterViewCount());
        if (getLoadMoreViewCount() > 0 && itemCount > 0) {
            itemViewModels.add(loadMoreViewBindingCreator.genItemViewModel(null));
        }
        // TODO: 2017/2/10 添加empty view
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

    protected IItemViewBindingCreator<Object> createLoadMoreViewBindingHelper() {
        return new SimpleLoadMoreViewBindingCreator(context);
    }

    protected abstract ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<T> items);

    protected abstract ArrayList<T> obtainDataSource(RefreshMode refreshMode);

    protected abstract ViewBindingRes getItemRes(int position, IItemViewModel item);

    public ViewBindingRes getHeaderRes() {
        return headerViewBindingCreator == null ? null : headerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getFooterRes() {
        return footerViewBindingCreator == null ? null :footerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getLoadMoreViewRes() {
        return loadMoreViewBindingCreator == null ? null :loadMoreViewBindingCreator.genViewBindingRes();
    }

    public ItemBinding getItemBinding() {
        return itemBinding;
    }

    public Context getContext() {
        return context;
    }

    private int getHeaderViewCount() {
        return getHeaderRes() == null ? 0 : 1;
    }

    private int getFooterViewCount() {
        return getFooterRes() == null ? 0 : 1;
    }

    private int getLoadMoreViewCount() {
        return getHeaderRes() == null ? 0 : 1;
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
