package com.example.appmovie.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.network.APIService;
import com.example.appmovie.responses.DetailResponse;
import com.example.appmovie.responses.MovieResponse;
import com.example.appmovie.responses.MovieResultSearchResponse;
import com.example.appmovie.responses.NowPlayingResponse;
import com.example.appmovie.responses.PopularResponse;
import com.example.appmovie.responses.SimilarMovieResponse;
import com.example.appmovie.responses.TopRateResponse;
import com.example.appmovie.responses.TrailerResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class MovieRepository {

    public
    MovieRepository() {

    }

    //observable
    //upcoming + toprate + popular + nowplaying
    public LiveData<Object> getDataMovie(String api_key,String language, int page){
        MutableLiveData<Object> data = new MutableLiveData<>();
        Observable<MovieResponse> upcoming = APIService.apiService.getMovie2(api_key, language, page);
        Observable<TopRateResponse> topRate = APIService.apiService.getTopRate(api_key, language, page);
        Observable<PopularResponse> popular = APIService.apiService.getPopular(api_key, language, page);
        Observable<NowPlayingResponse> nowplaying = APIService.apiService.getNowPlaying(api_key, language, page);

        Observable.merge(upcoming, topRate, popular, nowplaying)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public
                    void accept(Object o) throws Throwable {
                        data.setValue(o);
                    }
                });
        return data;
    }
    public LiveData<ResultSearch> getResultSearch(String api_key,String query, int page){
        MutableLiveData<ResultSearch> data = new MutableLiveData<>();
        APIService.apiService.getResultSearch(api_key, query, page)
                .enqueue(new Callback<ResultSearch>() {
                    @Override
                    public
                    void onResponse(Call<ResultSearch> call, Response<ResultSearch> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public
                    void onFailure(Call<ResultSearch> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<MovieResultSearchResponse> getMovieResultSearch(String api_key, String query){
        MutableLiveData<MovieResultSearchResponse> data = new MutableLiveData<>();
        APIService.apiService.getMovieResultSearch(api_key, query)
                .enqueue(new Callback<MovieResultSearchResponse>() {
                    @Override
                    public
                    void onResponse(Call<MovieResultSearchResponse> call, Response<MovieResultSearchResponse> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public
                    void onFailure(Call<MovieResultSearchResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
