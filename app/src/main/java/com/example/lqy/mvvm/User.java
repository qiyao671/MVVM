package com.example.lqy.mvvm;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:36.
 * 类描述：
 */

public class User {
    private String name;
    private String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
