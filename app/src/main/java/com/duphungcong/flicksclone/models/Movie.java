package com.duphungcong.flicksclone.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by udcun on 2/15/2017.
 */

public class Movie implements Serializable {
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
    private float voteAverage;

    @SerializedName("release_date")
    private String releaseDate;

    public String getTitle() {
        return this.title;
    }

    public String getPosterPath() { return this.posterPath; }

    public String getBackdropPath() { return this.backdropPath; }

    public String getOverview() { return this.overview; }

    public float getVoteAverage() { return this.voteAverage; }

    public String getReleaseDate() { return this.releaseDate; }
}
