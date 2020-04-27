package com.cursoandroid.gestordegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;

import java.util.ArrayList;

public class CategorySelectorAdapter extends RecyclerView.Adapter<CategorySelectorAdapter.ViewHolder> {
    private ArrayList<Category> categories;
    private CategorySelectorListener listener;

    public void setListener(CategorySelectorListener listener) {
        this.listener = listener;
    }

    public interface CategorySelectorListener {
        void onCategoryClick(Category category);
    }

    public CategorySelectorAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selector, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.txtItemSelector.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemSelector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemSelector = itemView.getRootView().findViewById(R.id.textViewSelector);
        }
    }
}
