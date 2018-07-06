package com.example.android.popularmoviespt1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//TODO 7: Create Adapter Class

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private List<Movie> xMovieList;
    private LayoutInflater xInflater;
    private Context xContext;
    private CustomClickListener listener;
    private int rowLayout;
    private static final String TAG = MovieAdapter.class.getSimpleName();





    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;
        public LinearLayout posterLayout;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterLayout = (LinearLayout) itemView.findViewById(R.id.posterLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            //title = (TextView) itemView.findViewById(R.id.title);


        }
    }
    public MovieAdapter(Context context, List<Movie> movies, int rowLayout)
    {

        this.xContext = context;
        this.xInflater = LayoutInflater.from(context);
        this.xMovieList = movies;
        this.rowLayout = rowLayout;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = xInflater.from(parent.getContext()).inflate(R.layout.r_movie, parent, false);
        final MovieViewHolder viewHolder = new MovieViewHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v, viewHolder.getPosition());
//            }
//        });
        return viewHolder;



    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position)
    {

        Movie movie = xMovieList.get(position);
        //TODO 7.2: Picasso image loading
        Picasso.with(xContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);
        //attempt to learn string in RV
        holder.title.setText(movie.getTitle());

        Log.d(TAG, "Title is: " + movie.getTitle());
        //get remainder of things you want to pass here?




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
