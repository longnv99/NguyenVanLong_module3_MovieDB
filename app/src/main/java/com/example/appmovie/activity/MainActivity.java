package com.example.appmovie.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appmovie.R;
import com.example.appmovie.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public
class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private
    ViewPager2 viewPager2;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottomnavigation);
        viewPager2 =findViewById(R.id.viewPager);
        viewPager2.setOffscreenPageLimit(3);

        setupViewPager();
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public
            boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.upcoming:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.toprate:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.popular:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.nowplaying:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }

    private void setupViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public
            void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.upcoming).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.toprate).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.popular).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.nowplaying).setChecked(true);
                        break;
                }
            }
        });
    }

    public
    void clickSearch(View view) {
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
    }
}