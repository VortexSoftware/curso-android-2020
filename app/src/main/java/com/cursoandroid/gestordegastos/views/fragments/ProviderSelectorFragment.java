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

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.adapters.ProviderSelectorAdapter;
import com.cursoandroid.gestordegastos.databinding.FragmentSelectorBinding;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.viewModels.ProviderSelectorViewModel;


public class ProviderSelectorFragment extends Fragment {
    private FragmentSelectorBinding binding;
    private ProviderSelectorAdapter adapter;
    private ProviderSelectorViewModel viewModel;
    private ProviderSelectorFragmentListener listener;

    public ProviderSelectorFragment() {
        // Required empty public constructor
    }


    public interface ProviderSelectorFragmentListener{
        void onProviderSelected(Provider provider);
    }


    public static ProviderSelectorFragment newInstance() {
        return new ProviderSelectorFragment();
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
        viewModel = new ViewModelProvider(this).get(ProviderSelectorViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
    }

    private void setupRecycler() {
        adapter = new ProviderSelectorAdapter(viewModel.getProviders().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.recyclerSelector.setLayoutManager(linearLayoutManager);
        binding.recyclerSelector.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        binding.recyclerSelector.setAdapter(adapter);
        adapter.setListener(new ProviderSelectorAdapter.ProviderSelectorListener() {
            @Override
            public void onAccountClick(Provider provider) {
               if (listener!=null)listener.onProviderSelected(provider);
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ProviderSelectorFragmentListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString()
                    + "must implement ProviderSelectorFragmentListener");
        }
    }
}
