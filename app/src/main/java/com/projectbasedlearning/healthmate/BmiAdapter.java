package com.projectbasedlearning.healthmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class BmiAdapter extends ArrayAdapter<String> {
    private String[] arr;
    public BmiAdapter(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource, arr);
        this.arr = arr;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.alertbox, parent, false);
        TextView t = convertView.findViewById(R.id.ans_text);
        t.setText(getItem(position));
        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return arr[position];
    }
}
