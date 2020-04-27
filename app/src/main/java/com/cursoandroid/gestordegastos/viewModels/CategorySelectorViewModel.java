package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Currency;

import java.util.ArrayList;

public class CategorySelectorViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Category>> categories = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Category>> getCategories() {
        generateCategories();
        return categories;
    }

    public void generateCategories(){
        categories.setValue(new ArrayList<Category>());
        categories.getValue().add(new Category("1","comida"));
        categories.getValue().add(new Category("2","Limpieza"));
        categories.getValue().add(new Category("3","Libreria"));
        categories.getValue().add(new Category("4","Limpieza"));
    }
}
