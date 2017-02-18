package com.duphungcong.flicksclone.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by udcun on 2/15/2017.
 */

public class Movie {
    public Movie() {
        this.title = "movie title";
    }

    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private String overview;

    @SerializedName("vote_average")
    private double voteAverage;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public String getPosterPath() { return this.posterPath; }

    public String getBackdropPath() { return this.backdropPath; }

    public String getOverview() { return this.overview; }

    public double getVoteAverage() { return this.voteAverage; }
}
