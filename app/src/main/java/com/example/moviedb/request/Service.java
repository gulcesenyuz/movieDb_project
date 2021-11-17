package com.example.moviedb.request;

import android.util.Log;

import com.example.moviedb.utils.Credentials;
import com.example.moviedb.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder=
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit= retrofitBuilder.build();
    private static MovieApi movieApi=retrofit.create(MovieApi.class);
    public static MovieApi getMovieApi(){
        Log.d("TEST","getMovieApi Sevice");
        Log.d("TEST",movieApi.toString());

        return movieApi;
    }

}
