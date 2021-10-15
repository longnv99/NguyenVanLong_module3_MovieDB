package com.example.appmovie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.adapter.MovieAdapter;
import com.example.appmovie.adapter.SearchAdapter;
import com.example.appmovie.databinding.ActivitySearchBinding;
import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.network.APIService;
import com.example.appmovie.utility.Utility;
import com.example.appmovie.viewmodel.MovieViewModel;
import com.example.appmovie.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public
class SearchActivity extends AppCompatActivity implements MovieListener {

    private
    SearchViewModel viewModel;
    private ActivitySearchBinding binding;
    private
    MovieAdapter adapter;
    private
    SearchAdapter searchAdapter;
    private
    List<Movie> list = new ArrayList<>();
    private List<Result> listResultSearch = new ArrayList<>();
    private int currentPage = 1;
    private
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        doInit();
        searchNameByQuery();
    }

    @Override
    protected
    void onDestroy() {
        super.onDestroy();
        binding = null;
        //dispose
        disposable.dispose();
    }

    private
    void doInit() {
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.recycleview.setHasFixedSize(true);
        adapter = new MovieAdapter(list, this);
        searchAdapter = new SearchAdapter(listResultSearch, this);
        binding.recycleview.setAdapter(adapter);
        binding.back.setOnClickListener(v -> onBackPressed());
    }

    private
    void loadDataSearch(String api_key, String keyword, int page) {
        viewModel.loading(currentPage, binding);
        viewModel.getResultSearch(api_key, keyword, page).observe(this, resultSearch -> {
            viewModel.loading(currentPage, binding);
            if (resultSearch.getResults() != null) {
                Log.d("ss", "ss");
                binding.recycleview.setVisibility(View.VISIBLE);
                listResultSearch.clear();
                listResultSearch.addAll(resultSearch.getResults());
                binding.recycleview.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }
        });

    }

    private
    void searchNameByQuery() {
        //using CompositeDisposable managerment
        //using Publish Subject
        disposable.add(viewModel.getSupjectFromView(binding.edsearch)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(s -> {
                    if (s.isEmpty())
                        return false;
                    else
                        return true;
                })
                .distinctUntilChanged()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    loadDataSearch(Utility.API_KEY, s, currentPage);
                }));
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
        viewModel.loading(currentPage, binding);
        viewModel.getMovieResultSearch(Utility.API_KEY, result.getName()).observe(this, movieResultSearchResponse -> {
            viewModel.loading(currentPage, binding);
            list.clear();
            int oldSize = list.size();
            list.addAll(movieResultSearchResponse.getResults());
            binding.recycleview.setAdapter(adapter);
            adapter.notifyItemRangeInserted(oldSize, list.size());
        });
    }
}