package com.example.appmovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

public
class Languages {
    @SerializedName("english_name")
    private String english_name;

    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("name")
    private String name;

    public
    String getEnglish_name() {
        return english_name;
    }

    public
    String getIso_639_1() {
        return iso_639_1;
    }

    public
    String getName() {
        return name;
    }
}
