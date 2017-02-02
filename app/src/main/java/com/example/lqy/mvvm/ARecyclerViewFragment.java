package com.example.lqy.mvvm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lqy.mvvm.databinding.FragmentRecyclerViewBinding;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:00.
 * 类描述：
 */

public abstract class ARecyclerViewFragment extends Fragment {
    private FragmentRecyclerViewBinding binding;
    private ACollectionViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        if (viewModel != null) {
            binding.setViewModel(viewModel);
        }
        return binding.getRoot();
    }

    protected abstract ACollectionViewModel createViewModel();

}
