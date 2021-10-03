package com.projectbasedlearning.healthmate;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AutoScrollAdapter extends RecyclerView.Adapter<AutoScrollAdapter.ViewHolder> {
    View view;
    Context context;
    private ArrayList<Articles> articlesArrayList;

    public AutoScrollAdapter(Context context, ArrayList<Articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bannerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        Picasso.get().load(articles.getUrlToImage()).into(holder.imv);
        holder.tv.setText(articles.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "banner " + (position+1) + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv = itemView.findViewById(R.id.imv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
