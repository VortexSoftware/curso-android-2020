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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.adapters.CategorySelectorAdapter;
import com.cursoandroid.gestordegastos.databinding.FragmentSelectorBinding;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.repositories.NewExpenseRepository;
import com.cursoandroid.gestordegastos.viewModels.CategorySelectorViewModel;

import java.util.ArrayList;


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
        viewModel.getCategoriesFromServer();
        binding.layoutOverlay.setVisibility(View.VISIBLE);
        setupObservers();
    }

    private void setupObservers() {
        viewModel.getCategories().observe(this, categories -> {
            setupRecycler(categories);
            if (categories.isEmpty()){
                binding.emptyListText.setText("No se encontraron categorias");
            }else {
                binding.emptyListText.setText("");
            }
            binding.layoutOverlay.setVisibility(View.GONE);
        });

        viewModel.getOnGetCategoryFail().observe(this, new Observer<NewExpenseRepository.OnGetCategoryFail>() {
            @Override
            public void onChanged(NewExpenseRepository.OnGetCategoryFail onGetCategoryFail) {
                binding.layoutOverlay.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecycler(ArrayList<Category> categories) {
        adapter = new CategorySelectorAdapter(categories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerSelector.setLayoutManager(linearLayoutManager);
        binding.recyclerSelector.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerSelector.setAdapter(adapter);
        adapter.setListener(new CategorySelectorAdapter.CategorySelectorListener() {
            @Override
            public void onCategoryClick(Category category) {
                if (listener != null) listener.onCategorySelected(category);
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
