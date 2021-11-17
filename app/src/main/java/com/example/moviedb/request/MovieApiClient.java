package com.example.moviedb.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.Credentials;
import com.example.moviedb.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mldMovies;
    private static MovieApiClient instance;

    public static MovieApiClient getInstance(){
        if(instance==null){
            instance=new MovieApiClient();
        }
        return instance;
    }
    private MovieApiClient(){
        mldMovies=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mldMovies;
    }

    public void searchMovies(String query,int pageNumber){
        Log.d("TEST","searchMovies apiclient");

        try {
            Response response= getMovies(query, pageNumber).execute();
            Log.d("HATA",response.message());
            if (response.code()==200){
               List<MovieModel> list =new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
               if (pageNumber==1){
                   mldMovies.postValue(list);
               }else{
                   List<MovieModel> currentMovies=mldMovies.getValue();
                   currentMovies.addAll(list);
                   mldMovies.postValue(currentMovies);
               }
            }else {
                String error= response.errorBody().string();
                Log.v("Tag","Error code: "+error);
                mldMovies.postValue(null);

            }
        } catch (IOException e) {
            e.printStackTrace();
            mldMovies.postValue(null);

        }

    }
    private Call<MovieSearchResponse> getMovies(String query ,int pageNumber){
        Log.d("TEST","getMovies apiclient");

        return Service.getMovieApi().searchMovie(
                Credentials.API_KEY,
                query,
                String.valueOf(pageNumber)
        );
    };

}
