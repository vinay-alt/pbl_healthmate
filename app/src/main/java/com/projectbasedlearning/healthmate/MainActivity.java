package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation vertanim, horanim, txtanim;
    CardView cv1, cv2;
    TextView txt;
    SharedPreferences OnBoardScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vertanim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        horanim = AnimationUtils.loadAnimation(this, R.anim.hor_anim);
        txtanim = AnimationUtils.loadAnimation(this, R.anim.anim_text);

        cv1 = findViewById(R.id.cardView);
        cv2 = findViewById(R.id.cardView2);
        txt = findViewById(R.id.txt);


        cv1.setAnimation(vertanim);
        cv2.setAnimation(horanim);
        txt.setAnimation(txtanim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                OnBoardScreen = getSharedPreferences("OnBoardScreen", MODE_PRIVATE);

                boolean isFirstTime = OnBoardScreen.getBoolean("firstTime", true);
                if (isFirstTime) {
                    SharedPreferences.Editor editor = OnBoardScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, OnBoard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 3000);


    }
}
