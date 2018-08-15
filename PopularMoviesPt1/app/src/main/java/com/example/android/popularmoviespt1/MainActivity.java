package com.example.android.popularmoviespt1;

import android.app.ActionBar;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.AdapterView.*;

import static com.example.android.popularmoviespt1.PopMovieUtils.API_KEY;

public class MainActivity extends AppCompatActivity implements MovieAdapter.PosterItemClickListener{

    private RecyclerView xRecyclerView;
    private MovieAdapter xAdapter;
    private static String TAG = MainActivity.class.getSimpleName();
    public static List<Movie> movies = new ArrayList<>();
    MovieAdapter.PosterItemClickListener listener;
    public String sortBy = "popular";

    public ArrayList<Movie> mChosen = new ArrayList<>();
    private Toast mToast;

    private static int NUM_LIST_ITEMS = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");


        if(API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please put in your API Key in PopMovieUtils class.", Toast.LENGTH_LONG).show();
            System.out.print("ERROR: Please put in your API Key in PopMovieUtils class.");
            //for when others test the code
        }



            xRecyclerView = findViewById(R.id.recyclerView);
            xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            listener = this;
        if(!API_KEY.isEmpty()){
            getTopMovies(getApplicationContext(), listener);
        }



    }


    private void getTopMovies(final Context context, final MovieAdapter.PosterItemClickListener listener) {

        try {
            MoviesAPIService moviesAPIService = PopMovieUtils.getClient().create(MoviesAPIService.class);
            Call<MovieResponse> call = moviesAPIService.getPopularMovies(API_KEY);
            if(sortBy.contentEquals("popular")) {

                call = moviesAPIService.getPopularMovies(API_KEY);
            }
            else if(sortBy.contentEquals("rating_high")){
                call = moviesAPIService.getTopRatedMovies(API_KEY);
            }
            else{ System.out.print("Error sortBy variable invalid");}

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



    public void onClick(int position, Movie movie) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, movie);
        startActivity(intent);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case R.id.action_most_popular:
                if(!sortBy.contentEquals("popular")) {
                    sortBy = "popular";
                    getTopMovies(getApplicationContext(), listener);}
                Toast.makeText(this, "Ordered by Most Popular  ", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_highest_rated:
                if(!sortBy.contentEquals("rating_high")){
                    sortBy = "rating_high";
                    getTopMovies(getApplicationContext(), listener);}
                Toast.makeText(this, "Order by Highest Rating ", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }


        return true;
    }






}

