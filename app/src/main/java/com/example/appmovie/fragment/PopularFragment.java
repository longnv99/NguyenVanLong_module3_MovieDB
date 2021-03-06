package com.example.appmovie.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmovie.activity.DetailActivity;
import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.adapter.MovieAdapter;
import com.example.appmovie.databinding.FragmentPopularBinding;
import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.responses.PopularResponse;
import com.example.appmovie.utility.Utility;
import com.example.appmovie.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public
class PopularFragment extends Fragment implements MovieListener {
    private
    MovieViewModel viewModel;
    private
    MovieAdapter adapter;
    private
    FragmentPopularBinding binding;
    private final
    List<Movie> list = new ArrayList<>();
    private int currentPage = 1;
    private int totalPage = 1;

    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //-return inflater.inflate(R.layout.fragment_popular, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);
        binding.setLifecycleOwner(this);
        init();
        return binding.getRoot();
    }

    @Override
    public
    void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private
    void init() {
        binding.RecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        adapter = new MovieAdapter(list, this);
        binding.RecyclerView.setAdapter(adapter);
        binding.RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public
            void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (binding.RecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalPage) {
                        currentPage += 1;
                        getPopularMovie();
                    }
                }
            }

            @Override
            public
            void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        getPopularMovie();
    }

    private
    void getPopularMovie() {
        loading();
        viewModel.getDataMovie(Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(getViewLifecycleOwner(), o -> {
            if (o instanceof PopularResponse) {
                loading();
                if (o != null) {
                    totalPage = ((PopularResponse) o).getTotal_pages();
                    if (((PopularResponse) o).getResults() != null) {
                        int oldSize = list.size();
                        list.addAll(((PopularResponse) o).getResults());
                        adapter.notifyItemRangeInserted(oldSize, list.size());
                    }
                }
            }
        });
    }

    private
    void loading() {
        if (currentPage == 1) {
            binding.setIsLoading(binding.getIsLoading() == null || !binding.getIsLoading());
        } else {
            binding.setIsLoadingMore(binding.getIsLoadingMore() == null || !binding.getIsLoadingMore());
        }
    }

    @Override
    public
    void onMovieClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public
    void onResultSearchClick(Result result) {
    }
}