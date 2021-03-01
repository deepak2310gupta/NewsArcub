package com.example.newsarcub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class BriefingFragment extends Fragment {

    public BriefingFragment() {
        // Required empty public constructor
    }

    ArrayList<ModelBriefNews>modelBriefNewsArrayList;
    RecyclerView briefsRecyclerViewNews;
    AdapterBriefNews adapterBriefNews;
    ImageView dropdownEditorials;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_briefing, container, false);
        dropdownEditorials=view.findViewById(R.id.dropdownEditorials);
        briefsRecyclerViewNews=view.findViewById(R.id.briefsRecyclerViewNews);
        modelBriefNewsArrayList=new ArrayList<>();
        modelBriefNewsArrayList.clear();
        loadtheBriefings();

        dropdownEditorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomEditorials();
            }
        });

        return  view;
    }

    private void showBottomEditorials() {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getContext());
        View view=LayoutInflater.from(getContext()).inflate(R.layout.editionsbottomsheet,null,false);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }


    private void loadtheBriefings() {


        String[] suggested = {"business","entertainment","health"};

       for(int k=0;k<suggested.length;k++){


           String url="https://saurav.tech/NewsAPI/top-headlines/category/"+suggested[k]+"/in.json";
           StringRequest stringRequestNews=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   try {

                       JSONObject jsonObject=new JSONObject(response);
                       JSONArray jsonArray1=jsonObject.getJSONArray("articles");
                       for(int i=19;i<27;i++){

                           try {
                               JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                               String one=jsonObject1.getString("title");
                               String two=jsonObject1.getJSONObject("source").getString("name");
                               String three=jsonObject1.getString("urlToImage");
                               String four=jsonObject1.getString("publishedAt");
                               String five=jsonObject1.getString("description");
                               String seven=jsonObject1.getString("url");


                               ModelBriefNews modelNewsClass=new ModelBriefNews(
                                       "",
                                       ""+two,
                                       "",
                                       ""+one,
                                       ""+five,
                                       ""+seven,
                                       ""+three,
                                       ""+four,
                                       "");

                               modelBriefNewsArrayList.add(modelNewsClass);
                           }
                           catch (Exception e){
                               Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
                       adapterBriefNews=new AdapterBriefNews(getContext(),modelBriefNewsArrayList);
                       briefsRecyclerViewNews.setAdapter(adapterBriefNews);
                       adapterBriefNews.notifyDataSetChanged();

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
