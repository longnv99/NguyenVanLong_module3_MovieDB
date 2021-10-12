package com.example.appmovie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.adapter.MovieAdapter;
import com.example.appmovie.adapter.SearchAdapter;
import com.example.appmovie.databinding.ActivitySearchBinding;
import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.responses.MovieResultSearchResponse;
import com.example.appmovie.utility.Utility;
import com.example.appmovie.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public
class SearchActivity extends AppCompatActivity implements MovieListener {

    private
    MovieViewModel viewModel;
    private ActivitySearchBinding binding;
    private
    MovieAdapter adapter;
    private
    SearchAdapter searchAdapter;
    private
    List<Movie> list = new ArrayList<>();
    private List<Result> listResultSearch = new ArrayList<>();
    private int currentPage = 1;
    private Timer timer;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search);
        doInit();
    }

    private
    void doInit() {
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        binding.recycleview.setHasFixedSize(true);
        adapter = new MovieAdapter(list, this);
        searchAdapter = new SearchAdapter(listResultSearch, this);
        binding.recycleview.setAdapter(adapter);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                onBackPressed();
            }
        });
        binding.edsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public
            void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public
            void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("runHandler", "change");
                if(timer != null)
                    timer.cancel();
            }

            @Override
            public
            void afterTextChanged(Editable s) {
                Log.d("runHandler", "change");
                if( ! s.toString().trim().isEmpty()){
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public
                        void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public
                                void run() {
                                    listResultSearch.clear();
                                    Log.d("runHandler", "r");
                                    loadDataSearch(Utility.API_KEY, s.toString(), currentPage);
                                    binding.edsearch.clearFocus();
                                }
                            });
                        }
                    },800);
                }
                else {
                    listResultSearch.clear();
                    searchAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void loadDataSearch(String api_key, String keyword, int page){
        loading();
        viewModel.getResultSearch(api_key, keyword, page).observe(this, new Observer<ResultSearch>() {
            @Override
            public
            void onChanged(ResultSearch resultSearch) {
                loading();
                if(resultSearch.getResults() != null){
                    Log.d("ss", "ss");
                    binding.recycleview.setVisibility(View.VISIBLE);
                    listResultSearch.addAll(resultSearch.getResults());
                    binding.recycleview.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void loading(){
        if(currentPage == 1){
            if(binding.getIsLoading() != null && binding.getIsLoading()){
                binding.setIsLoading(false);
            }
            else {
                binding.setIsLoading(true);
            }
        }
        else {
            if(binding.getIsLoadingMore() != null && binding.getIsLoadingMore()){
                binding.setIsLoadingMore(false);
            }
            else {
                binding.setIsLoadingMore(true);
            }
        }
    }


    @Override
    public
    void onMovieClick(Movie movie) {
        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public
    void onResultSearchClick(Result result) {
        loading();
        viewModel.getMovieResultSearch(Utility.API_KEY, result.getName()).observe(this, new Observer<MovieResultSearchResponse>() {
            @Override
            public
            void onChanged(MovieResultSearchResponse movieResultSearchResponse) {
                loading();
                list.clear();
                int oldSize = list.size();
                list.addAll(movieResultSearchResponse.getResults());
                binding.recycleview.setAdapter(adapter);
                adapter.notifyItemRangeInserted(oldSize, list.size());
            }
        });
    }
}