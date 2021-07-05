package com.example.molytv.fragments;

import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

import com.example.molytv.R;
import com.google.android.material.tabs.TabLayout;

public class CategoryFragment extends Fragment {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,container,false);
    }
}
