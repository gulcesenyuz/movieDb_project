package com.example.moviedb.request;

import android.util.Log;

import com.example.moviedb.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {

    private static Retrofit.Builder retrofitBuilder=
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit= retrofitBuilder.build();
    private static MovieApiRequest movieApiRequest =retrofit.create(MovieApiRequest.class);
    public static MovieApiRequest getMovieApi(){
        Log.d("TEST","getMovieApi Sevice");
        return movieApiRequest;
    }
}
