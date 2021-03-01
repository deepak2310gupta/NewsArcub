package com.example.newsarcub;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class NewSuggestedFragment extends Fragment {

    public NewSuggestedFragment() {
    }

    Button preferringSetBtn;
    RelativeLayout businessPrefer,entertainmentPrefer,sportsPrefer,generalPrefer,sciencePrefer,healthPrefer;
    boolean varOne,varTwo,varThree,varFour,varFive,varSix;
    TextView txtBusiness,txtGeneral,txtSports,txtHealth,txtEntertainment,txtScience;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_new_suggested, container, false);
        preferringSetBtn=view.findViewById(R.id.preferringSetBtn);


        businessPrefer=view.findViewById(R.id.businessPrefer);
        entertainmentPrefer=view.findViewById(R.id.entertainmentPrefer);
        sportsPrefer=view.findViewById(R.id.sportsPrefer);
        generalPrefer=view.findViewById(R.id.generalPrefer);
        sciencePrefer=view.findViewById(R.id.sciencePrefer);
        healthPrefer=view.findViewById(R.id.healthPrefer);

        txtBusiness=view.findViewById(R.id.txtBusiness);
        txtGeneral=view.findViewById(R.id.txtGeneral);
        txtSports=view.findViewById(R.id.txtSports);
        txtHealth=view.findViewById(R.id.txtHealth);
        txtEntertainment=view.findViewById(R.id.txtEntertainment);
        txtScience=view.findViewById(R.id.txtScience);

        varOne=false;
        varTwo=false;
        varThree=false;
        varFour=false;
        varFive=false;
        varSix=false;


        businessPrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varOne==false){
                    businessPrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtBusiness.setTextColor(Color.parseColor("#FFFFFF"));
                    varOne=true;
                }
                else{
                    businessPrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtBusiness.setTextColor(Color.parseColor("#090909"));
                    varOne=false;
                }

            }
        });


        entertainmentPrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varTwo==false){
                    entertainmentPrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtEntertainment.setTextColor(Color.parseColor("#FFFFFF"));
                    varTwo=true;
                }
                else{
                    entertainmentPrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtEntertainment.setTextColor(Color.parseColor("#090909"));
                    varTwo=false;
                }
            }
        });

        sportsPrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varThree==false){
                    sportsPrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtSports.setTextColor(Color.parseColor("#FFFFFF"));
                    varThree=true;
                }
                else{
                    sportsPrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtSports.setTextColor(Color.parseColor("#090909"));
                    varThree=false;
                }
            }
        });

        generalPrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varFour==false){
                    generalPrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtGeneral.setTextColor(Color.parseColor("#FFFFFF"));
                    varFour=true;
                }
                else{
                    generalPrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtGeneral.setTextColor(Color.parseColor("#090909"));
                    varFour=false;
                }
            }
        });

        sciencePrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varFive==false){
                    sciencePrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtScience.setTextColor(Color.parseColor("#FFFFFF"));
                    varFive=true;
                }
                else{
                    sciencePrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtScience.setTextColor(Color.parseColor("#090909"));
                    varFive=false;
                }
            }
        });

        healthPrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(varSix==false){
                    healthPrefer.setBackgroundResource(R.drawable.selectedcardviewbg);
                    txtHealth.setTextColor(Color.parseColor("#FFFFFF"));
                    varSix=true;
                }
                else{
                    healthPrefer.setBackgroundResource(R.drawable.cardbgview);
                    txtHealth.setTextColor(Color.parseColor("#090909"));
                    varSix=false;
                }
            }
        });



        preferringSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!varOne && !varTwo && !varThree && !varFour && !varFive && !varSix){
                    Toast.makeText(getContext(), "Please Select Your Preferences", Toast.LENGTH_SHORT).show();
                }
                else{
                    SuggestionsFragment myAgain = new SuggestionsFragment();
                    FragmentTransaction fragmentTransactionAgain=getFragmentManager().beginTransaction();
                    Bundle bAgain = new Bundle();
                    bAgain.putString("value1new", ""+varOne);
                    bAgain.putString("value2new", ""+varTwo);
                    bAgain.putString("value3new", ""+varThree);
                    bAgain.putString("value4new", ""+varFour);
                    bAgain.putString("value5new", ""+varFive);
                    bAgain.putString("value6new", ""+varSix);
                    myAgain.setArguments(bAgain);
                    fragmentTransactionAgain.replace(R.id.containers, myAgain,"").commit();

                }






            }
        });


        return  view;


    }




}
