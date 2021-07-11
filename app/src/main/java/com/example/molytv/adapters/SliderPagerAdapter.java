package com.example.molytv.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.molytv.MoviePlayerActivity;
import com.example.molytv.R;
import com.example.molytv.models.Slide;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext ;
    private List<Slide> mList ;

    MovieItemClickListener movieItemClickListener;


    public SliderPagerAdapter(Context mContext, List<Slide> mList,MovieItemClickListener listener) {
        this.mContext = mContext;
        this.mList = mList;
        movieItemClickListener = listener;

    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);
        slideImg.setImageResource(mList.get(position).getImage());
        slideText.setText(mList.get(position).getTitle());

        container.addView(slideLayout);

        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if((position) == 0) {
//                    Toast.makeText(mContext, "One", Toast.LENGTH_SHORT).show();
//                } else if(position == 1) {
//                    Toast.makeText(mContext, "Two", Toast.LENGTH_SHORT).show();
//                } else if(position == 2) {
//                    Toast.makeText(mContext, "Three", Toast.LENGTH_SHORT).show();
//                }

                movieItemClickListener.onMovieHomeClick(mList.get(position));

            }
        });


        return slideLayout;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
