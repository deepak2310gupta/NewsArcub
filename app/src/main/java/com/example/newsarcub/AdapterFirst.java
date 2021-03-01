package com.example.newsarcub;

import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdapterFirst extends RecyclerView.Adapter<AdapterFirst.NewsFirst> {


    Context context;
    ArrayList<ModelBriefNews>modelBriefNewsArrayList;


    public AdapterFirst(Context context, ArrayList<ModelBriefNews> modelBriefNewsArrayList) {
        this.context = context;
        this.modelBriefNewsArrayList = modelBriefNewsArrayList;
    }

    @NonNull
    @Override
    public NewsFirst onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.firstnewsrowhor,parent,false);
        return new NewsFirst(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFirst holder, int position) {

        final ModelBriefNews modelBriefNews=modelBriefNewsArrayList.get(position);

        String one=modelBriefNews.getUrlToImage();
        String two=modelBriefNews.getTitle();
        String three=modelBriefNews.getPublishedAt();
        String four=modelBriefNews.getDescription();
        String five=modelBriefNews.getName();

        Picasso.get().load(one).into(holder.firstImage011);
        String getDateNew=three;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate="";
        SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy K:mm a");
        try {
            Date date=dateFormat.parse(getDateNew);
            formattedDate=dateFormat2.format(date);

        }catch (Exception e){
            e.printStackTrace();
        }
        holder.mainSouceNewsTime.setText(formattedDate);
        holder.firstHeadingNews1.setText(two);
        holder.mainSourceNews.setText(five);
        holder.contentNewsDesc.setText(four);


        holder.cardView010.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTheBottomDialog(modelBriefNews);
            }
        });





    }

    private void showTheBottomDialog(final ModelBriefNews modelBriefNews) {

        final BottomSheetDialog bottomSheetDialognew=new BottomSheetDialog(context);
        View view=LayoutInflater.from(context).inflate(R.layout.laynewsfirstdetails,null);
        final WebView NewsDetailsWebView=view.findViewById(R.id.NewsDetailsWebViewHelp);


        WebSettings webSettings=NewsDetailsWebView.getSettings();
        NewsDetailsWebView.getSettings().setJavaScriptEnabled(true);
        NewsDetailsWebView.setVerticalScrollBarEnabled(false);
        NewsDetailsWebView.loadUrl(modelBriefNews.getUrl());
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setEnableSmoothTransition(true);
        bottomSheetDialognew.setContentView(view);


        bottomSheetDialognew.setCanceledOnTouchOutside(true);

        bottomSheetDialognew.show();

    }

    @Override
    public int getItemCount() {
        return modelBriefNewsArrayList.size();
    }

    public class NewsFirst extends RecyclerView.ViewHolder {
        ImageView firstImage011;
        TextView firstHeadingNews1,mainSourceNews,mainSouceNewsTime,contentNewsDesc;
        CardView cardView010;
        public NewsFirst(@NonNull View itemView) {
            super(itemView);
            cardView010=itemView.findViewById(R.id.cardView010);
            firstImage011=itemView.findViewById(R.id.firstImage011);
            firstHeadingNews1=itemView.findViewById(R.id.firstHeadingNews1);
            mainSouceNewsTime=itemView.findViewById(R.id.mainSouceNewsTime);
            mainSourceNews=itemView.findViewById(R.id.mainSourceNews);
            contentNewsDesc=itemView.findViewById(R.id.contentNewsDesc);
        }
    }



}
