package com.example.moviedb.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private MovieApiClient movieApiClient;
    private static MovieRepository instance;
    public static  MovieRepository getInstance(){
        if(instance==null){
            instance=new MovieRepository();
        }
        return instance;
    }
    private MovieRepository(){
       movieApiClient=MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public void searchMovie(String query,int pageNumber){
        movieApiClient.searchMovies(query,pageNumber);
    }
}
