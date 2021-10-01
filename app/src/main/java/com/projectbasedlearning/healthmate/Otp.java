package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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
        txt.setText("Verified, You can change your password below");
        verify = findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Otp.this, "changed successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Otp.this, Account.class);
//                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(this, ForgetPassword.class);
//        startActivity(i);
        this.finish();

    }
}
