package com.example.android.popularmoviespt1;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import javax.security.auth.callback.Callback;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.android.popularmoviespt1.PopMovieUtils.API_KEY;

public interface MoviesAPIService {

    //https://api.themoviedb.org/3/movie/76341?api_key={api_key}

        // Request method and URL specified in the annotation

        @GET("?sort_by=popylarity.desc&api_key="+API_KEY)
        Call<List<Movie>> getMovies();

//        @GET("films")
//        Call<List<Movie>> getMovies();
//
//        @POST("users/new")
//        Call<User> createUser(@Body User user);



// @GET("/movie/popular")
//    void getPopularMovies(Callback<Movie.MovieResult> cb);

}
