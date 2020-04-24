package com.cursoandroid.gestordegastos.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.adapters.AccountSelectorAdapter;
import com.cursoandroid.gestordegastos.databinding.FragmentSelectorBinding;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.viewModels.AccountSelectorViewModel;


public class AccountSelectorFragment extends Fragment {
    private FragmentSelectorBinding binding;
    private AccountSelectorViewModel viewModel;
    private AccountSelectorAdapter adapter;

    private AccountSelectorFragmentListener listener;

    public interface AccountSelectorFragmentListener {
        void onAccountSelected(Account account);
    }

    public AccountSelectorFragment() {
        // Required empty public constructor
    }


    public static AccountSelectorFragment newInstance() {
        return new AccountSelectorFragment();
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
        viewModel = new ViewModelProvider(this).get(AccountSelectorViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
    }

    private void setupRecycler() {
        adapter = new AccountSelectorAdapter(viewModel.getAccounts().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerSelector.setLayoutManager(linearLayoutManager);
        binding.recyclerSelector.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerSelector.setAdapter(adapter);
        adapter.setListener(new AccountSelectorAdapter.AccountSelectorListener() {
            @Override
            public void onAccountClick(Account account) {
                if (listener != null) listener.onAccountSelected(account);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AccountSelectorFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement AccountSelectorFragmentListener");

        }
    }
}
