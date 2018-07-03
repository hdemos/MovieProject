package com.example.android.popularmoviespt1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//TODO 7: Create Adapter Class

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private List<com.example.android.popularmoviespt1.Movie> xMovieList;
    private LayoutInflater xInflater;
    private Context xContext;
    //private CustomClickListener listener;
    final private PosterItemClickListener xOnClickListener;


    public MovieAdapter(Context context, List<Movie> movies, PosterItemClickListener listener)
    {
        this.xContext = context;
        this.xInflater = LayoutInflater.from(context);
        this.xMovieList = new ArrayList<>();
        xOnClickListener = listener;
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView title;

        public MovieViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            xOnClickListener.onListItemClick(clickedPosition);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = xInflater.inflate(R.layout.r_movie, parent, false);
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
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        com.example.android.popularmoviespt1.Movie movie = xMovieList.get(position);
        //TODO 7.2: Picasso image loading
        Picasso.with(xContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);
        //attempt to learn string in RV
        holder.title.setText(movie.getOriginalTitle());
        //get remainder of things you want to pass here?




    }

    @Override
    public int getItemCount() {

        return (xMovieList == null) ? 0 : xMovieList.size();
    }

    public void setxMovieList(List<com.example.android.popularmoviespt1.Movie> movieList)
    {
        this.xMovieList.clear();
        this.xMovieList.addAll(movieList);
        //make sure to notifychange or will crash
        notifyDataSetChanged();
    }


    //creating listener interface for RV
    public interface PosterItemClickListener {
        void onListItemClick(int clickItemIndex);
    }




}
