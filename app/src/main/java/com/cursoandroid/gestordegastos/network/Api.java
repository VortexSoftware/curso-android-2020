package com.cursoandroid.gestordegastos.network;

import com.cursoandroid.gestordegastos.models.Account;
import com.cursoandroid.gestordegastos.models.Category;
import com.cursoandroid.gestordegastos.models.Expense;
import com.cursoandroid.gestordegastos.models.Provider;
import com.cursoandroid.gestordegastos.network.requests.CreateExpenseRequest;
import com.cursoandroid.gestordegastos.network.responses.CreateExpenseResponse;
import com.cursoandroid.gestordegastos.network.responses.LoginResponse;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Single<LoginResponse> makeLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/api/v1/transactions/myExpenses")
    Single<ArrayList<Expense>> getExpenses();

    @GET("/api/v1/accounts")
    Single<ArrayList<Account>> getAccounts();

    @GET("/api/v1/expenseCategories")
    Single<ArrayList<Category>> getCategories();

    @GET("/api/v1/providers")
    Single<ArrayList<Provider>> getProviders(
            @Query("categoryId") int categoryId
    );

    @POST("/api/v1/transactions")
    Single<CreateExpenseResponse> createNewExpense(
            @Body CreateExpenseRequest createExpenseRequest
    );
}
