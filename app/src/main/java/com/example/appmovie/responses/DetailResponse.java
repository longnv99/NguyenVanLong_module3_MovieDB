package com.example.appmovie.responses;

import com.example.appmovie.model.detailmodel.Collection;
import com.example.appmovie.model.detailmodel.Companies;
import com.example.appmovie.model.detailmodel.Countries;
import com.example.appmovie.model.detailmodel.Genres;
import com.example.appmovie.model.detailmodel.Languages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class DetailResponse {
    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("belongs_to_collection")
    private
    Collection belongs_to_collection;

    @SerializedName("budget")
    private double budget;

    @SerializedName("genres")
    private
    List<Genres> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private int id;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("production_companies")
    private List<Companies> production_companies;

    @SerializedName("production_countries")
    private
    List<Countries> production_countries;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("revenue")
    private double revenue;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("spoken_languages")
    private
    List<Languages> spoken_languages;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

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
    Collection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public
    double getBudget() {
        return budget;
    }

    public
    List<Genres> getGenres() {
        return genres;
    }

    public
    String getHomepage() {
        return homepage;
    }

    public
    int getId() {
        return id;
    }

    public
    String getImdb_id() {
        return imdb_id;
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
    String getPoster_path() {
        return poster_path;
    }

    public
    List<Companies> getProduction_companies() {
        return production_companies;
    }

    public
    List<Countries> getProduction_countries() {
        return production_countries;
    }

    public
    List<Languages> getSpoken_languages() {
        return spoken_languages;
    }

    public
    String getRelease_date() {
        return release_date;
    }

    public
    double getRevenue() {
        return revenue;
    }

    public
    int getRuntime() {
        return runtime;
    }

    public
    String getStatus() {
        return status;
    }

    public
    String getTagline() {
        return tagline;
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
