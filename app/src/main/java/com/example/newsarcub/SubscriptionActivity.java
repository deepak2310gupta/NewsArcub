package com.example.newsarcub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionActivity extends AppCompatActivity {


    TextView theSubscriptionnewsarcubTxt;
    ImageView subscriptionBackBtn;
    ViewPager PagerViewSubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        theSubscriptionnewsarcubTxt=findViewById(R.id.theSubscriptionnewsarcubTxt);
        subscriptionBackBtn=findViewById(R.id.subscriptionBackBtn);
        PagerViewSubscription=findViewById(R.id.PagerViewSubscription);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstsubscibedFragment(), "ONE");
        adapter.addFragment(new SecondsubscribedFragment(), "TWO");
        PagerViewSubscription.setAdapter(adapter);



        subscriptionBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubscriptionActivity.this,DashboardActivity.class));
            }
        });

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }





}
