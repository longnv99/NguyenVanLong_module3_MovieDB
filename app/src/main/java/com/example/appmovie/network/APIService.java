package com.example.appmovie.network;

import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.responses.DetailResponse;
import com.example.appmovie.responses.MovieResponse;
import com.example.appmovie.responses.MovieResultSearchResponse;
import com.example.appmovie.responses.NowPlayingResponse;
import com.example.appmovie.responses.PopularResponse;
import com.example.appmovie.responses.SimilarMovieResponse;
import com.example.appmovie.responses.TopRateResponse;
import com.example.appmovie.responses.TrailerResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public
interface APIService {
    //Link API upcoming movie
    //https://api.themoviedb.org/3/movie/upcoming?api_key=c2316184af86b621c7840f188495ac4d&language=en-US&page=1
    // link detail https://api.themoviedb.org/3/movie/{movie_id}?api_key=c2316184af86b621c7840f188495ac4d&language=en-US
    // link video yt https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=c2316184af86b621c7840f188495ac4d&language=en-US

    APIService apiService = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(APIService.class);

    //observable
    @GET("3/movie/upcoming")
    Observable<MovieResponse> getMovie2(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    @GET("3/movie/top_rated")
    Observable<TopRateResponse> getTopRate(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/movie/popular")
    Observable<PopularResponse> getPopular(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/movie/now_playing")
    Observable<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/movie/{movie_id}")
    Observable<DetailResponse> getDetailMovie2(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language);

    @GET("/3/movie/{movie_id}/videos")
    Observable<TrailerResponse> getTrailerMovie2(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language);

    @GET("/3/movie/{movie_id}/similar")
    Observable<SimilarMovieResponse> getSimilarMovie2(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    //get list name
    @GET("/3/search/keyword")
    Call<ResultSearch> getResultSearch(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") int page);

    //get list movie
    @GET("/3/search/movie")
    Call<MovieResultSearchResponse> getMovieResultSearch(
            @Query("api_key") String api_key,
            @Query("query") String query
    );
}
