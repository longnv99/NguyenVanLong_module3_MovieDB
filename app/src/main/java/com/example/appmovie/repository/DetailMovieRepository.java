package com.example.appmovie.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmovie.network.APIService;
import com.example.appmovie.responses.DetailResponse;
import com.example.appmovie.responses.SimilarMovieResponse;
import com.example.appmovie.responses.TrailerResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class DetailMovieRepository {

    public
    DetailMovieRepository() {
    }

    //mutiple using merge
    public
    LiveData<Object> getDetailResponse2(int movie_id, String api_key, String language, int page) {
        MutableLiveData<Object> data = new MutableLiveData<>();
        Observable<DetailResponse> detailObservable = APIService.apiService.getDetailMovie2(movie_id, api_key, language);
        Observable<TrailerResponse> trailerObservable = APIService.apiService.getTrailerMovie2(movie_id, api_key, language);
        Observable<SimilarMovieResponse> similarObservable = APIService.apiService.getSimilarMovie2(movie_id, api_key, language, page);

        Observable.merge(detailObservable, trailerObservable, similarObservable)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> data.setValue(o));
        return data;
    }
}
