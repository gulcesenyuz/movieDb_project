package com.example.moviedb.views;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.moviedb.viewModels.SearchScreenViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchScreenFragment extends Fragment {

    private final String TAG = "Custom_Tag_Main";
    private final String TEST = "DENEME";
    private FragmentSearchScreenBinding binding;
    private SearchScreenViewModel searchScreenViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchScreenBinding.inflate(getLayoutInflater());
        View view;
        view= binding.getRoot();
        searchScreenViewModel= new ViewModelProvider(this).get(SearchScreenViewModel.class);
        ObserveChange();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TEST"," binding.btnSearch. SearchScreenFragment");

                searchMovie(binding.etSearch.getText().toString(),1);
            }
        });

            return view;
    }
    //Observe data change
    private void ObserveChange(){
        Log.d("TEST","ObserveChange SearchScreenFragment");

        searchScreenViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if(movieModels != null){

                    for(MovieModel movieModel:movieModels){
                        Log.d("TEST","onChanged: "+movieModel.getTitle());

                    }
                }
            }
        });
    }

    private void searchMovie(String query,int pageNumber){
        searchScreenViewModel.searchMovie(query, pageNumber);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Search Screen Fragment");

    }

}