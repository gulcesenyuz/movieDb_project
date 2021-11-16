package com.example.moviedb.utils;

import com.example.moviedb.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://api.themoviedb.org/3/search/movie?api_key=f6d1ac9df10f0016bf98195fd027caa9&query=Jack%20Reacher&page=1
public interface MovieApi {

    //Search for movies
    @GET( "search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );
}
