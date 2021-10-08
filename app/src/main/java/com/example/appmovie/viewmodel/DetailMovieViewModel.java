package com.example.appmovie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.repository.DetailMovieRepository;

public
class DetailMovieViewModel extends ViewModel {
    private
    DetailMovieRepository detailMovieRepository;

    public
    DetailMovieViewModel() {
        detailMovieRepository = new DetailMovieRepository();
    }

//    public
//    LiveData<DetailResponse> getDetailMovie(int movie_id, String api_key, String language){
//        return detailMovieRepository.getDetailMovie(movie_id, api_key, language);
//    }
//
//    public LiveData<TrailerResponse> getTrailerMovie(int movie_id, String api_key,String language){
//        return detailMovieRepository.getTrailerMovie(movie_id, api_key, language);
//    }
//
//    public LiveData<SimilarMovieResponse> getSimilarMovie(int movie_id, String api_key, String language, int page){
//        return detailMovieRepository.getSimilarMovie(movie_id, api_key, language, page);
//    }

    public LiveData<Object> getDataDetailResponse(int movie_id, String api_key, String language, int page){
        return detailMovieRepository.getDetailResponse2(movie_id, api_key, language, page);
    }
}
