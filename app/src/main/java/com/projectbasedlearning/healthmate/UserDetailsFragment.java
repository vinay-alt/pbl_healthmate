package com.projectbasedlearning.healthmate;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class UserDetailsFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager fm;
    UserDetailsSwitch adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        if (condition to check whether user is login or not) {
//            Intent i = new Intent(getContext(), Account.class);
//            startActivity(i);
//        }
        tabLayout = getView().findViewById(R.id.tab_layout);
        viewPager = getView().findViewById(R.id.view_pager);
        View v=inflater.inflate(R.layout.user_details_fragment, container, false);

        tabLayout.addTab(tabLayout.newTab().setText("Account"));
        tabLayout.addTab(tabLayout.newTab().setText("Medical"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // final UserDetailsSwitch adapter = new UserDetailsSwitch(getSupportFragmentManager(), this, tabLayout.getTabCount());
//        fm = new FragmentManager(getContext());
//        adapter = new UserDetailsSwitch(new FragmentManager(getContext()), getContext(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
        adapter = new UserDetailsSwitch(getContext(), tabLayout.getTabCount());

        viewPager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return v;

    }


}
