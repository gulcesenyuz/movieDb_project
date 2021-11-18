package com.example.moviedb.views.mainScreen;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.request.MovieApiRequest;
import com.example.moviedb.request.RequestService;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchScreenViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> movieList=new MutableLiveData<>();

    public void getMovieData(String movieName){
        MovieApiRequest movieApiRequest = RequestService.getMovieApi();
        Call<MovieSearchResponse> responseCall= movieApiRequest
                .searchMovie(
                        Credentials.API_KEY,
                        movieName,
                        "1"
                );
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.v("Tag", "the response" +response.body().toString());
                    movieList.postValue(response.body().getMovies());
                    //List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                }
                else{
                    try {
                        Log.v("Tag","Error "+ response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<List<MovieModel>>getMovieList(){
        return movieList;
    }
}
