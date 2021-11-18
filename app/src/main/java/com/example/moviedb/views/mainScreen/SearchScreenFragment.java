package com.example.moviedb.views.mainScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.databinding.FragmentSearchScreenBinding;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.request.RequestService;
import com.example.moviedb.response.MovieSearchResponse;
import com.example.moviedb.utils.Credentials;
import com.example.moviedb.request.MovieApiRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchScreenFragment extends Fragment {

    private final String TAG = "Custom_Tag_Main";
    private SearchScreenViewModel searchScreenViewModel;
    private FragmentSearchScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchScreenBinding.inflate(getLayoutInflater());
        View view;
        view= binding.getRoot();
        searchScreenViewModel= ViewModelProviders.of(this).get(SearchScreenViewModel.class);
        this.initObservers();


        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: "+ binding.etSearch.getText().toString());
                observeModelData(binding.etSearch.getText().toString());
            }
        });
            return view;
    }

    private void observeModelData(String movieName) {
        searchScreenViewModel.getMovieData(movieName);
    }

    private void initObservers(){
        searchScreenViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                Log.d(TAG, "onChanged: ");
                //ekle
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Search Screen Fragment");

    }

}