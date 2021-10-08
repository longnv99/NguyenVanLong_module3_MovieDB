package com.example.appmovie.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.adapter.SimilarMovieAdapter;
import com.example.appmovie.databinding.ActivityDetailBinding;
import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.trailermodel.Trailer;
import com.example.appmovie.responses.DetailResponse;
import com.example.appmovie.responses.SimilarMovieResponse;
import com.example.appmovie.responses.TrailerResponse;
import com.example.appmovie.utility.Utility;
import com.example.appmovie.viewmodel.DetailMovieViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public
class DetailActivity extends AppCompatActivity implements MovieListener {
    private ActivityDetailBinding binding;
    private
    DetailMovieViewModel detailMovieViewModel;
    private List<Trailer> list = new ArrayList<>();
    private List<Movie> listSimilarMovie = new ArrayList<>();
    private
    SimilarMovieAdapter adapter;
    private int currentPage = 1;
    private int totalPage = 1;


    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        doInit();
    }

    private
    void doInit() {
        detailMovieViewModel = new ViewModelProvider(this).get(DetailMovieViewModel.class);
        int movie_id = getIntent().getIntExtra("movie_id",0);

        //call detail
        getDetailMovie(movie_id);

        //call trailer
        getTrailerMovie(movie_id);

        //play trailer
        playTrailer();

        //call similar
        adapter = new SimilarMovieAdapter(listSimilarMovie, this);
        binding.recycleViewSimilar.setHasFixedSize(true);
        binding.recycleViewSimilar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recycleViewSimilar.setAdapter(adapter);
        binding.recycleViewSimilar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public
            void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(binding.recycleViewSimilar.canScrollVertically(1)){
                    if (currentPage <=totalPage){
                        currentPage += 1;
                        getSimilarMovie(movie_id);
                    }
                }
            }
        });
        getSimilarMovie(movie_id);
    }

    private void getDetailMovie(int movie_id){

        detailMovieViewModel.getDataDetailResponse(movie_id, Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(this,
                new Observer<Object>() {
                    @Override
                    public
                    void onChanged(Object o) {
                        if(o instanceof DetailResponse){
                            if((DetailResponse) o != null){
                                binding.setDetail((DetailResponse) o);
                            }
                            else
                                binding.setDetail(null);
                        }
                    }
                });


    }

    private void getTrailerMovie(int movie_id){
        //call trailer
//        detailMovieViewModel.getTrailerMovie(movie_id, Utility.API_KEY, Utility.LANGUAGE).observe(this, new Observer<TrailerResponse>() {
//            @Override
//            public
//            void onChanged(TrailerResponse trailerResponse) {
//                if(trailerResponse != null){
//                    list.addAll(trailerResponse.getResults());
//                }
//            }
//        });

        detailMovieViewModel.getDataDetailResponse(movie_id, Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(this,
                new Observer<Object>() {
                    @Override
                    public
                    void onChanged(Object o) {
                        if(o instanceof TrailerResponse){
                            if((TrailerResponse) o != null){
                                list.addAll(((TrailerResponse) o).getResults());
                            }
                        }
                    }
                });


    }

    private void getSimilarMovie(int movie_id){
        loadingSimilar();
//        detailMovieViewModel.getSimilarMovie(movie_id, Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(this, new Observer<SimilarMovieResponse>() {
//            @Override
//            public
//            void onChanged(SimilarMovieResponse similarMovieResponse) {
//                loadingSimilar();
//                if (similarMovieResponse!=null){
//                    totalPage = similarMovieResponse.getTotal_pages();
//                    if(similarMovieResponse.getResults()!=null){
//                        int oldSize = listSimilarMovie.size();
//                        listSimilarMovie.addAll(similarMovieResponse.getResults());
//                        //adapter notify
//                        adapter.notifyItemRangeInserted(oldSize, listSimilarMovie.size());
//                    }
//                }
//            }
//        });

        detailMovieViewModel.getDataDetailResponse(movie_id, Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(this,
                new Observer<Object>() {
                    @Override
                    public
                    void onChanged(Object o) {
                        loadingSimilar();
                        if(o instanceof SimilarMovieResponse){
                            if ((SimilarMovieResponse) o!=null){
                                totalPage = ((SimilarMovieResponse) o).getTotal_pages();
                                if(((SimilarMovieResponse) o).getResults()!=null){
                                    int oldSize = listSimilarMovie.size();
                                    listSimilarMovie.addAll(((SimilarMovieResponse) o).getResults());
                                    //adapter notify
                                    adapter.notifyItemRangeInserted(oldSize, listSimilarMovie.size());
                                }
                            }
                        }
                    }
                });

    }

    private void playTrailer(){
        binding.youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public
            void onReady(@NotNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                //youTubePlayer.loadVideo(list.get(0).getKey(),0);
                for (Trailer t : list){
                    youTubePlayer.loadVideo(t.getKey(), 0);
                }

            }
        });
    }

    private void loadingSimilar(){
        if (currentPage >= 1){
            if(binding.getIsLoadingSimilar() != null && binding.getIsLoadingSimilar()){
                binding.setIsLoadingSimilar(false);
            }
            else
                binding.setIsLoadingSimilar(true);
        }
    }


    @Override
    public
    void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public
    void onResultSearchClick(Result result) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie_id", result.getId());
        startActivity(intent);
    }
}
