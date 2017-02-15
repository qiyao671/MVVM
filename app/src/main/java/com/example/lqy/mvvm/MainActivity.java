package com.example.lqy.mvvm;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.example.lqy.mvvm.base.fragment.ARecyclerViewFragment;
import com.example.lqy.mvvm.base.viewModel.ACollectionViewModel;
import com.example.lqy.mvvm.zhihu.DailyListViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Fragment fragment = new ARecyclerViewFragment() {
//            @Override
//            protected ACollectionViewModel createViewModel() {
//                return new DailyListViewModel(getActivity());
//            }
//        };
//        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        EditText et = (EditText) findViewById(R.id.et);
        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
    }
}
