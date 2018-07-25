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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.parceler.Parcels;

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
    private static String TAG = MainActivity.class.getSimpleName();
    public static List<Movie> movies = new ArrayList<>();
    MovieAdapter.PosterItemClickListener listener;

    public ArrayList<Movie> mChosen = new ArrayList<>();
    //OnItemClickListener listener;
    private Toast mToast;

    private static int NUM_LIST_ITEMS = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        if(API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please put in your API Key in PopMovieUtils class.", Toast.LENGTH_LONG).show();
            //for when other test the code
        }

        //String[] movieList = getResources().getStringArray(R.array.sandwich_names);
        //Log.i("Main_Activity", "got the following for movie names: " + movieList[1]);


        xRecyclerView = findViewById(R.id.recyclerView);
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        listener = this;

        //xAdapter = new MovieAdapter(getApplicationContext(), movies, R.layout.r_movie, this);

        //xAdapter = new MovieAdapter(int NUM_LIST_ITEMS, getApplicationContext(), movies, listener);  //has different

        //xRecyclerView.setAdapter(xAdapter);
        //xRecyclerView.setOnClickListener(listener);
        getTopMovies(getApplicationContext(), listener);



    }


    private void getTopMovies(final Context context, final MovieAdapter.PosterItemClickListener listener) {

        try {
            MoviesAPIService moviesAPIService = PopMovieUtils.getClient().create(MoviesAPIService.class);
            Call<MovieResponse> call = moviesAPIService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    int statusCode = response.code();
                    if(response.isSuccessful()) {
                        List<Movie> movies = response.body().getResults();
                        if (xAdapter == null) {
                            Log.d(TAG, "Received " + movies.size() + " movies like: " + movies.get(1));
                           xAdapter = new MovieAdapter(NUM_LIST_ITEMS, context, movies, listener);  //has different

                            xRecyclerView.setAdapter(xAdapter);
                        } else {
                            xAdapter.setxMovieList(movies);
                            xAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.e(TAG, "Failed to load list Status Code=" + String.valueOf(statusCode));
                    }

                }


                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure:  " + t.getMessage() + " /n");
                    t.printStackTrace();
                    Log.e(TAG, "more details: " + call);
                    Log.e(TAG, t.toString());
                }
            });
        }catch (Exception e){
            Log.d(TAG, "No Connection "+ e.getMessage());
        }
    }

    private void getMovieDetails() {

    }

    public void onClick(Movie movie) {

//WORKING ISH
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        //Movie movie = movies.get(clickedItemIndex);
        intent.putExtra(DetailActivity.EXTRA_POSITION, movie);
        //Log.d(TAG, " " + clickedItemIndex + " ");
        startActivity(intent);
////
////TO HERE
    }

//    @Override
//    public void onListItemClick( int clickedItemIndex) {
//
////WORKING ISH
////        Intent intent = new Intent(this, DetailActivity.class);
////        Movie movie = movies.get(clickedItemIndex);
////        intent.putExtra(DetailActivity.EXTRA_POSITION, movie);
////        Log.d(TAG, " " + clickedItemIndex + " ");
////        startActivity(intent);
//////
//////TO HERE
//////
//
//
//
//        if (mToast != null) {
//            mToast.cancel();
//        }
//
//
//        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
//        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//
//        mToast.show();
//
//
//    }


}

