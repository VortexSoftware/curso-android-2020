package com.cursoandroid.gestordegastos.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.databinding.FragmentSelectorBinding;


public class CategorySelectorFragment extends Fragment {
    private FragmentSelectorBinding binding;

    public CategorySelectorFragment() {
        // Required empty public constructor
    }


    public static CategorySelectorFragment newInstance() {
        return new CategorySelectorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selector, container, false);
        return binding.getRoot();
    }
}
