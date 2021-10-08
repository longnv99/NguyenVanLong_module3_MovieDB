package com.example.appmovie.actions;

import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;

public
interface MovieListener {
    void onMovieClick(Movie movie);
    void onResultSearchClick(Result result);
}
