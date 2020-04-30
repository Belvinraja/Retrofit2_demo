package com.example.retrofit_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface json_api {

    @GET("posts/1")
    Call<List<Post>> getPost();
}
