package com.example.android.popularmoviespt1;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopMovieUtils {

    final static String API_KEY = "69e9f8e34637b9e6f6f8e56ce742d1dc"; //set to null when submitting
    final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);

    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> movieClass){
        if(retrofit ==null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(movieClass);
    }
//
//    final static String PARAM_QUERY = "?";
//    final static String PARAM_SORT = "sort_by";
//    final static String sortByPop = "popularity.desc";
//    final static String API = "api_key";
//    final static String API_KEY = "69e9f8e34637b9e6f6f8e56ce742d1dc";   ///takeout before submitting
//
//    public static URL buildURl(String movieSearchQuery) {
//        //fill in method
//        //consider making a switch or method for each search category for part 2
//        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
//                //.appendQueryParameter(PARAM_QUERY, movieSearchQuery)
//                .appendQueryParameter(PARAM_SORT, sortByPop)
//                //.appendPath("?&")
//                .appendQueryParameter(API, API_KEY)
//                .build();
//
//        URL url = null;
//        try {
//            url = new URL(builtUri.toString());
//
//        }catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Log.d("BuildURL", "URL built: " + url);
//        return url;
//
//        //attempt to make retrofit work
//        public static final String BASE_URL = "http://api.myservice.com/";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }


//    **
//            * This method returns the entire result from the HTTP response.
//            *
//            * @param url The URL to fetch the HTTP response from.
//     * @return The contents of the HTTP response.
//            * @throws IOException Related to network and stream reading
//     */

//    public static String getResponseFromHttpUrl(URL url) throws IOException {
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try{
//            InputStream in = urlConnection.getInputStream();
//
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter(""); //what delimeter for json
//            boolean hasInput = scanner.hasNext();
//            if(hasInput) {
//                return scanner.next();
//            } else {
//                return null;
//            }
//        } finally {
//            urlConnection.disconnect();
//        }
//    }


}
