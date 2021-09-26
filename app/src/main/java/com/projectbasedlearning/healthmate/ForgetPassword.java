package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgetPassword extends AppCompatActivity {

    Button submit;
    TextInputLayout number;
    String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        number = findViewById(R.id.phnforget);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = number.getEditText().getText().toString();
                Intent i = new Intent(getApplicationContext(), Otp.class);
                i.putExtra("number", num);
                startActivity(i);
            }
        });

    }
}
