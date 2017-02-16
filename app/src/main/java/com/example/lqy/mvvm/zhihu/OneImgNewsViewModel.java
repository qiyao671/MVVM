package com.example.lqy.mvvm.zhihu;

import android.app.Fragment;
import android.content.Context;
import android.databinding.ObservableField;

import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.DailyListBean;

import rx.Observable;

/**
 * Created by qiyao on 2017/2/16.
 */

public class OneImgNewsViewModel implements IItemViewModel {
    private Context context;

    private DailyListBean.StoriesBean storiesBean;

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();

    public OneImgNewsViewModel(Context context, DailyListBean.StoriesBean storiesBean) {
        this.context = context;
        this.storiesBean = storiesBean;
        title.set(storiesBean.getTitle());
        imageUrl.set(storiesBean.getImages().get(0));
    }

    @Override
    public int getItemViewType() {
        return DailyListBean.StoriesBean.TYPE_NEWS;
    }
}
