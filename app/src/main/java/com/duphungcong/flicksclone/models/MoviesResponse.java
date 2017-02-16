package com.duphungcong.flicksclone.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by udcun on 2/15/2017.
 */

public class MoviesResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getResults() {
        return results;
    }
}
