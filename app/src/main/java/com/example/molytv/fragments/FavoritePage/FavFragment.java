package com.example.molytv.fragments.FavoritePage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.molytv.R;
import com.google.android.material.tabs.TabLayout;

public class FavFragment extends Fragment {
//
//    public static final String SHARED_PREFS = "sharedPrefs";
//    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_fav, container, false);
//        if(getListFromSP()!=null){
//
//        };

        return view;

    }

//    private List<String> getListFromSP() {
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        String getData = sharedPreferences.getString("Set", "");
//        List<String> movieList = gson.fromJson(getData, type);
//        return movieList;
//    }

}