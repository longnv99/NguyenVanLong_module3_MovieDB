package com.example.appmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appmovie.model.searchmodel.ResultSearch;
import com.example.appmovie.network.APIService;
import com.example.appmovie.viewmodel.MovieViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class TestRetrofitActivity extends AppCompatActivity {

    MovieViewModel viewModel;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        viewModel = new MovieViewModel();
    }

    public
    void onClickTest(View view) {
//        APIService.apiService.getMovie("c2316184af86b621c7840f188495ac4d","en-US", 1).enqueue(new Callback<MovieResponse>() {
//            @Override
//            public
//            void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                Toast.makeText(TestRetrofitActivity.this, "call successfull", Toast.LENGTH_LONG).show();
//                if(response.code()==200)
//                {
//                    Log.v("Tag", "the response "+response.body().toString());
//                    List<Movie> list = new ArrayList<>(response.body().getResults());
//                    for (Movie movie : list){
//                        Log.v("Tag", "The release title"+movie.getTitle());
//                    }
//
//                }
//                else if(response.code()==400){
//                    Log.v("Tag","Error r");
//                }
//                else {
//                    try {
//                        Log.v("Tag", "Error"+response.errorBody().string());
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public
//            void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.v("Tag","Error r");
//            }
//        });
//        APIService.apiService.getMovie2("c2316184af86b621c7840f188495ac4d","en-US", 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MovieResponse>() {
//                    @Override
//                    public
//                    void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public
//                    void onNext(@NonNull MovieResponse movieResponse) {
//                        Toast.makeText(TestRetrofitActivity.this, "successfull", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public
//                    void onError(@NonNull Throwable e) {
//                        Toast.makeText(TestRetrofitActivity.this, "error", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public
//                    void onComplete() {
//
//                    }
//                });
//        APIService.apiService.getResultSearch("c2316184af86b621c7840f188495ac4d","crush", 1).enqueue(new Callback<ResultSearch>() {
//            @Override
//            public
//            void onResponse(Call<ResultSearch> call, Response<ResultSearch> response) {
//                Toast.makeText(TestRetrofitActivity.this, "call successfull "+response.body().getTotal_results(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public
//            void onFailure(Call<ResultSearch> call, Throwable t) {
//
//            }
//        });
    }
}