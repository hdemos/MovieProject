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

//import static com.example.android.popularmoviespt1.PopMovieUtils.API_KEY;

public interface MoviesAPIService {

    //https://api.themoviedb.org/3/movie/76341?api_key={api_key}

        // Request method and URL specified in the annotation

    //get top rated to start with

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String API_KEY);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String API_KEY);

//    @GET("movie/{id}")
//    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String API_KEY);




}
