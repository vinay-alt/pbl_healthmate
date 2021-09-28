package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
                createDialog(v);
            }
        });

    }

    public void createDialog(View v) {
        num = number.getEditText().getText().toString();
        final Dialog dialog = new Dialog(ForgetPassword.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("Reset Password");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.reset_pass);

        TextView text = (TextView) dialog.findViewById(R.id.verification_text);
        text.setText("Please Enter an OTP that we sent on "+ num +" and click on verify");

        Button dialogButton = (Button) dialog.findViewById(R.id.change);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(ForgetPassword.this, Otp.class);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(this, Account.class);
//        startActivity(i);
        this.finish();
    }
}
