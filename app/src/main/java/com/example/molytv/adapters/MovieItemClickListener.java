package com.example.molytv.adapters;

import android.widget.ImageView;

import com.example.molytv.models.Movie;
import com.example.molytv.models.Slide;


public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity
    void onMovieHomeClick(Slide slide);
}
