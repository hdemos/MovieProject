package com.example.android.popularmoviespt1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //get specific movie info


        //Bundle extras = getIntent().getExtras();
        xTitle = findViewById(R.id.titleDetail);
        xOverView = findViewById(R.id.overview);
        xReleaseDate = findViewById(R.id.releaseDate);
        xUserRating = findViewById(R.id.userRating);
        imageView = findViewById(R.id.poster_iv);


        Intent intent = getIntent();


        if(intent==null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }
        Movie movie = MainActivity.movies.get(position);

        xTitle.setText(movie.getTitle());
        xOverView.setText(movie.getOverview());
        xUserRating.setText(String.valueOf(movie.getVote_average()));
        xReleaseDate.setText(movie.getRelease_date());
        Picasso.with(this).load(movie.getPoster()).placeholder(R.color.colorAccent).into(imageView);


        ArrayList<Movie> mChosen = new ArrayList<>();
        mChosen = (ArrayList<Movie>) intent.getSerializableExtra("movie_chosen");
        System.out.print(mChosen);



        //String poster_url = (String) getIntent().getStringExtra("poster_img");
        //load poster from url
//        if(extras!=null) {
//            int position = extras.getInt("position");
//            //displayMovie(position);
//            Movie movie = MainActivity.movies.get(position);

//            xTitle.setText(movie.getTitle());
//            xOverView.setText(movie.getOverview());
//            xUserRating.setText(String.valueOf(movie.getVote_average()));
//            xReleaseDate.setText(movie.getRelease_date()));
//            Picasso.with(this).load(movie.getPoster()).placeholder(R.color.colorAccent).into(imageView);

       // }

        //Picasso.with(this).load("https://i.pinimg.com/originals/a2/e4/c4/a2e4c4ace4ed0eb1a730359b514cbbb9.jpg").placeholder(R.color.colorAccent).into(imageView);

    }



    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


}



