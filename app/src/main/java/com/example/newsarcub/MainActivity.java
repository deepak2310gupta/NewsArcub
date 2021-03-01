package com.example.newsarcub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    View naview1,naview2,naview3,naview4,naview5;
    Animation animation1,animation2;
    TextView NewsTxt,Arcubtxt;
    ConnectionDetection connectionDetection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionDetection=new ConnectionDetection(this);
        Arcubtxt=findViewById(R.id.Arcubtxt);
        NewsTxt=findViewById(R.id.NewsTxt);

        animation1= AnimationUtils.loadAnimation(this,R.anim.anim1);
        animation2=AnimationUtils.loadAnimation(this,R.anim.anim2);

        naview1=findViewById(R.id.naview1);
        naview2=findViewById(R.id.naview2);
        naview3=findViewById(R.id.naview3);
        naview4=findViewById(R.id.naview4);
        naview5=findViewById(R.id.naview5);

        naview1.setAnimation(animation1);
        naview2.setAnimation(animation2);
        naview3.setAnimation(animation2);
        naview4.setAnimation(animation1);
        naview5.setAnimation(animation2);



        startTheProject();



    }

    private void startTheProject() {

        if(connectionDetection.isConnected()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                    finish();
                }
            }, 1850);

        }

        else{
            Toast.makeText(MainActivity.this, "Please Check Your Internet Connection ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
        }

    }


}
