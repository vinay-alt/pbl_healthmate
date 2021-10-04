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
                GenerateOtp(v);
            }
        });

    }

    public void createDialog(View v, String num) {
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

    public void GenerateOtp(View v){
        num = number.getEditText().getText().toString();
        if (!ValidatePhoneNumber(num)) {
            return;
        }
        Toast.makeText(this, "Your Otp is ****", Toast.LENGTH_SHORT).show();
        createDialog(v, num);
    }

    public boolean ValidatePhoneNumber(String valphn) {
        String checkspaces = "\\A\\w{1,20}\\z";
        if (valphn.isEmpty()) {
            number.setError("Field cannot be empty");
            return false;
        } else if (valphn.length()<10) {
            number.setError("atleast 10 characters required");
            return false;
        } else if (!valphn.matches(checkspaces)) {
            number.setError("No white spaces are allowed");
            return false;
        } else {
            number.setError(null);
            number.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(this, Account.class);
//        startActivity(i);
        this.finish();
    }
}
