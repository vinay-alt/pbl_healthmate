package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Otp extends AppCompatActivity {

    TextView txt;
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        txt = findViewById(R.id.msg_txt);
        Intent i = getIntent();
        String str = i.getStringExtra("number");
        txt.setText("Please Enter an OTP that we sent on "+ str +" and click on verify");
        verify = findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Otp.this, "Verified", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
