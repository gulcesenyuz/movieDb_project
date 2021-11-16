package com.example.moviedb.response;

import com.example.moviedb.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//for single movie request
public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;
    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString(){
        return "MovieResponse{"+"movie= "+movie+"}";
    }
}
