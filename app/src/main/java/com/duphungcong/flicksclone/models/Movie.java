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

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public String getPosterPath() { return this.posterPath; }
}
