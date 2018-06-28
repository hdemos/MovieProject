package com.example.android.popularmoviespt1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
//TODO 7: Create Adapter Class

public class MovieAdapter extends RecyclerView.Adapter<MainActivity.MovieViewHolder>
{

    private List<Movie> xMovieList;
    private LayoutInflater xInflater;
    private Context xContext;

    public MovieAdapter(Context context)
    {
        this.xContext = context;
        this.xInflater = LayoutInflater.from(context);
        this.xMovieList = new ArrayList<>();
    }

    @Override
    public MainActivity.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = xInflater.inflate(R.layout.r_movie, parent, false);
        MainActivity.MovieViewHolder viewHolder = new MainActivity.MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivity.MovieViewHolder holder, int position)
    {
        Movie movie = xMovieList.get(position);
        //TODO 7.2: Picasso image loading
        Picasso.with(xContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return (xMovieList == null) ? 0 : xMovieList.size();
    }

    public void setxMovieList(List<Movie> movieList)
    {
        this.xMovieList.clear();
        this.xMovieList.addAll(movieList);
        //make sure to notifychange or will crash
        notifyDataSetChanged();
    }
}
