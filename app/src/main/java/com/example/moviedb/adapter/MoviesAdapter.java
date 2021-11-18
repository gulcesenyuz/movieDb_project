package com.example.moviedb.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;
import com.example.moviedb.databinding.RvItemBinding;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.views.mainScreen.SearchScreenFragment;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private static final String TAG= "RecyclerViewAdapter";
    private List<MovieModel> movies;
    private SearchScreenFragment context;

    public MoviesAdapter(SearchScreenFragment context){
        this.context= context;
        this.movies=new ArrayList<>();
    }

    public void setAdapterMovieList(List<MovieModel> movies){
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding binding=RvItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder called");
        MovieModel movieModel=movies.get(position);
        if(movieModel.getTitle() != null){
            holder.binding.tvMovieName.setText(movieModel.getTitle());
            Log.d(TAG, holder.binding.tvMovieName.toString());
        }else   Log.d(TAG,"null");
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
    RvItemBinding binding;
        public ViewHolder(RvItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
