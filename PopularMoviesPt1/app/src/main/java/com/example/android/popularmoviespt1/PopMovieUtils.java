package com.example.android.popularmoviespt1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopMovieUtils {
    private static final String TAG = PopMovieUtils.class.getSimpleName();

    final static String API_KEY = BuildConfig.TMDB_API_KEY; //set to null when submitting
    final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
