package com.example.android.popularmoviespt1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements MovieAdapter.PosterItemClickListener {

    private RecyclerView xRecyclerView;
    private MovieAdapter xAdapter;
    private static final String TAG = "MainActivity";
    private ArrayList<Movie> movies = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        //String[] movieList = getResources().getStringArray(R.array.sandwich_names);
        //Log.i("Main_Activity", "got the following for movie names: " + movieList[1]);

        //String Prabhu = "popularity.desc";
        //PopMovieUtils.buildURl(Prabhu);

        xRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        xAdapter = new MovieAdapter(this, movies, this);
        xRecyclerView.setAdapter(xAdapter);
        tempPopulatePosters();
        //getMovies();
    }

    private void getMovies() {

        MoviesAPIService moviesAPIService = PopMovieUtils.createService(MoviesAPIService.class);
        Call<List<Movie>> call = moviesAPIService.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    for( Movie movie: response.body()) {
                        movies.add(movie);
                        Log.d(TAG, "test");
                    }
                    xAdapter.setxMovieList(movies);
                }else{ Log.e(TAG, "fail " + response.message()); }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e(TAG, "onFailure:  " + t.getMessage() +" /n");
                t.printStackTrace();
                Log.e(TAG, "more details: "+ call);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //switch case
        return true;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        launchDetailActivity(clickedItemIndex);
    }


    public void tempPopulatePosters() {


        for (int i = 0; i<20; i++)
        {
            //revisit logic again
            movies.add(new Movie());
        }
        xAdapter.setxMovieList(movies);


    }

//
    public class FetchMovies extends AsyncTask<Void,Void,Void> {
        @Override
    protected Void doInBackground(Void... voids) {

            return null;
        }
}



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


    //TODO 6: Add viewHolder class. VIewHolder holds a reference to the views in the row layout.


    public void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);

        String posterAddress = "https://i.pinimg.com/originals/a2/e4/c4/a2e4c4ace4ed0eb1a730359b514cbbb9.jpg";
        intent.putExtra(DetailActivity.EXTRA_POSITION, position); //correct way
        intent.putExtra("poster_img", posterAddress);
        startActivity(intent);
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

