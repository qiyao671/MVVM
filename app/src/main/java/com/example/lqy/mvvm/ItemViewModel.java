package com.example.lqy.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.Toast;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:48.
 * 类描述：
 */

public class ItemViewModel{
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
}