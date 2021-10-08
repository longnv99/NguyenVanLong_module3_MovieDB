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

    //single call
//    public
//    LiveData<DetailResponse> getDetailMovie(int movie_id, String api_key, String language){
//        MutableLiveData<DetailResponse> data = new MutableLiveData<>();
//        APIService.apiService.getDetailMovie(movie_id, api_key, language).enqueue(new Callback<DetailResponse>() {
//            @Override
//            public
//            void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public
//            void onFailure(Call<DetailResponse> call, Throwable t) {
//                data.setValue(null);
//            }
//        });
//        return data;
//    }
//
//    public
//    LiveData<TrailerResponse> getTrailerMovie(int movie_id, String api_key, String language){
//        MutableLiveData<TrailerResponse> data = new MutableLiveData<>();
//        APIService.apiService.getTrailerMovie(movie_id, api_key, language).enqueue(new Callback<TrailerResponse>() {
//            @Override
//            public
//            void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public
//            void onFailure(Call<TrailerResponse> call, Throwable t) {
//                data.setValue(null);
//            }
//        });
//        return data;
//    }
//
//    public LiveData<SimilarMovieResponse> getSimilarMovie(int movie_id, String api_key, String language, int page){
//        MutableLiveData<SimilarMovieResponse> data = new MutableLiveData<>();
//        APIService.apiService.getSimilarMovie(movie_id, api_key, language, page).enqueue(new Callback<SimilarMovieResponse>() {
//            @Override
//            public
//            void onResponse(Call<SimilarMovieResponse> call, Response<SimilarMovieResponse> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public
//            void onFailure(Call<SimilarMovieResponse> call, Throwable t) {
//                data.setValue(null);
//            }
//        });
//        return data;
//    }

    //mutiple using merge
    public LiveData<Object> getDetailResponse2(int movie_id, String api_key, String language, int page) {
        MutableLiveData<Object> data = new MutableLiveData<>();
        Observable<DetailResponse> detailObservable = APIService.apiService.getDetailMovie2(movie_id, api_key, language);
        Observable<TrailerResponse> trailerObservable = APIService.apiService.getTrailerMovie2(movie_id, api_key, language);
        Observable<SimilarMovieResponse> similarObservable = APIService.apiService.getSimilarMovie2(movie_id, api_key, language, page);

        Observable.merge(detailObservable, trailerObservable, similarObservable)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public
                    void accept(Object o) throws Throwable {
                        data.setValue(o);
                    }
                });
        return data;
    }
}
