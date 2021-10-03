package com.projectbasedlearning.healthmate;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    ViewFlipper flipper;
    Button bmi_button;
    float bmi;
    Dialog dialog;
    TextInputLayout height, weight;
    TextView out;
    Button close_lay;
    Animation listanim;
    LinearLayout lay_bmi;
    private RecyclerView newsRV;
    private ArrayList<Articles> articlesArrayList;
    private NewsRVAdaptor newsRVAdaptor;
    ArrayList<Articles> articles;
    ProgressBar loading;
    RecyclerView banner;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        int imgarray[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
        newsRV = v.findViewById(R.id.newsrv);
        lay_bmi = v.findViewById(R.id.lay_bmi);
        bmi_button = v.findViewById(R.id.bmi);
        height = v.findViewById(R.id.height_parent);
        weight = v.findViewById(R.id.weight_parent);
        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatehw(v);
            }
        });
        articlesArrayList = new ArrayList<Articles>();
        newsRVAdaptor = new NewsRVAdaptor(articlesArrayList, getContext());
        newsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRV.setAdapter(newsRVAdaptor);
        loading = v.findViewById(R.id.loading);
        getNews(v);
        return v;
    }

    public void setBanner(View v) {
        banner = v.findViewById(R.id.banner);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        banner.setLayoutManager(layoutManager);
        Log.e("hudgyhsbknlmuiyf", ""+articlesArrayList.size());
        ArrayList<Articles> listOfStrings = new ArrayList<Articles>(articlesArrayList.subList(0,3).size());
        listOfStrings.addAll(articlesArrayList.subList(0,3));
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getActivity(), listOfStrings);
        banner.setAdapter(scrollAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(banner);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager.findLastCompletelyVisibleItemPosition() <  scrollAdapter.getItemCount()-1) {
                    layoutManager.smoothScrollToPosition(banner, new RecyclerView.State(), layoutManager.findLastCompletelyVisibleItemPosition()+1);
                } else {
                    layoutManager.smoothScrollToPosition(banner, new RecyclerView.State(), 0);
                }
            }
        }, 0, 3000);
    }

    public void validatehw(View v) {
        String hts = height.getEditText().getText().toString().trim();
        String wts = weight.getEditText().getText().toString().trim();
        if (hts.length()>0 && wts.length()>0) {
            try {
                float ht = Float.parseFloat(height.getEditText().getText().toString().trim());
                float wt = Float.parseFloat(weight.getEditText().getText().toString().trim());
                CalculateBmi(v, ht, wt);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid height or weight", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else if(hts.length()<=0 && wts.length()>0) {
            Toast.makeText(getContext(), "Height is Empty", Toast.LENGTH_SHORT).show();
        } else if (wts.length()<=0 && hts.length()>0) {
            Toast.makeText(getContext(), "Weight is Empty", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Both Fields are Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void CalculateBmi(View v,float ht, float wt) {
//        float ht = Float.parseFloat(height.getEditText().getText().toString().trim());
//        float wt = Float.parseFloat(weight.getEditText().getText().toString().trim());
        ht = ht/100;
        bmi = wt/(ht*ht);
        String ans = output(bmi,ht, wt);
        listanim = AnimationUtils.loadAnimation(getContext(), R.anim.letsgo);
        final View alertview = getLayoutInflater().inflate(R.layout.alertbox, null, false);
        if (lay_bmi.findViewById(R.id.alertlayout) == null) {
            lay_bmi.addView(alertview);
            alertview.setAnimation(listanim);
            TextView out = alertview.findViewById(R.id.ans_text);
            out.setText(ans);
            close_lay = alertview.findViewById(R.id.close);
            close_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lay_bmi.removeView(alertview);
                }
            });
        }
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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

    public void getNews(View v){
        loading.setVisibility(View.VISIBLE);
        String url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=e848d779def74a3b9e97becf7bcc0bca";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;
        call = retrofitAPI.getNews(url);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                ArrayList<Articles> articles = newsModel.getArticles();
                for (int i=3;i<articles.size();i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));
                }
                newsRVAdaptor.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
                setBanner(v);
            }
            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load articles", Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }
        });
    }


}