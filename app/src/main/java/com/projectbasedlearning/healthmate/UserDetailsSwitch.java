package com.projectbasedlearning.healthmate;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;


public class UserDetailsSwitch extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;


     public UserDetailsSwitch(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;

     }

    @Override
    public int getCount() {
        return totalTabs;
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
