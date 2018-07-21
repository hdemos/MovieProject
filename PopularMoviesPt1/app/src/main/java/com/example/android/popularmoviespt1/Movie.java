package com.example.android.popularmoviespt1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable
{
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("backdrop_path")
    private String backdrop;



    @SerializedName("release_date")
    private String release_date;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("vote_average")
    private Double vote_average;
    @SerializedName("video")
    private boolean hasVideo;

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public Movie(String title, boolean adult, String poster_path, String overview) {

        this.title = title;

        this.poster_path = poster_path;
        this.overview = overview;


    }
    public Movie(String Title, boolean Adult, String Poster_path, String Overview, String Release_date, Double Vote_average) {
        //note: we are setting the items here because we are not changing the data once recieved
        //setters are added in the event this changes and we want to edit data

        //this.title = title;

        //this.poster_path = poster_path;
        //this.overview = overview;
        title = Title;
        poster_path = Poster_path;
        overview = Overview;
        release_date = Release_date;
        vote_average = Vote_average;

        //setRelease_date(release_date);
        //setVote_average(vote_average);

    }
    private Movie(Parcel source) {
        //pulling out data
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        //return poster;
        //return "http://t2.gstatic.com/imagez?q=tbn:ANd9GcQW3LbpT94mtUG1PZIIzJNxmFX399wr_NcvoppJ82k7z99Hx6in";
        //TODO return poster image until API call works
        //return "https://i.pinimg.com/originals/a2/e4/c4/a2e4c4ace4ed0eb1a730359b514cbbb9.jpg";
        return TMDB_IMAGE_PATH + poster_path;
    }



    public String getBackdrop() {
        return TMDB_IMAGE_PATH + backdrop;
    }


    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }
    //    public static class MovieResult {
//        private List<Movie> results;
//
//        public List<Movie> getResults() {
//            return results; //pt2
//        }
//    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(backdrop);
        dest.writeString(release_date);
        dest.writeDouble(vote_average);
        //dest.writeBooleanArray(hasVideo);?


    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
            //uses private constructor
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
            //knows the size of the constructor already
        }
    };
}
