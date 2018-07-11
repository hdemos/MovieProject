package com.example.android.popularmoviespt1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopMovieUtils {
    private static final String TAG = PopMovieUtils.class.getSimpleName();

    final static String API_KEY = "69e9f8e34637b9e6f6f8e56ce742d1dc"; //set to null when submitting
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
