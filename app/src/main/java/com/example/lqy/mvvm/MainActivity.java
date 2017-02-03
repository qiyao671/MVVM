package com.example.lqy.mvvm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lqy.mvvm.list.ListFragment;
import com.example.lqy.mvvm.sectionedList.SectionedListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, new SectionedListFragment()).commit();
    }
}
