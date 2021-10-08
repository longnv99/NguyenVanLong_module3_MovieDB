package com.example.appmovie.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmovie.activity.DetailActivity;
import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.adapter.MovieAdapter;
import com.example.appmovie.adapter.SearchAdapter;
import com.example.appmovie.databinding.FragmentUpcomingBinding;
import com.example.appmovie.model.Movie;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.responses.MovieResponse;
import com.example.appmovie.utility.Utility;
import com.example.appmovie.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public
class UpcomingFragment extends Fragment implements MovieListener {
    private
    MovieViewModel viewModel;
    private
    MovieAdapter adapter;
    private
    SearchAdapter searchAdapter;
    private
    FragmentUpcomingBinding binding;
    private
    List<Movie> list = new ArrayList<>();
    private List<Result> listResultSearch = new ArrayList<>();
    private int currentPage = 1;
    private int currentPageSearchResult = 1;
    private int totalPage = 1;

    @Override
    public
    View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_upcoming, container, false);
        //init binding
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_upcoming,
                container,
                false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }

    @Override
    public
    void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            UpcomingFragment fragment = new UpcomingFragment();
        }
        //init();
    }

    @Override
    public
    void onDestroy() {
        super.onDestroy();
        binding.upcomingRecyclerView.setVisibility(View.INVISIBLE);

    }

    @Override
    public
    void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }


    private
    void init() {
//        binding.swipeRefresh.setOnRefreshListener(this);
//        binding.swipeRefresh.setColorSchemeResources(R.color.black);
        binding.upcomingRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        adapter = new MovieAdapter(list, this);
        searchAdapter = new SearchAdapter(listResultSearch, this);
        binding.upcomingRecyclerView.setAdapter(adapter);
        binding.upcomingRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public
            void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                int topRowVeticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
//                binding.swipeRefresh.setEnabled(topRowVeticalPosition >= 0);
                if(binding.upcomingRecyclerView.canScrollVertically(1)){
                    if (currentPage <= totalPage){
                        currentPage +=1;
                        getUpcomingMovie();
                    }
                }
            }
        });
        getUpcomingMovie();
    }


    //loadData Movie in Upcoming
    private void getUpcomingMovie(){
        loading();
                viewModel.getDataMovie(Utility.API_KEY, Utility.LANGUAGE, currentPage).observe(getViewLifecycleOwner(), o -> {
                    if (o instanceof MovieResponse) {
                        loading();
                        if (((MovieResponse) o).getResults() != null) {
                            totalPage = ((MovieResponse) o).getTotal_pages();
                            if (((MovieResponse) o).getResults() != null) {
                                int oldSize = list.size();
                                list.addAll(((MovieResponse) o).getResults());
                                adapter.notifyItemRangeInserted(oldSize, list.size());
                            }
                        }
                    }
                });


    }

    //load more progressbar
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
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public
    void onResultSearchClick(Result result) {
    }


}