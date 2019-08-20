package com.example.android.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceholderApi jsonPlaceholderApi;

    // TODO: Remove before commit
    private static final String apiKey = " ";

    private static final String JSONPLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/";

    private static final String MOVIE_MOST_POPULAR_URL = "http://api.themoviedb.org/3/movie/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(JSONPLACEHOLDER_URL)
                .baseUrl(MOVIE_MOST_POPULAR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        //getPosts();
        getMovies();

    }


    // Method for calling our "posts" JSON data
    private void getPosts() {
        Call<List<Post>> call = jsonPlaceholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.isSuccessful()) {

                    List<Post> posts = response.body();
                    for (Post post: posts) {
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "UserID: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n\n";

                        textViewResult.append(content);
                    }

                } else {
                    // handle request errors
                    textViewResult.setText("Code: " + response.code());

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getMovies() {

        Call<MovieResponse> call = jsonPlaceholderApi.getMovies(apiKey);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if(response.isSuccessful()) {

                    MovieResponse movieResponse = response.body();
                    List<Movie> movies = movieResponse.getMovieResults();
                    for (Movie movie : movies) {
                        String content = "";
                        content += "Title: " + movie.getTitle() + "\n";
                        content += "Vote Average: " + movie.getVoteAverage() + "\n";
                        content += "Release Date: " + movie.getReleaseDate() + "\n\n";

                        textViewResult.append(content);
                    }

                } else {
                    textViewResult.setText("Code: " + response.code());

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
