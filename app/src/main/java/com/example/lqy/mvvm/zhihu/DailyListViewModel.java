package com.example.lqy.mvvm.zhihu;

import android.content.Context;

import com.example.lqy.mvvm.base.other.ViewBindingRes;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.DailyListBean;
import com.example.lqy.mvvm.net.HttpMethods;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/2/15.
 */

public class DailyListViewModel extends ACollectionViewModel<DailyListBean.StoriesBean> {
    public DailyListViewModel(Context context) {
        super(context);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new GetDailyListTask(refreshMode);
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return null;
    }

    @Override
    protected IItemViewModel newItemViewModel(DailyListBean.StoriesBean item) {
        return null;
    }

    private class GetDailyListTask extends APagingTask<List<DailyListBean.StoriesBean>> {

        public GetDailyListTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected Observable<List<DailyListBean.StoriesBean>> getData() {
            return HttpMethods.getInstance().getDailyList();
        }

        @Override
        protected Observable<List<DailyListBean.StoriesBean>> handleData(Observable<List<DailyListBean.StoriesBean>> upstream) {
            return upstream;
        }
    }

}
