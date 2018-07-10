package com.example.android.popularmoviespt1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.AdapterView.*;

import static com.example.android.popularmoviespt1.PopMovieUtils.API_KEY;

public class MainActivity extends AppCompatActivity implements MovieAdapter.PosterItemClickListener{

    private RecyclerView xRecyclerView;
    private MovieAdapter xAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Movie> movies = new ArrayList<>();
    MovieAdapter.PosterItemClickListener listener = null;
    //OnItemClickListener listener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        if(API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please put in your API Key in PopMovieUtils class.", Toast.LENGTH_LONG).show();
        }

        //String[] movieList = getResources().getStringArray(R.array.sandwich_names);
        //Log.i("Main_Activity", "got the following for movie names: " + movieList[1]);


        xRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        listener = this;
        //xAdapter = new MovieAdapter(getApplicationContext(), movies, R.layout.r_movie, this);
        getTopMovies(getApplicationContext(), listener);




    }


    private void getTopMovies(final Context context, final MovieAdapter.PosterItemClickListener listener) {

        MoviesAPIService moviesAPIService = PopMovieUtils.getClient().create(MoviesAPIService.class);
        Call<MovieResponse> call = moviesAPIService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {

                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Received "+movies.size() + " movies like: " + movies.get(1));
                //listener = this;
                xAdapter = new MovieAdapter(context, movies, R.layout.r_movie, listener);
                xRecyclerView.setAdapter(xAdapter);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:  " + t.getMessage() +" /n");
                t.printStackTrace();
                Log.e(TAG, "more details: "+ call);
                Log.e(TAG, t.toString());
            }
        });
    }

    private void getMovieDetails() {

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        launchDetailActivity(clickedItemIndex);
    }


    //TODO 6: Add viewHolder class. VIewHolder holds a reference to the views in the row layout.


    public void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);


        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }


}

