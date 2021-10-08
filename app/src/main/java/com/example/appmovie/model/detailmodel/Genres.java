package com.example.appmovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

public
class Genres {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public
    int getId() {
        return id;
    }

    public
    String getName() {
        return name;
    }
}
