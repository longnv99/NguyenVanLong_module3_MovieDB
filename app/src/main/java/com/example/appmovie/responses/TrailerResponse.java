package com.example.appmovie.responses;

import com.example.appmovie.model.trailermodel.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;

public
class TrailerResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private
    List<Trailer> results;

    public
    int getId() {
        return id;
    }

    public
    List<Trailer> getResults() {
        return results;
    }
}
