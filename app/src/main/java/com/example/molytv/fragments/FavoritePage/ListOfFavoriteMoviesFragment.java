package com.example.molytv.fragments.FavoritePage;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import com.example.molytv.MoviePlayerActivity;
        import com.example.molytv.R;
        import com.example.molytv.adapters.MovieItemClickListener;
        import com.example.molytv.adapters.MovieListVerticleAdapter;
        import com.example.molytv.models.Movie;
        import com.example.molytv.models.Slide;
        import com.google.android.material.tabs.TabLayout;
        import java.util.ArrayList;
        import java.util.List;

public class ListOfFavoriteMoviesFragment extends Fragment implements MovieItemClickListener {

    private List<Slide> lstSlides;
    private RecyclerView movieListRv;
    Context thisContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_of_fav_movies, container, false);
        thisContext = container.getContext();
        movieListRv = view.findViewById(R.id.Rv_fav_movies_list);

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("MOLY TEASER", "MOLY TV is an online television platform celebrating and showcasing the best of entertainment", R.drawable.silder2two, R.drawable.silder2two, "Moly Tv", "5-Start", "OgGx131D6bc"));
        lstMovies.add(new Movie("MOLY TEASER", "MOLY TV is an online television platform celebrating and showcasing the best of entertainment", R.drawable.img11, R.drawable.img11, "Moly Tv", "5-Start", "OgGx131D6bc"));



//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            lstMovies = (ArrayList<Movie>) bundle.getSerializable("videoList");
//        }


        MovieListVerticleAdapter movieAdapter = new MovieListVerticleAdapter(thisContext,lstMovies,this);
        movieListRv.setAdapter(movieAdapter);
        movieListRv.setLayoutManager(new LinearLayoutManager(thisContext,LinearLayoutManager.VERTICAL,false));

        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent i = new Intent(getActivity(), MoviePlayerActivity.class);
        i.putExtra("videoLink", movie.getStreamingLink());
        startActivity(i);
    }

    @Override
    public void onMovieSlideClick(Slide slide) {

    }
}