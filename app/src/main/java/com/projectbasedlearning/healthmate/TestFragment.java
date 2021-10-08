package com.projectbasedlearning.healthmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestFragment extends Fragment {
    FloatingActionButton fmain, fadd, fedit;
    Float translationYaxis = 100f;
    boolean menuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    LinearLayout fileLayout;
    Button uploaddoc;
    FloatingActionButton addfile;
    View adddoc;
    Animation layanim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        showMenu(v);
        return v;
    }
    private void showMenu(View v) {
        fmain = v.findViewById(R.id.fab);
        fadd = v.findViewById(R.id.fabadd);
        fedit = v.findViewById(R.id.fabedit);

        fadd.setAlpha(0f);
        fedit.setAlpha(0f);

        fadd.setTranslationY(translationYaxis);
        fedit.setTranslationY(translationYaxis);

        fmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOpen) {
                    closeMenu();
                } else {
                    OpenMenu();
                }
            }
        });
    }
    private void OpenMenu() {
        menuOpen = !menuOpen;
        fmain.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        fadd.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fedit.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout(v);
            }
        });
        fedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sjhdgyhvdjihugf", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addLayout(View v) {
        addfile = v.findViewById(R.id.fabadd);
        fileLayout = getView().findViewById(R.id.fileLayout);
        adddoc = getLayoutInflater().inflate(R.layout.adddoc, null, false);

        layanim = AnimationUtils.loadAnimation(getContext(), R.anim.lay_anim);
        fileLayout.setAnimation(layanim);
        fileLayout.addView(adddoc);
        uploaddoc = fileLayout.findViewById(R.id.uploaddoc);
        uploaddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileLayout.removeView(adddoc);
                Toast.makeText(getContext(), "Uploaded Document", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void closeMenu() {
        menuOpen = !menuOpen;
        fmain.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
        fadd.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fedit.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOpen) {
                    closeMenu();
                } else {
                    OpenMenu();
                }
            }
        });
        fedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOpen) {
                    closeMenu();
                } else {
                    OpenMenu();
                }
            }
        });
    }
}
