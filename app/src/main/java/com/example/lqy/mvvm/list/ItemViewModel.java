package com.example.lqy.mvvm.list;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.example.lqy.mvvm.base.viewModel.itemViewModel.IItemViewModel;
import com.example.lqy.mvvm.bean.User;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:48.
 * 类描述：
 */

public class ItemViewModel implements IItemViewModel {
    private Context context;

    private User user;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> pwd = new ObservableField<>();

    public ItemViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
        name.set(user.getName());
        pwd.set(user.getPwd());
    }


    public void onClick() {
        Toast.makeText(context, user.getName() + " " + user.getPwd(), Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemViewType() {
        return 0;
    }
}