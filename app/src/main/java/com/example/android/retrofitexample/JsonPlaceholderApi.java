package com.example.android.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {

    @GET("posts")  // we get "posts" from the url - https://jsonplaceholder.typicode.com/posts
    Call<List<Post>> getPosts();

}
