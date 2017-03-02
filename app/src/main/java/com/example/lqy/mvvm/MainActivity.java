package com.example.lqy.mvvm;

import android.os.Bundle;

import com.example.lqy.mvvm.sectionedList.SectionedListFragment;
import com.trello.rxlifecycle.components.RxActivity;

public class MainActivity extends RxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, new SectionedListFragment()).commit();
//        EditText et = (EditText) findViewById(R.id.et);
//        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
    }
}
