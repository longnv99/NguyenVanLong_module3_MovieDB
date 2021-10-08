package com.example.appmovie.model.trailermodel;

import com.google.gson.annotations.SerializedName;

public
class Trailer {
    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private int size;

    @SerializedName("type")
    private String type;

    @SerializedName("official")
    private boolean official;

    @SerializedName("published_at")
    private String published_at;

    @SerializedName("id")
    private String id;

    public
    String getIso_639_1() {
        return iso_639_1;
    }

    public
    String getIso_3166_1() {
        return iso_3166_1;
    }

    public
    String getName() {
        return name;
    }

    public
    String getKey() {
        return key;
    }

    public
    String getSite() {
        return site;
    }

    public
    int getSize() {
        return size;
    }

    public
    String getType() {
        return type;
    }

    public
    boolean isOfficial() {
        return official;
    }

    public
    String getPublished_at() {
        return published_at;
    }

    public
    String getId() {
        return id;
    }
}
