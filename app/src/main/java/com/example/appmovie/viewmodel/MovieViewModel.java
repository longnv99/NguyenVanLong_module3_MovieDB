package com.example.appmovie.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.databinding.FragmentUpcomingBinding;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.repository.MovieRepository;
import com.example.appmovie.responses.MovieResponse;
import com.example.appmovie.responses.MovieResultSearchResponse;

public
class MovieViewModel extends ViewModel {
    private
    MovieRepository movieRepository;

    public
    MovieViewModel() {
        movieRepository = new MovieRepository();
    }

    //data movie
    public
    LiveData<Object> getDataMovie(String api_key, String language, int page) {
        return movieRepository.getDataMovie(api_key, language, page);
    }

    public
    LiveData<ResultSearch> getResultSearch(String api_key, String query, int page) {
        return movieRepository.getResultSearch(api_key, query, page);
    }

    public
    LiveData<MovieResultSearchResponse> getMovieResultSearch(String api_key, String query) {
        return movieRepository.getMovieResultSearch(api_key, query);
    }

}
