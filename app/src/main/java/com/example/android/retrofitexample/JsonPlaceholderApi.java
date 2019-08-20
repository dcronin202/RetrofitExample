package com.example.android.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {

    @GET("posts")  // we get "posts" from the url - https://jsonplaceholder.typicode.com/posts
    Call<List<Post>> getPosts();

    @GET("popular")  // to get the url "popular?api_key="
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    //@GET
    //Call<List<Movie>> getMovies(@Url String url);

}
