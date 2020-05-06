package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Currency;
import com.cursoandroid.gestordegastos.repositories.NewExpenseRepository;

import java.util.ArrayList;

public class CategorySelectorViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Category>> categories = new MutableLiveData<>();
    private MutableLiveData<NewExpenseRepository.OnGetCategoryFail> onGetCategoryFail = new MutableLiveData<>();
    private NewExpenseRepository newExpenseRepository;

    public NewExpenseRepository getNewExpenseRepository() {
        if (newExpenseRepository == null){
            newExpenseRepository = new NewExpenseRepository();
            setupObservers();
        }
        return newExpenseRepository;
    }

    public MutableLiveData<NewExpenseRepository.OnGetCategoryFail> getOnGetCategoryFail() {
        return onGetCategoryFail;
    }

    private void setupObservers() {
        getNewExpenseRepository().getOnGetCategoriesSuccessData().subscribe(onGetCategoriesSuccess -> {
            getCategories().setValue(onGetCategoriesSuccess.getCategories());
        });
        getNewExpenseRepository().getOnGetCategoriesFailData().subscribe(onGetCategoryFail -> {
            getOnGetCategoryFail().setValue(onGetCategoryFail);
        });
    }

    public MutableLiveData<ArrayList<Category>> getCategories() {
        return categories;
    }

    public void getCategoriesFromServer() {
        getNewExpenseRepository().getCategoriesFromServer();
    }
}
