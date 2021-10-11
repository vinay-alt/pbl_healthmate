package com.projectbasedlearning.healthmate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SymptomDetailsFragment extends Fragment {
    TextView symp;
    boolean[] selectedsymp;
    ArrayList<Integer> symplist = new ArrayList<>();
    String[] sympArray= {"fever","cough","flu"};
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_symptom_details, container, false);
        symp = v.findViewById(R.id.symptoms);
        selectedsymp = new boolean[sympArray.length];
        symp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setTitle("Select Symptoms");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(sympArray, selectedsymp, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b){
                            symplist.add(i);
                            Collections.sort(symplist);

                        }else {
                            symplist.remove(i);

                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                       for (int j=0; j<symplist.size();j++){
                           stringBuilder.append(sympArray[symplist.get(j)]);
                           if (j != symplist.size()-1) {
                               stringBuilder.append(", ");

                           }
                       }
                       //symp.setText(stringBuilder.toString());
                        symp.setText(stringBuilder.toString());


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for ( int j=0;j<selectedsymp.length;j++){
                            selectedsymp[j]= false;
                            symplist.clear();
                            //symp.setText("");
                            symp.setText("");

                        }

                    }
                });
                builder.show();
            }
        });
        return v;

    }


}
