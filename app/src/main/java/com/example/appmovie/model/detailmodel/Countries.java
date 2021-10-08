package com.example.appmovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

public
class Countries {
    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("name")
    private String name;

    public
    String getIso_3166_1() {
        return iso_3166_1;
    }

    public
    String getName() {
        return name;
    }
}

