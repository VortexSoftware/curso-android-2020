package com.cursoandroid.gestordegastos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Provider;

import java.util.ArrayList;

public class ProviderSelectorViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Provider>> providers = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Provider>> getProviders() {
        generateProviders();
        return providers;
    }

    public void generateProviders(){
        providers.setValue(new ArrayList<Provider>());
        providers.getValue().add(new Provider("1","proveedor 1"));
        providers.getValue().add(new Provider("2","proveedor 2"));
        providers.getValue().add(new Provider("3","proveedor 3"));
        providers.getValue().add(new Provider("4","proveedor 4"));
    }
}
