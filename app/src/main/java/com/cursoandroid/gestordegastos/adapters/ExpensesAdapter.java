package com.cursoandroid.gestordegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.gestordegastos.R;
import com.cursoandroid.gestordegastos.models.Expense;

import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    private ArrayList<Expense> expenses;
    private ExpensesAdapterInterface listener;

    public ExpensesAdapter(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setListener(ExpensesAdapterInterface listener) {
        this.listener = listener;
    }

    public interface ExpensesAdapterInterface{
        void onItemClick(Expense expense);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expenses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Expense expense = expenses.get(position);
        holder.txtDate.setText(expense.getCreatedAt());
        holder.txtAccount.setText(expense.getAccount().getName());
        holder.txtCategory.setText(expense.getProvider().getName());
        holder.txtAmount.setText(expense.getAmountAsString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(expense);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate, txtAccount, txtAmount, txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.getRootView().findViewById(R.id.textViewDate);
            txtAccount = itemView.getRootView().findViewById(R.id.textViewAccount);
            txtAmount = itemView.getRootView().findViewById(R.id.textViewAmount);
            txtCategory = itemView.getRootView().findViewById(R.id.textViewCategory);
        }
    }

}
