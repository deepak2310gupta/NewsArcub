package com.example.newsarcub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static javax.xml.transform.OutputKeys.ENCODING;

public class AdapterNews extends  RecyclerView.Adapter<AdapterNews.Newsholder> {


    private Context context;
    private ArrayList<ModelNewsClass>modelNewsClassArrayList;

    public AdapterNews(Context context, ArrayList<ModelNewsClass> modelNewsClassArrayList) {
        this.context = context;
        this.modelNewsClassArrayList = modelNewsClassArrayList;
    }

    @NonNull
    @Override
    public Newsholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View viewNew= LayoutInflater.from(context).inflate(R.layout.newsitemlistshorts,parent,false);
        return new Newsholder(viewNew);
    }

    @Override
    public void onBindViewHolder(@NonNull Newsholder holder, int position) {

        final ModelNewsClass modelNewsClass=modelNewsClassArrayList.get(position);

        String one =modelNewsClass.getName();
        String two=modelNewsClass.getPublishedAt();
        final String thrre=modelNewsClass.getTitle();
        String four=modelNewsClass.getUrlToImage();
        final String five=modelNewsClass.getUrl();
        final String nine=modelNewsClass.getDescription();

        String getDateNew=two;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate="";
        SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy K:mm a");
        try {
            Date date=dateFormat.parse(getDateNew);
            formattedDate=dateFormat2.format(date);

        }catch (Exception e){
            e.printStackTrace();
        }

        holder.timeNews.setText(formattedDate);
        holder.txtTopNews.setText(one);
        holder.newsHead.setText(thrre);


        if(four.equals("")){
            Picasso.get().load(R.drawable.defaultnewsimg).into(holder.imageLogo01);
        }
        else{
            Picasso.get().load(four).into(holder.imageLogo01);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ParticularNewsDetailsActivity.class);
                intent.putExtra("urlID",five);
                intent.putExtra("DescID",nine);
                context.startActivity(intent);


            }
        });


        holder.shareBtnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlToShare=thrre+"\n"+five;
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,urlToShare);
                context.startActivity(Intent.createChooser(shareIntent,"Share Via"));
            }
        });


    }



    @Override
    public int getItemCount() {
        return modelNewsClassArrayList.size();
    }

    public class Newsholder extends RecyclerView.ViewHolder {

        ImageView imageLogo01,shareBtnNews;
        TextView newsHead,txtTopNews,timeNews;
        public Newsholder(@NonNull View itemView) {
            super(itemView);

            imageLogo01=itemView.findViewById(R.id.imageLogo011);
            newsHead=itemView.findViewById(R.id.newsHead1);
            txtTopNews=itemView.findViewById(R.id.txtTopNews1);
            timeNews=itemView.findViewById(R.id.timeNews1);
            shareBtnNews=itemView.findViewById(R.id.shareBtnNews);
        }

    }
}
