package com.example.appmovie.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.databinding.ActivitySearchBinding;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.network.APIService;
import com.example.appmovie.repository.MovieRepository;
import com.example.appmovie.responses.MovieResultSearchResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public
class SearchViewModel extends ViewModel {
    private
    MovieRepository movieRepository;

    public
    SearchViewModel() {
        movieRepository = new MovieRepository();
    }

    public
    Observable<ResultSearch> getNameMovieByQuery(String api_key, String query){
        return movieRepository.getNameByQuery(api_key, query);
    }

    public
    LiveData<MovieResultSearchResponse> getMovieByQuery(String api_key, String query) {
        return movieRepository.getMovieByQuery(api_key, query);
    }

    public
    Observable<String> getSupjectFromView(EditText editText){
        PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public
            void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public
            void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public
            void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });
        return subject;
    }

    public
    void loading(int currentPage, ActivitySearchBinding binding) {
        if (currentPage == 1) {
            if (binding.getIsLoading() != null && binding.getIsLoading()) {
                binding.setIsLoading(false);
            } else {
                binding.setIsLoading(true);
            }
        } else {
            if (binding.getIsLoadingMore() != null && binding.getIsLoadingMore()) {
                binding.setIsLoadingMore(false);
            } else {
                binding.setIsLoadingMore(true);
            }
        }
    }
}
