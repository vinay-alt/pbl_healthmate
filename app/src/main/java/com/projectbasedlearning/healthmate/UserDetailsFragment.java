package com.projectbasedlearning.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserDetailsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (true) {
            Intent i = new Intent(getContext(), Account.class);
            startActivity(i);
        }
        return inflater.inflate(R.layout.user_details_fragment, container, false);
    }
}
