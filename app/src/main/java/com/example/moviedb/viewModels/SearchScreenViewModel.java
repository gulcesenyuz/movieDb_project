package com.example.moviedb.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.models.MovieModel;
import com.example.moviedb.repositories.MovieRepository;

import java.util.List;

public class SearchScreenViewModel extends ViewModel {
    private MovieRepository movieRepository;

    //constructor
    public SearchScreenViewModel() {
        movieRepository=MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies() ;
    }

    public void searchMovie(String query,int pageNumber){
        Log.d("TEST","searchMovie SearchScreenViewModel " );

        movieRepository.searchMovie(query, pageNumber);
    }
}
