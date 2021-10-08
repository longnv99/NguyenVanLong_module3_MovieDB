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
import com.example.appmovie.databinding.ItemSearchResultBinding;
import com.example.appmovie.model.searchmodel.Result;

import java.util.List;

public
class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private List<Result> list;
    private
    MovieListener movieListener;

    public
    SearchAdapter(List<Result> list, MovieListener movieListener) {
        this.list = list;
        this.movieListener = movieListener;
    }

    @NonNull
    @Override
    public
    SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchResultBinding itemSearchResultBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_search_result, parent, false);
        return new SearchViewHolder(itemSearchResultBinding);
    }

    @Override
    public
    void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.setBinding(list.get(position));
    }

    @Override
    public
    int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private
        ItemSearchResultBinding itemSearchResultBinding;
        public
        SearchViewHolder(ItemSearchResultBinding itemSearchResultBinding) {
            super(itemSearchResultBinding.getRoot());
            this.itemSearchResultBinding = itemSearchResultBinding;
        }

        public void setBinding(Result result){
            itemSearchResultBinding.setResult(result);
            itemSearchResultBinding.executePendingBindings();
            itemSearchResultBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    movieListener.onResultSearchClick(result);
                }
            });
        }
    }
}
