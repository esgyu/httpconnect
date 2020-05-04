package com.example.httpconnect;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("/todos/")
    Call<String> getItemList();
}
