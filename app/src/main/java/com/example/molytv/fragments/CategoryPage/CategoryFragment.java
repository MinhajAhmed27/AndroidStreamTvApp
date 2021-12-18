package com.example.molytv.fragments.CategoryPage;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.molytv.R;
import com.example.molytv.fragments.ListOfMoviesFragment;
import com.google.android.material.tabs.TabLayout;

public class CategoryFragment extends Fragment{

    View view;
    TabLayout tabLayout;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity()!=null){
            tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
            tabLayout.setVisibility(View.VISIBLE);
        }


    }

    //TO STOP ORIENTATION
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(getActivity()!=null){
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if(getActivity()!=null){
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//        }
//    }

    ImageView catImageView1,catImageView2,catImageView3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null){
            view =  inflater.inflate(R.layout.fragment_category,container,false);
        }


        catImageView1 = view.findViewById(R.id.CatImageView1);
        catImageView2 = view.findViewById(R.id.CatImageView2);
        catImageView3 = view.findViewById(R.id.CatImageView3);
        ListOfMoviesFragment listOfMoviesFragment = new ListOfMoviesFragment ();
        CategoryMovieDetailFragment categoryMovieDetailFragment = new CategoryMovieDetailFragment();

        //catImageView1.setImageResource(mData.get(i).getThumbnail());

        catImageView1.setOnClickListener(v -> {

            replaceFragment(listOfMoviesFragment);
        });
        catImageView2.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("videoList","");
            categoryMovieDetailFragment.setArguments(bundle);

            replaceFragment(categoryMovieDetailFragment);
        });
        catImageView3.setOnClickListener(v -> {

            replaceFragment(listOfMoviesFragment);
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
