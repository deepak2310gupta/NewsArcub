package com.example.newsarcub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class ParticularNewsDetailsActivity extends AppCompatActivity {



    private String url,descNew;
    private  WebView webNewsView;
    ImageView shareBtnhndt;
    ImageButton nextBackBtn100;
    ImageView txtToSpeechNewsBtn,format;
    TextToSpeech t1;
    boolean helpVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_news_details);
        url=getIntent().getStringExtra("urlID");
        descNew=getIntent().getStringExtra("DescID");
        helpVar=false;
        shareBtnhndt=findViewById(R.id.shareBtnhndt);
        format=findViewById(R.id.format);
        nextBackBtn100=findViewById(R.id.nextBackBtn100);
        txtToSpeechNewsBtn=findViewById(R.id.txtToSpeechNewsBtn);
        webNewsView=findViewById(R.id.webNewsView);
        webNewsView.setWebViewClient(new HelpClient());

        WebSettings webSettings=webNewsView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setEnableSmoothTransition(true);

        webNewsView.getSettings().setJavaScriptEnabled(true);
        webNewsView.setVerticalScrollBarEnabled(false);
        webNewsView.loadUrl(url);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        txtToSpeechNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helpVar==true){
                    t1.stop();
                    txtToSpeechNewsBtn.setImageResource(R.drawable.volume);
                    helpVar=false;
                }
                else {
                    String toSpeak = webNewsView.getTitle() + "" + descNew;
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    txtToSpeechNewsBtn.setImageResource(R.drawable.ic_adjust_black_24dp);
                    helpVar = true;

                }
            }
        });

        format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebSettings webSettings=webNewsView.getSettings();
                webSettings.setTextZoom(125);
                Toast.makeText(ParticularNewsDetailsActivity.this, "Format Size Increased", Toast.LENGTH_SHORT).show();
            }
        });

        shareBtnhndt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareHelpFun();
            }
        });
        nextBackBtn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.stop();
                onBackPressed();
            }
        });

    }


    private void shareHelpFun(){
        String urlToShare=webNewsView.getUrl();
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,urlToShare);
        startActivity(Intent.createChooser(shareIntent,"Share Via"));
    }
    private class HelpClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Toast.makeText(ParticularNewsDetailsActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
        }



    }




}
