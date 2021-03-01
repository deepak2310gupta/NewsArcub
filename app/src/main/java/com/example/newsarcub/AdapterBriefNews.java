package com.example.newsarcub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBriefNews extends RecyclerView.Adapter<AdapterBriefNews.Briefholder> {

    Context context;
    ArrayList<ModelBriefNews>modelBriefNewsArrayList;

    public AdapterBriefNews(Context context, ArrayList<ModelBriefNews> modelBriefNewsArrayList) {
        this.context = context;
        this.modelBriefNewsArrayList = modelBriefNewsArrayList;
    }

    @NonNull
    @Override
    public Briefholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.briefinghorizontalrownews,parent,false);
        return new Briefholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Briefholder holder, int position) {

        ModelBriefNews modelBriefNews=modelBriefNewsArrayList.get(position);
        String one=modelBriefNews.getUrlToImage();
        String two=modelBriefNews.getTitle();
        String three=modelBriefNews.getName();
        final String four=modelBriefNews.getDescription();
        final String eightnew=modelBriefNews.getUrl();

        if(one.equals("")){
            Picasso.get().load(R.drawable.defaultnewsimg).into(holder.briefimageNews);
        }else{
            Picasso.get().load(one).into(holder.briefimageNews);
        }

        holder.newsTitleSecond.setText(two);
        holder.contentNewsThird.setText(four);
        holder.newsSourceFourth.setText(three);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(context,TopnewsdetailActivity.class);
                intent1.putExtra("IdURLNew",eightnew);
                intent1.putExtra("DescId",four);
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelBriefNewsArrayList.size();
    }

    public class Briefholder extends RecyclerView.ViewHolder {
        ImageView briefimageNews;
        TextView newsTitleSecond,newsSourceFourth,contentNewsThird,newsTimeBrief;
        public Briefholder(@NonNull View itemView) {
            super(itemView);


            briefimageNews=itemView.findViewById(R.id.briefimageNews);
            newsTitleSecond=itemView.findViewById(R.id.newsTitleSecond);
            newsSourceFourth=itemView.findViewById(R.id.newsSourceFourth);
            contentNewsThird=itemView.findViewById(R.id.contentNewsThird);
            newsTimeBrief=itemView.findViewById(R.id.newsTimeBrief);


        }
    }



}
