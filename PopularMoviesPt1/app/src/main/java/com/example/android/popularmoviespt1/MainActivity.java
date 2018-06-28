package com.example.android.popularmoviespt1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Downloader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView xRecyclerView;
    private MovieAdapter xAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 3: reference recyclerview
        setContentView(R.layout.activity_main);
        xRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //TODO 4: create new Movie getters and setters class
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        xAdapter = new MovieAdapter(this);
        xRecyclerView.setAdapter(xAdapter);
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i<20; i++)
        {
            //revisit logic again
            movies.add(new Movie());
        }
        xAdapter.setxMovieList(movies);

        //added below, fix to work :(
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://api.themoviedb.org/3")
//                .setRequestInterceptor(new RequestInterceptor() {
//                    @Override
//                    public void intercept(RequestFacade request) {
//                        request.addEncodedQueryParam("api_key", "YOUR_API_KEY");
//                    }
//                })
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .build();
//        MoviesAPIService service = restAdapter.create(MoviesAPIService.class);
//        service.getPopularMovies(new SortedList.Callback<Movie.MovieResult>() {
//            @Override
//            public void success(Movie.MovieResult movieResult, Downloader.Response response) {
//                xAdapter.setxMovieList(movieResult.getResults());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                error.printStackTrace();
//            }
//        });
    }
    //TODO 6: Add viewHolder class. VIewHolder holds a reference to the views in the row layout.
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

//    //delete method if doesn't work
//    public void initializeRetrofit() {
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://api.themoviedb.org/3")
//                .setRequestInterceptor(new RequestInterceptor() {
//                    @Override
//                    public void intercept(RequestFacade request) {
//                        request.addEncodedQueryParam("api_key", "YOUR_API_KEY");
//                    }
//                })
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .build();
//        MoviesAPIService service = restAdapter.create(MoviesAPIService.class);
//        service.getPopularMovies(new SortedList.Callback<Movie.MovieResult>() {
//            @Override
//            public void success(Movie.MovieResult movieResult, Downloader.Response response) {
//                xAdapter.setMovieList(movieResult.getResults());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                error.printStackTrace();
//            }
//        });
//    }

}

