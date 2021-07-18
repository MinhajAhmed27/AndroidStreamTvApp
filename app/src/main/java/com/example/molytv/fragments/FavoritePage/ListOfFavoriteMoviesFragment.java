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
        tabLayout.setVisibility(View.GONE);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_of_movies, container, false);
        thisContext = container.getContext();
        movieListRv = view.findViewById(R.id.Rv_movies_list);

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("MOLY TEASER", "MOLY TV is an online television platform celebrating and showcasing the best of entertainment", R.drawable.silder2two, R.drawable.silder2two, "Moly Tv", "5-Start", "OgGx131D6bc"));
        lstMovies.add(new Movie("ESCAPE I", "ESCAPE IS AN ACTION DRAMA, IT IS ABOUT  RELATIONSHIP, FRIENDSHIP AND IT ALSO INVOLVES CRIME", R.drawable.slide2, R.drawable.slide2, "Moly Tv", "5-Start", "P6iZhLvKbVE"));
        lstMovies.add(new Movie("MOLY TEASER", "MOLY TV is an online television platform celebrating and showcasing the best of entertainment", R.drawable.img11, R.drawable.img11, "Moly Tv", "5-Start", "OgGx131D6bc"));
        lstMovies.add(new Movie("ESCAPE I", "ESCAPE IS AN ACTION DRAMA, IT IS ABOUT  RELATIONSHIP, FRIENDSHIP AND IT ALSO INVOLVES CRIME", R.drawable.silder2two, R.drawable.slider14, "Moly Tv", "5-Start", "P6iZhLvKbVE"));
        lstMovies.add(new Movie("MOLY TEASER", "MOLY TV is an online television platform celebrating and showcasing the best of entertainment", R.drawable.slider14, R.drawable.molyshow, "Moly Tv", "5-Start", "OgGx131D6bc"));
        lstMovies.add(new Movie("ESCAPE I", "ESCAPE IS AN ACTION DRAMA, IT IS ABOUT  RELATIONSHIP, FRIENDSHIP AND IT ALSO INVOLVES CRIME", R.drawable.img13, R.drawable.slider14, "Moly Tv", "5-Start", "P6iZhLvKbVE"));



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