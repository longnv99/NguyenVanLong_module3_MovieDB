package com.example.appmovie.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.databinding.ActivityDetailBinding;
import com.example.appmovie.model.searchmodel.Result;
import com.example.appmovie.model.trailermodel.Trailer;
import com.example.appmovie.repository.DetailMovieRepository;
import com.example.appmovie.responses.DetailResponse;
import com.example.appmovie.utility.Utility;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public
class DetailMovieViewModel extends ViewModel {
    private
    DetailMovieRepository detailMovieRepository;

    public
    DetailMovieViewModel() {
        detailMovieRepository = new DetailMovieRepository();
    }

    public
    LiveData<Object> getDataDetailResponse(int movie_id, String api_key, String language, int page) {
        return detailMovieRepository.getDetailResponse2(movie_id, api_key, language, page);
    }

    public
    void loadingSimilar(int currentPage, ActivityDetailBinding binding) {
        if (currentPage >= 1) {
            if (binding.getIsLoadingSimilar() != null && binding.getIsLoadingSimilar()) {
                binding.setIsLoadingSimilar(false);
            } else
                binding.setIsLoadingSimilar(true);
        }
    }

    public
    void playTrailer(ActivityDetailBinding binding, List<Trailer> list) {
        binding.youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public
            void onReady(@NotNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                //youTubePlayer.loadVideo(list.get(0).getKey(),0);
                for (Trailer t : list) {
                    youTubePlayer.loadVideo(t.getKey(), 0);
                }
            }
        });
    }

}
