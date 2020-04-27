package com.cursoandroid.gestordegastos.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.adapters.CategorySelectorAdapter;
import com.cursoandroid.gestordegastos.databinding.FragmentSelectorBinding;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.viewModels.CategorySelectorViewModel;


public class CategorySelectorFragment extends Fragment {
    private FragmentSelectorBinding binding;
    private CategorySelectorAdapter adapter;
    private CategorySelectorViewModel viewModel;
    private CategorySelectorFragmentListener listener;

    public CategorySelectorFragment() {
        // Required empty public constructor
    }

    public interface CategorySelectorFragmentListener {
        void onCategorySelected(Category category);
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
        viewModel = new ViewModelProvider(this).get(CategorySelectorViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
    }

    private void setupRecycler() {
        adapter = new CategorySelectorAdapter(viewModel.getCategories().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerSelector.setLayoutManager(linearLayoutManager);
        binding.recyclerSelector.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerSelector.setAdapter(adapter);
        adapter.setListener(new CategorySelectorAdapter.CategorySelectorListener() {
            @Override
            public void onCategoryClick(Category category) {
                if (listener!=null) listener.onCategorySelected(category);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (CategorySelectorFragmentListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString()
                    + "must implement CategorySelectorFragmentListener");
        }
    }
}
