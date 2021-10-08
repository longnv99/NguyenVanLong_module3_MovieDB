package com.example.appmovie.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appmovie.fragment.NowPlayingFragment;
import com.example.appmovie.fragment.PopularFragment;
import com.example.appmovie.fragment.TopRateFragment;
import com.example.appmovie.fragment.UpcomingFragment;

public
class ViewPagerAdapter extends FragmentStateAdapter {

    public
    ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public
    Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new UpcomingFragment();
            case 1:
                return new TopRateFragment();
            case 2:
                return new PopularFragment();
            case 3:
                return new NowPlayingFragment();
            default:
                return new UpcomingFragment();
        }
    }

    @Override
    public
    int getItemCount() {
        return 4;
    }
}
