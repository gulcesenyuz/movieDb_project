package com.example.moviedb.views;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.databinding.FragmentSearchScreenBinding;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.request.Service;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.Credentials;
import com.example.moviedb.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchScreenFragment extends Fragment {

    private final String TAG = "Custom_Tag_Main";
    private final String TEST = "TEST";


    private FragmentSearchScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchScreenBinding.inflate(getLayoutInflater());
        View view;
        view= binding.getRoot();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: "+ binding.etSearch.getText().toString());
                GetRetrofitResponse();

            }
        });
            return view;
    }

    private void GetRetrofitResponse() {
        Log.d("TEST","GetRetrofitResponse" );
        MovieApi movieApi= Service.getMovieApi();
        Log.d("TEST","1" );
        Call<MovieSearchResponse> responseCall= movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        binding.etSearch.getText().toString(),
                        "1"
                );
        Log.d("TEST", String.valueOf(responseCall.request()));

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.v("Tag", "the response" +response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for(MovieModel movie: movies){
                        Log.v("Tag","The title movie:  "+ movie.getTitle());
                    }
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Search Screen Fragment");

    }

}