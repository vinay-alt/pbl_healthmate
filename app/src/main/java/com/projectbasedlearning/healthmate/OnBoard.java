package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OnBoard extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots_layout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Animation letsgoanim;
    int currentposition;
    Button letsgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        viewPager = findViewById(R.id.slider);
        dots_layout = findViewById(R.id.dots);

        letsgo = findViewById(R.id.letsgo);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);

        viewPager.addOnPageChangeListener(changeListener);

    }


    public void skip(View view) {
        startActivity(new Intent(this, HomeScreen.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentposition+1);
    }

    private void addDots(int position){
        dots = new TextView[3];
        dots_layout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots_layout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.red));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentposition = position;

            if (position == 0) {
                letsgo.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                letsgo.setVisibility(View.INVISIBLE);
            } else {
                letsgoanim = AnimationUtils.loadAnimation(OnBoard.this,R.anim.letsgo);
                letsgo.setAnimation(letsgoanim);
                letsgo.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
