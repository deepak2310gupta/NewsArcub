package com.example.newsarcub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment  {

    public HomeFragment() {
        // Required empty public constructor
    }

    AppBarLayout firstAppbar;
    RecyclerView firstRecyclerViewNews;
    RecyclerView newsRecyclerView,topPicksRecyclerview;
    AdapterNews adapterNews;
    AdapterSecondNews adapterSecondNews;
    ArrayList<ModelNewsClass>modelNewsClassArrayList1;
    ArrayList<ModelNewsClass>modelNewsClassArrayList;
    ArrayList<ModelBriefNews>modelBriefNewsArrayList;

    TextView topPicks;
    AdapterFirst adapterFirst;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        firstAppbar=view.findViewById(R.id.firstAppbar);
        firstRecyclerViewNews=view.findViewById(R.id.firstRecyclerViewNews);

        newsRecyclerView=view.findViewById(R.id.newsRecyclerView);
        topPicksRecyclerview=view.findViewById(R.id.topPicksRecyclerview);
        topPicks=view.findViewById(R.id.txtTopPicks);
        modelNewsClassArrayList=new ArrayList<>();
        modelNewsClassArrayList.clear();
        modelNewsClassArrayList1=new ArrayList<>();
        modelNewsClassArrayList1.clear();
        modelBriefNewsArrayList=new ArrayList<>();
        modelBriefNewsArrayList.clear();









        loadTheNewsShortsnew();
        loadTheFirstNews();
        loadTheHorizontalNews();




        return  view;

    }



    private void loadTheFirstNews() {


        String url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json";

        StringRequest stringRequestNews=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray1=jsonObject.getJSONArray("articles");
                    for(int i=2;i<3;i++){

                        try {
                            JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                            String one=jsonObject1.getString("title");
                            String two=jsonObject1.getJSONObject("source").getString("name");
                            String three=jsonObject1.getString("urlToImage");
                            String four=jsonObject1.getString("publishedAt");
                            String five=jsonObject1.getString("description");
                            String six=jsonObject1.getString("url");

                            ModelBriefNews modelNewsClass=new ModelBriefNews(
                                    "",
                                    ""+two,
                                    "",
                                    ""+one,
                                    ""+five,
                                    ""+six,
                                    ""+three,
                                    ""+four,
                                    "");

                            modelBriefNewsArrayList.add(modelNewsClass);



                            topPicks.setVisibility(View.VISIBLE);

                        }
                        catch (Exception e){
                            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    adapterFirst=new AdapterFirst(getContext(),modelBriefNewsArrayList);
                    firstRecyclerViewNews.setAdapter(adapterFirst);
                    adapterFirst.notifyDataSetChanged();
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


    private void loadTheHorizontalNews() {

        String[] arrSuggestions = {"science","health","sports","entertainment"};


        for(int i=0;i<arrSuggestions.length;i++){

            String url="https://saurav.tech/NewsAPI/top-headlines/category/"+arrSuggestions[i]+"/in.json";
            StringRequest stringRequestNews12=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray jsonArray1=jsonObject.getJSONArray("articles");
                        for(int i=8;i<13;i++){

                            try {
                                JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                                String one=jsonObject1.getString("title");
                                String two=jsonObject1.getJSONObject("source").getString("name");
                                String three=jsonObject1.getString("urlToImage");
                                String four=jsonObject1.getString("publishedAt");
                                String five=jsonObject1.getString("url");
                                String ten=jsonObject1.getString("description");

                                ModelNewsClass modelNewsClass1=new ModelNewsClass(
                                        "",
                                        ""+four,
                                        ""+three,
                                        ""+five,
                                        ""+ten,
                                        ""+one,
                                        "",
                                        ""+two);

                                modelNewsClassArrayList1.add(modelNewsClass1);
                            }

                            catch (Exception e){
                                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapterSecondNews=new AdapterSecondNews(getContext(),modelNewsClassArrayList1);
                        topPicksRecyclerview.setAdapter(adapterSecondNews);
                        adapterSecondNews.notifyDataSetChanged();

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

            RequestQueue requestQueue123= Volley.newRequestQueue(getContext());
            requestQueue123.add(stringRequestNews12);



        }
    }
    private void loadTheNewsShortsnew(){


        String url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json";
        StringRequest stringRequestNews=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray1=jsonObject.getJSONArray("articles");
                    for(int i=3;i<12;i++){

                        try {
                            JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                            String one=jsonObject1.getString("title");
                            String two=jsonObject1.getJSONObject("source").getString("name");
                            String three=jsonObject1.getString("urlToImage");
                            String four=jsonObject1.getString("publishedAt");
                            String five=jsonObject1.getString("url");
                            String ten=jsonObject1.getString("description");
                            ModelNewsClass modelNewsClass=new ModelNewsClass(
                                    "",
                                    ""+four,
                                    ""+three,
                                    ""+five,
                                    ""+ten,
                                    ""+one,
                                    "",
                                    ""+two);

                            modelNewsClassArrayList.add(modelNewsClass);
                        }
                        catch (Exception e){
                            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    adapterNews=new AdapterNews(getContext(),modelNewsClassArrayList);
                    newsRecyclerView.setAdapter(adapterNews);
                    adapterNews.notifyDataSetChanged();

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
