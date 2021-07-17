package com.example.molytv.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.molytv.BuildConfig;
import com.example.molytv.R;
import com.google.android.material.tabs.TabLayout;

public class MoreFragment extends Fragment {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutTop);
        tabLayout.setVisibility(View.GONE);
    };
    CardView share;
    CardView booking;
    CardView feedback;
    CardView restore;
    CardView signIn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_more,container,false);

        share = view.findViewById(R.id.cardViewSharing);
        share.setOnClickListener(e->{
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");

            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            startActivity(sendIntent);

        });

        booking = view.findViewById(R.id.cardViewBooking);
        booking.setOnClickListener(e->{
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SENDTO);
//            sendIntent.setData(Uri.parse("mailto:"));
//            sendIntent.putExtra(sendIntent.EXTRA_EMAIL,"@gmail.com");
//            startActivity(sendIntent);

//c
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//            intent.putExtra(Intent.EXTRA_EMAIL, "techbinary0101@gmail.com");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
//               startActivity(intent);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","techbinary0101@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Booking Info");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

        });

        feedback = view.findViewById(R.id.cardViewFeedback);
        feedback.setOnClickListener(e->{

            Intent feedbackEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","techbinary0101@gmail.com", null));
            feedbackEmail.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            feedbackEmail.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(feedbackEmail, "Send email..."));
        });

        restore = view.findViewById(R.id.cardViewRestore);
        restore.setOnClickListener(e->{

            Context context = getContext();
            CharSequence text = "You have no past purchases linked to your account";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        });

        signIn = view.findViewById(R.id.cardViewSignIn);
        signIn.setOnClickListener(e->{

            Context context = getContext();
            SignUpFragment signUpFragment = new SignUpFragment();

            replaceFragment(signUpFragment);
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
