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

public class AdapterSecondNews extends RecyclerView.Adapter<AdapterSecondNews.NewsHold> {

    Context context;
    ArrayList<ModelNewsClass>modelNewsClassArrayList;

    public AdapterSecondNews(Context context, ArrayList<ModelNewsClass> modelNewsClassArrayList) {
        this.context = context;
        this.modelNewsClassArrayList = modelNewsClassArrayList;
    }



    @NonNull
    @Override
    public NewsHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsView= LayoutInflater.from(context).inflate(R.layout.newsshortslist,parent,false);
        return new NewsHold(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHold holder, int position) {

        ModelNewsClass modelNewsClass=modelNewsClassArrayList.get(position);

        String one=modelNewsClass.getUrlToImage();
        String two=modelNewsClass.getTitle();
        final String eleven=modelNewsClass.getDescription();
        final String three=modelNewsClass.getUrl();
        Picasso.get().load(one).into(holder.imageLogo02);
        holder.txtNews02.setText(two);

        holder.readmreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HorizontalDetailnewsActivity.class);
                intent.putExtra("idUrl",three);
                intent.putExtra("descId",eleven);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelNewsClassArrayList.size();
    }

    public class NewsHold extends RecyclerView.ViewHolder {
        ImageView imageLogo02;
        TextView txtNews02,readmreBtn;
        public NewsHold(@NonNull View itemView) {
            super(itemView);
            readmreBtn=itemView.findViewById(R.id.readmreBtn);
            txtNews02=itemView.findViewById(R.id.txtNews02);
            imageLogo02=itemView.findViewById(R.id.imageLogo02);

        }
    }
}
