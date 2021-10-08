package com.example.appmovie.responses;

import com.example.appmovie.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class PopularResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_results;

    public
    int getPage() {
        return page;
    }

    public
    List<Movie> getResults() {
        return results;
    }

    public
    int getTotal_pages() {
        return total_pages;
    }

    public
    int getTotal_results() {
        return total_results;
    }
}
