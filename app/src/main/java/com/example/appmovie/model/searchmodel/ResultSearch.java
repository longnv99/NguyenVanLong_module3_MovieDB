package com.example.appmovie.model.searchmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class ResultSearch {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private
    List<Result> results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_results;

    public
    int getPage() {
        return page;
    }

    public
    List<Result> getResults() {
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
