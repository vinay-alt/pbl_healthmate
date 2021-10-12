package com.projectbasedlearning.healthmate;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;


public class UserDetailsSwitch extends PagerAdapter {

    private Context context;
    int totalTabs;


    public UserDetailsSwitch(Context context, int totalTabs) {
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                AccountTabFragment accountTabFragment = new AccountTabFragment();
                return accountTabFragment;
            case 1:
                MedicalTabFragment medicalTabFragment = new MedicalTabFragment();
                return medicalTabFragment;
            default:
                return null;
        }
    }
}
