package com.projectbasedlearning.healthmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ViewFlipper flipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        int imgarray[] = {R.drawable.report, R.drawable.user, R.drawable.test};
        flipper = (ViewFlipper)v.findViewById(R.id.flipper);
        for (int i=0;i<imgarray.length;i++) {
            showImg(imgarray[i]);
        }
        return v;
    }

    public void showImg(int img){
        ImageView imageview = new ImageView(getContext());
        imageview.setBackgroundResource(img);
        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
}
