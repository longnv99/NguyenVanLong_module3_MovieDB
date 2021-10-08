package com.example.appmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class Movie {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    @SerializedName("id")
    private int id;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("title")
    private String title;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("vote_count")
    private int vote_count;

    public
    boolean isAdult() {
        return adult;
    }

    public
    String getBackdrop_path() {
        return backdrop_path;
    }

    public
    List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public
    int getId() {
        return id;
    }

    public
    String getOriginal_language() {
        return original_language;
    }

    public
    String getOriginal_title() {
        return original_title;
    }

    public
    String getOverview() {
        return overview;
    }

    public
    float getPopularity() {
        return popularity;
    }

    public
    String getPosterPath() {
        return posterPath;
    }

    public
    String getReleaseDate() {
        return releaseDate;
    }

    public
    String getTitle() {
        return title;
    }

    public
    boolean isVideo() {
        return video;
    }

    public
    float getVote_average() {
        return vote_average;
    }

    public
    int getVote_count() {
        return vote_count;
    }
}
