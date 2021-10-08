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
import com.example.appmovie.databinding.ItemSimilarMovieBinding;
import com.example.appmovie.model.Movie;

import java.util.List;

public
class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.SimilarHolder>{
    private
    List<Movie> list;

    private
    MovieListener movieListener;

    public
    SimilarMovieAdapter(List<Movie> list, MovieListener movieListener) {
        this.list = list;
        this.movieListener = movieListener;
    }

    @NonNull
    @Override
    public
    SimilarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSimilarMovieBinding itemSimilarMovieBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_similar_movie,
                parent,
                false
        );
        return new SimilarHolder(itemSimilarMovieBinding);
    }

    @Override
    public
    void onBindViewHolder(@NonNull SimilarHolder holder, int position) {
        holder.setBinding(list.get(position));
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    public class SimilarHolder extends RecyclerView.ViewHolder {
        private
        ItemSimilarMovieBinding itemSimilarMovieBinding;

        public
        SimilarHolder(ItemSimilarMovieBinding itemSimilarMovieBinding) {
            super(itemSimilarMovieBinding.getRoot());
            this.itemSimilarMovieBinding = itemSimilarMovieBinding;
        }

        public void setBinding(Movie movie){
            itemSimilarMovieBinding.setSimilar(movie);
            itemSimilarMovieBinding.executePendingBindings();
            itemSimilarMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    movieListener.onMovieClick(movie);
                }
            });
        }
    }
}
