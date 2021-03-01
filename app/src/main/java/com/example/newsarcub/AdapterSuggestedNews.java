package com.example.newsarcub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterSuggestedNews extends RecyclerView.Adapter<AdapterSuggestedNews.SuggestedNewsHolder> {


    Context context;
    ArrayList<ModelNewsClass> modelNewsClassArrayList;

    public AdapterSuggestedNews(Context context, ArrayList<ModelNewsClass> modelNewsClassArrayList) {
        this.context = context;
        this.modelNewsClassArrayList = modelNewsClassArrayList;
    }


    @NonNull
    @Override
    public SuggestedNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.suggestedhorizontalnews,parent,false);
        return new SuggestedNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SuggestedNewsHolder holder, final int position) {

        final ModelNewsClass modelNewsClass= modelNewsClassArrayList.get(position);

        String one=modelNewsClass.getPublishedAt();
        String two=modelNewsClass.getTitle();
        String three=modelNewsClass.getUrlToImage();
        String four=modelNewsClass.getName();

        String getDateNew=one;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate="";
        SimpleDateFormat dateFormat2=new SimpleDateFormat("MM/dd/yyyy K:mm a");

        try {
            Date date=dateFormat.parse(getDateNew);
            formattedDate=dateFormat2.format(date);

        }catch (Exception e){
            e.printStackTrace();
        }

        Picasso.get().load(three).into(holder.newsImageLogo);
        holder.newsSuggestedTitle.setText(two);
        holder.suggestedSourceNews.setText(four);
        holder.SuggestedNewstime.setText(formattedDate);



    }

    private void deleteItemRow(int pos) {

         notifyItemRemoved(pos-1);
         Toast.makeText(context, "Read Fewer Stories Like This", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return modelNewsClassArrayList.size();
    }

    public class SuggestedNewsHolder extends RecyclerView.ViewHolder {
        ImageView newsImageLogo,readFewerstories;
        TextView newsSuggestedTitle,suggestedSourceNews,SuggestedNewstime;
        CardView suggestedNewsHorizontalcardView;
        public SuggestedNewsHolder(@NonNull View itemView) {
            super(itemView);
            suggestedNewsHorizontalcardView=itemView.findViewById(R.id.suggestedNewsHorizontalcardView);
            readFewerstories=itemView.findViewById(R.id.readFewerstories);
            newsImageLogo=itemView.findViewById(R.id.newsImageLogo);
            newsSuggestedTitle=itemView.findViewById(R.id.newsSuggestedTitle);
            suggestedSourceNews=itemView.findViewById(R.id.suggestedSourceNews);
            SuggestedNewstime=itemView.findViewById(R.id.SuggestedNewstime);

        }
    }


}
