package com.example.lqy.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lqy.mvvm.zhihu.DailyListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, new DailyListFragment()).commit();
//        EditText et = (EditText) findViewById(R.id.et);
//        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
    }
}
