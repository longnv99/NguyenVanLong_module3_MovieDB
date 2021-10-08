package com.example.appmovie.model.searchmodel;

import com.google.gson.annotations.SerializedName;

public
class Result {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public
    String getName() {
        return name;
    }

    public
    int getId() {
        return id;
    }
}
