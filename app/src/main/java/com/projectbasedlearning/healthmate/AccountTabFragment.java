package com.projectbasedlearning.healthmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AccountTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.user_details_account_fragment, container, false);

        return root;
    }
}
