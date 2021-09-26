package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class Account extends AppCompatActivity {

    ViewPager viewPager;
    Button loginbt, signupbt, forget;
    int currentposition;
    AccountAdaptor accountAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        viewPager = findViewById(R.id.account_pager);
        loginbt = findViewById(R.id.loginbt);
        signupbt = findViewById(R.id.signupbt);
        forget = findViewById(R.id.forget);
        accountAdaptor = new AccountAdaptor(this);
        viewPager.setAdapter(accountAdaptor);


    }

    public void next(View view){
        viewPager.setCurrentItem(currentposition+1);

    }

    public void prev(View view){
        viewPager.setCurrentItem(currentposition-1);

    }

    public void change(View view) {
        Intent i = new Intent(this, ForgetPassword.class);
        startActivity(i);
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentposition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
    }
}
