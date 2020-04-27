package com.cursoandroid.gestordegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Provider;

import java.util.ArrayList;

public class ProviderSelectorAdapter extends RecyclerView.Adapter<ProviderSelectorAdapter.ViewHolder> {
    private ArrayList<Provider> providers;
    private ProviderSelectorListener listener;

    public void setListener(ProviderSelectorListener listener) {
        this.listener = listener;
    }

    public interface ProviderSelectorListener{
        void onAccountClick(Provider provider);
    }
    public ProviderSelectorAdapter(ArrayList<Provider> accounts) {
        this.providers = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selector, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Provider provider = providers.get(position);
        holder.txtItemSelector.setText(provider.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (listener!=null)listener.onAccountClick(provider);
            }
        });
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemSelector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemSelector = itemView.getRootView().findViewById(R.id.textViewSelector);
        }
    }
}
