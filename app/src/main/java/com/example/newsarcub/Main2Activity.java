package com.example.newsarcub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {



    ConnectionDetection connectionDetection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        connectionDetection=new ConnectionDetection(this);





            while (!connectionDetection.isConnected());
            new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Main2Activity.this, DashboardActivity.class));
                finish();
            }
        }, 1150);




    }



}
