package com.example.newsarcub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SuggestionsFragment extends Fragment {

    public SuggestionsFragment() {
    }
    AdapterSuggestedNews adapterSuggestedNews;
    RecyclerView suggestedRecyclerView;
    ArrayList<ModelNewsClass>modelNewsClassesList123;
    String one1,two2,three3,four4,five5,six6;
    ArrayList<String>listSuggested;
    ImageView btnImageLoGO,crownForSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_suggestions, container, false);
        listSuggested=new ArrayList<>();
        Bundle bundle = getArguments();
        one1= bundle.getString("value1new");
        two2= bundle.getString("value2new");
        three3= bundle.getString("value3new");
        four4= bundle.getString("value4new");
        five5= bundle.getString("value5new");
        six6= bundle.getString("value6new");
        btnImageLoGO=view.findViewById(R.id.btnImageLoGO);
        crownForSubscription=view.findViewById(R.id.crownForSubscription);
        suggestedRecyclerView=view.findViewById(R.id.suggestedRecyclerView);
        modelNewsClassesList123=new ArrayList<>();
        modelNewsClassesList123.clear();



        if(one1.equals("true")){
            listSuggested.add("business");
        }
        if(two2.equals("true")){
            listSuggested.add("general");
        }
        if(three3.equals("true")){
            listSuggested.add("sports");
        }
        if(four4.equals("true")){
            listSuggested.add("health");
        }
        if(five5.equals("true")){
            listSuggested.add("entertainment");
        }
        if(six6.equals("true")){
            listSuggested.add("science");
        }


        btnImageLoGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        crownForSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Please Wait ...");
                progressDialog.setMessage("Fetching Your Subscription Plan");
                progressDialog.setCancelable(false);
                progressDialog.show();


                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(getContext(), SubscriptionActivity.class));
                    }
                }, 650);
            }
        });


        loadSuggestionNews(listSuggested);
        return view;
    }


    private void loadSuggestionNews(ArrayList<String>listSuggested) {



        for(int i=0;i<listSuggested.size();i++){


            String url="https://saurav.tech/NewsAPI/top-headlines/category/"+listSuggested.get(i)+"/in.json";
            StringRequest stringRequestNews=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray jsonArray1=jsonObject.getJSONArray("articles");
                        for(int i=26;i<30;i++){

                            try {
                                JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                                String one=jsonObject1.getString("title");
                                String two=jsonObject1.getJSONObject("source").getString("name");
                                String three=jsonObject1.getString("urlToImage");
                                String four=jsonObject1.getString("publishedAt");
                                String five=jsonObject1.getString("url");

                                ModelNewsClass modelNewsClass=new ModelNewsClass(
                                        "",
                                        ""+four,
                                        ""+three,
                                        ""+five,
                                        "",
                                        ""+one,
                                        "",
                                        ""+two);

                                modelNewsClassesList123.add(modelNewsClass);
                            }
                            catch (Exception e){
                                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapterSuggestedNews=new AdapterSuggestedNews(getContext(),modelNewsClassesList123);
                        suggestedRecyclerView.setAdapter(adapterSuggestedNews);
                        adapterSuggestedNews.notifyDataSetChanged();

                    }
                    catch (Exception e){
                        Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequestNews);

        }








    }




}
