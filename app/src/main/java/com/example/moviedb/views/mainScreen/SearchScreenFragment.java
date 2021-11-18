package com.example.moviedb.views.mainScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.adapter.MoviesAdapter;
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

    private SearchScreenViewModel searchScreenViewModel;
    private FragmentSearchScreenBinding binding;
    private MoviesAdapter moviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchScreenBinding.inflate(getLayoutInflater());
        View view;
        view= binding.getRoot();
        searchScreenViewModel= ViewModelProviders.of(this).get(SearchScreenViewModel.class);
        this.initObservers();//*
        initComponents();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observeModelData(binding.etSearch.getText().toString());//*
            }
        });
            return view;
    }

    private void initComponents() {
        moviesAdapter=new MoviesAdapter(this);
        binding.rvSearchResult.setAdapter(moviesAdapter);

    }

    private void observeModelData(String movieName) {
        searchScreenViewModel.getMovieData(movieName);
    }

    private void initObservers(){

        searchScreenViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                prepareRecycler(movieModels);
            }
        });
    }

    private void prepareRecycler(List<MovieModel> movieModels) {
        moviesAdapter.setAdapterMovieList(movieModels);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}