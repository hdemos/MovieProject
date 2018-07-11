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
import com.example.android.popularmoviespt1.Movie;

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
    private PosterItemClickListener xOnClickListener;
    private static final String TAG = MovieAdapter.class.getSimpleName();





    public class MovieViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public ImageView imageView;
        public TextView title;
        public LinearLayout posterLayout;

        public MovieViewHolder(View itemView, PosterItemClickListener xOnClickListener) {
            super(itemView);
            posterLayout = (LinearLayout) itemView.findViewById(R.id.posterLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
            //title = (TextView) itemView.findViewById(R.id.title);


        }
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Log.d(TAG, "clicked position: " + clickedPosition);
            xOnClickListener.onListItemClick(clickedPosition);
        }
    }
    public MovieAdapter(Context context, List<Movie> movies, PosterItemClickListener listener)
    {

        this.xContext = context;
        //this.xInflater = LayoutInflater.from(context);
        this.xMovieList = movies;
        xOnClickListener = listener;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //View view = xInflater.from(parent.getContext()).inflate(R.layout.r_movie, parent, false);
        View view = xInflater.from(parent.getContext()).inflate(R.layout.r_movie, parent, false);
        return new MovieViewHolder(view, xOnClickListener);



    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
          Movie movie = xMovieList.get(position);
          //holder.vote_average.setText(String.valueOf(movie.getVote_average()));
//        //TODO 7.2: Picasso image loading
//
        //TODO 7.2: Picasso image loading
        //Picasso.with(xContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);
        Picasso.with(xContext).load(xMovieList.get(position).getPoster()).into(holder.imageView);


        Log.d(TAG, "Title is: " + xMovieList.get(position).getPoster());
        //holder.title.setText(movie.getTitle());
        //holder.posterLayout.setText(movie.getPoster());
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

    public interface PosterItemClickListener {
        void onListItemClick(int clickItemIndex);
    }






}
