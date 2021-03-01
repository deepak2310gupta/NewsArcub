package com.example.newsarcub;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    ImageView backBtn111;
    TextView txtHiuser,emailOfUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);
        backBtn111=viewProfile.findViewById(R.id.backBtn111);
        txtHiuser=viewProfile.findViewById(R.id.txtHiuser);
        emailOfUser=viewProfile.findViewById(R.id.emailOfUser);




        return viewProfile;
    }
}
