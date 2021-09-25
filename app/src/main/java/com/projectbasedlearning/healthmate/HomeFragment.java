package com.projectbasedlearning.healthmate;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;

public class HomeFragment extends Fragment {

    ViewFlipper flipper;
    Button bmi_button;
    float bmi;
    Dialog dialog;
    TextInputLayout height, weight;
    TextView out;
    ListView listView;
    Animation listanim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        int imgarray[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
        flipper = (ViewFlipper)v.findViewById(R.id.flipper);
        for (int i=0;i<imgarray.length;i++) {
            showImg(imgarray[i]);
        }

        listView = v.findViewById(R.id.listview);
        bmi_button = v.findViewById(R.id.bmi);
        height = v.findViewById(R.id.height_parent);
        weight = v.findViewById(R.id.weight_parent);

        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateBmi(v);
            }
        });

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

    public void CalculateBmi(View v) {
        float ht = Float.parseFloat(height.getEditText().getText().toString().trim());
        float wt = Float.parseFloat(weight.getEditText().getText().toString().trim());
        ht = ht/100;
        bmi = wt/(ht*ht);
        String ans = output(bmi,ht, wt);;
//        createDialog(v, ans);

        listanim = AnimationUtils.loadAnimation(getContext(), R.anim.letsgo);
        listView.setAnimation(listanim);
        String[] arr = new String[1];
        arr[0] = ans;
        BmiAdapter ba = new BmiAdapter(getContext(), R.layout.alertbox, arr);
        listView.setAdapter(ba);
    }

    public void removeEle(View v) {
        listView.removeAllViews();
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public void createDialog(View v, String ans) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.alertbox);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.slider_back));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        out =  dialog.findViewById(R.id.ans_text);
        out.setText(ans);
        dialog.show();
    }

    public String output(float bmi, float ht, float wt) {
        String ans;
        if (bmi<16) {
            ans = "You are severly thin. You are expected to increase weight atleast around "+round((float)(18.5 - bmi),1) + "Healthy weight for your height is somewhere around " + round((float)(18.5*ht*ht),1) + "-"+ (25*ht*ht);
        } else if (bmi > 16 && bmi < 18.5) {
            ans = "You are moderately thin. You are expected to increase weight atleast around "+round((float)(18.5 - bmi),1) + "Healthy weight for your height is somewhere around " + round((float)(18.5*ht*ht),1) + "-"+ (25*ht*ht);
        } else if (bmi > 18.5 && bmi < 25) {
            ans = "Your bmi index is "+bmi+" You belong to normal weight category of 18.5 - 25.";
        } else if (bmi > 25 && bmi < 30) {
            ans = "You are Overweight. You should consider descreasing weight atleast around "+round((float)(bmi - 25),1) + "Healthy weight for your height is somewhere around " + round((float)(18.5*ht*ht),1) + "-"+ (25*ht*ht);
        } else if (bmi > 30) {
            ans = "You are Obese. You should seriously think about descreasing your weight atleast around "+round((float)(bmi - 25),1) + "Healthy weight for your height is somewhere around " + round((float)(18.5*ht*ht),1) + "-"+ (25*ht*ht);
        } else {
            ans = "Error";
        }
        return ans;
    }
}
