package com.example.appmovie.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.databinding.FragmentUpcomingBinding;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.repository.MovieRepository;
import com.example.appmovie.responses.MovieResponse;
import com.example.appmovie.responses.MovieResultSearchResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

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
}
