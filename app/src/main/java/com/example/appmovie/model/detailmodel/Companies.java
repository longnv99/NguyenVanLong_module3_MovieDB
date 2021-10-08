package com.example.appmovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

public
class Companies {
    @SerializedName("id")
    private int id;

    @SerializedName("logo_path")
    private String logo_path;

    @SerializedName("name")
    private String name;

    @SerializedName("origin_country")
    private String origin_country;

    public
    int getId() {
        return id;
    }

    public
    String getLogo_path() {
        return logo_path;
    }

    public
    String getName() {
        return name;
    }

    public
    String getOrigin_country() {
        return origin_country;
    }
}
