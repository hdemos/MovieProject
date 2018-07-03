package com.example.android.popularmoviespt1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie
{
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("backdrop_path")
    private String backdrop;
    private String vote_average;
    private String release_date;

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";



    public String getOriginalTitle() {
        return title;
    }

    public void setOriginalTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        //return poster;
        //return "http://t2.gstatic.com/imagez?q=tbn:ANd9GcQW3LbpT94mtUG1PZIIzJNxmFX399wr_NcvoppJ82k7z99Hx6in";
        //TODO return poster image until API call works
        return "https://i.pinimg.com/originals/a2/e4/c4/a2e4c4ace4ed0eb1a730359b514cbbb9.jpg";
        //return TMDB_IMAGE_PATH + poster_path;
    }

    public void setPoster(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String description) {
        this.overview = description;
    }

    public String getBackdrop() {
        return TMDB_IMAGE_PATH + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String voter_average) {
        this.vote_average = voter_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results; //pt2
        }
    }
}
