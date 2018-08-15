package com.example.android.popularmoviespt1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.android.popularmoviespt1.Movie;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//TODO 7: Create Adapter Class

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private List<Movie> xMovieList;
    private LayoutInflater xInflater;
    private Context xContext;
    final private PosterItemClickListener xOnClickListener;
    private static final String TAG = MovieAdapter.class.getSimpleName();
    private int mNumberItems;


    public interface PosterItemClickListener {
        void onClick(int position, Movie movie);
    }

    public MovieAdapter(int numberOfItems, Context context, List<Movie> movies, PosterItemClickListener listener)
    {
        this.xContext = context;
        //this.xInflater = LayoutInflater.from(context);
        this.xMovieList = movies;
        xOnClickListener = listener;
        mNumberItems = numberOfItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.r_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {


        Log.d(TAG, "#" + position);
        Movie movie = xMovieList.get(position);
//        //TODO 7.2: Picasso image loading
        Picasso.with(xContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);
        Log.d(TAG, "Title is a: " + xMovieList.get(position).getTitle());
        Log.d(TAG, "Poster location is: " + xMovieList.get(position).getPoster());
    }

    @Override
    public int getItemCount() {

        return (xMovieList == null) ? 0 : xMovieList.size();

    }

    public void setxMovieList(List<Movie> movieList)
    {

        xMovieList = movieList;
        //make sure to notifychange or will crash
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public LinearLayout posterLayout;
        private TextView name;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterLayout = (LinearLayout) itemView.findViewById(R.id.posterLayout);
            name = (TextView) itemView.findViewById(R.id.titleDetail);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }
        public void bind(final Movie item, final AdapterView.OnItemClickListener listener) {
            name.setText(item.getTitle());

//            
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Log.d(TAG, "clicked position: " + clickedPosition);
            Log.d(TAG, "set xOnClickListener");
            ////THE CODE STOPS HERE AND GETS AN EMPTY ARRAY :(
            Movie movie2 = xMovieList.get(clickedPosition);
            Log.d(TAG, " " + movie2.getTitle() + " ");
            xOnClickListener.onClick(clickedPosition, movie2);
            Log.d(TAG, "Launched detail activity)");


        }
    }













}
