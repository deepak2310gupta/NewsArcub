package com.example.newsarcub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {


    BottomNavigationView viewNavigationBar;
    LinearLayout SubscribedLayout;
    TextView txtPremiumMember,txtSubscribeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SubscribedLayout=findViewById(R.id.SubscribedLayout);
        txtPremiumMember=findViewById(R.id.txtPremiumMember);
        txtSubscribeBtn=findViewById(R.id.txtSubscribeBtn);
        viewNavigationBar=findViewById(R.id.bottomNavViewer);
        viewNavigationBar.setOnNavigationItemSelectedListener(selectedListener);

        HomeFragment myAgain = new HomeFragment();
        FragmentTransaction fragmentTransactionAgain = getSupportFragmentManager().beginTransaction();
        fragmentTransactionAgain.replace(R.id.containers, myAgain,"").commit();

        SubscribedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
                progressDialog.setTitle("Please Wait ...");
                progressDialog.setMessage("Fetching Your Subscription Plan");
                progressDialog.setCancelable(false);
                progressDialog.show();


                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(DashboardActivity.this, SubscriptionActivity.class));
                        finish();
                    }
                }, 650);
            }
        });


    }


    BottomNavigationView.OnNavigationItemSelectedListener selectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.firstItem:
                    HomeFragment myAgain = new HomeFragment();
                    FragmentTransaction fragmentTransactionAgain = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionAgain.replace(R.id.containers, myAgain,"").commit();
                    break;

                case R.id.secondItem:
                    BriefingFragment myFragment1 = new BriefingFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.containers, myFragment1,"").commit();
                    break;

                case R.id.fifthItem:
                    SignupinFragment myFragment123 = new SignupinFragment();
                    FragmentTransaction fragmentTransaction123 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction123.replace(R.id.containers, myFragment123,"").commit();
                    break;
                case R.id.fourthItem:
                    NewSuggestedFragment myFragment1234 = new NewSuggestedFragment();
                    FragmentTransaction fragmentTransaction1234 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1234.replace(R.id.containers, myFragment1234,"").commit();
                    break;

            }


            return false;
        }
    };








}
