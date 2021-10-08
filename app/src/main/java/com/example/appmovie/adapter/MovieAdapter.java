package com.example.appmovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovie.R;
import com.example.appmovie.actions.MovieListener;
import com.example.appmovie.databinding.ItemMovieBinding;
import com.example.appmovie.model.Movie;

import java.util.List;

public
class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private
    List<Movie> list;
    private
    MovieListener movieListener;

    public
    MovieAdapter(List<Movie> list, MovieListener movieListener) {
        this.list = list;
        this.movieListener = movieListener;
    }

    @NonNull
    @Override
    public
    MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_movie, parent, false);
        return new MovieHolder(itemMovieBinding);
    }

    @Override
    public
    void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.setBinding(list.get(position));
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private
        ItemMovieBinding itemMovieBinding;
        public
        MovieHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding=itemMovieBinding;
        }

        public void setBinding(Movie movie){
            itemMovieBinding.setMovie(movie);
            itemMovieBinding.executePendingBindings();
            itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    movieListener.onMovieClick(movie);
                }
            });
        }
    }
}
