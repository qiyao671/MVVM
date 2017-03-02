package com.example.lqy.mvvm.zhihu;

import android.app.Fragment;

import com.example.lqy.mvvm.BR;
import com.example.lqy.mvvm.R;
import com.example.lqy.mvvm.base.other.ViewBindingRes;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.DailyListBean;
import com.example.lqy.mvvm.net.HttpMethods;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/2/15.
 */

public class DailyListViewModel extends ACollectionViewModel<DailyListBean.StoriesBean> {
    public DailyListViewModel(Fragment fragment) {
        super(fragment);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new GetDailyListTask(refreshMode).execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_one_img_news, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(DailyListBean.StoriesBean item) {
        // TODO: 2017/2/15 要做新闻item
        return new OneImgNewsViewModel(getFragment().getActivity(), item);
    }

    private class GetDailyListTask extends APagingTask {

        public GetDailyListTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected Observable<List<DailyListBean.StoriesBean>> getData(RefreshMode mode) {
            return HttpMethods.getInstance().getDailyList();
        }
    }

}
