package com.example.android.popularmoviespt1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = DetailActivity.class.getSimpleName();
    private TextView xTitle;
    private TextView xOverView;
    private TextView xReleaseDate;
    private TextView xUserRating;
    private ImageView imageView;
    private String releaseDate;
    private String ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Movie movie = (Movie) getIntent().getParcelableExtra(EXTRA_POSITION);
        String title = movie.getTitle();
        Log.d(TAG, "main title: " + title);
        String posterPath = movie.getPoster();
        String overview = movie.getOverview();
        releaseDate = movie.getRelease_date();
        Double rating = movie.getVote_average();



        //sets views and correct text
        xTitle = findViewById(R.id.titleDetail);
        xOverView = findViewById(R.id.overview);//overview title
        xReleaseDate = findViewById(R.id.releaseDate);
        xUserRating = findViewById(R.id.userRating);
        imageView = findViewById(R.id.poster_iv);


        xTitle.setText(title);
        xOverView.setText(overview);
        ratingLogic(rating);
        xUserRating.setText(ratingText);
        releaseYearLogic(releaseDate);
        xReleaseDate.setText(releaseDate);
        String posterUri = movie.getPoster();
        Picasso.with(this).load(posterUri).into(imageView);

    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void releaseYearLogic(String fullDate) {

        fullDate = fullDate.substring(0, fullDate.indexOf('-'));
        releaseDate = fullDate;

    }

    private void ratingLogic(Double rating) {
        //conversion logic
        ratingText = rating + "/10";

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

}




