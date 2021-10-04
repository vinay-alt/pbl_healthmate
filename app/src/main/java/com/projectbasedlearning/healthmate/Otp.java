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

import com.google.android.material.textfield.TextInputLayout;

public class Otp extends AppCompatActivity {

    TextView txt;
    Button verify;
    TextInputLayout newpass, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        txt = findViewById(R.id.msg_txt);
        txt.setText("You can change your password below");
        verify = findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassVal();
            }
        });
    }

    public void PassVal() {
        if (!ResetPass()) {
            return;
        }
        Toast.makeText(Otp.this, "changed successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Otp.this, Account.class);
//                startActivity(i);
        finish();
    }

    public boolean ResetPass() {
        newpass = findViewById(R.id.newpass);
        repass = findViewById(R.id.repass);
        String valnewPass = newpass.getEditText().getText().toString().trim();
        String valrepass = repass.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";
        if (!ValidatePassword(newpass) | !ValidatePassword(repass)) {
            return false;
        } else {
            if (!valnewPass.equals(valrepass)) {
                newpass.setError("Both fields must have same value");
                repass.setError("Both fields must have same value");
                return false;
            } else {
                newpass.setError(null);
                repass.setError(null);
                newpass.setErrorEnabled(false);
                repass.setErrorEnabled(false);
                return true;
            }
        }
    }

    public boolean ValidatePassword(TextInputLayout password_parent) {
        String valPass = password_parent.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";
        if (valPass.isEmpty()) {
            password_parent.setError("Field cannot be empty");
            return false;
        } else if (!valPass.matches(checkPassword)) {
            password_parent.setError("atleast 4 characters, 1 special character and no white spaces required");
            return false;
        } else {
            password_parent.setError(null);
            password_parent.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(this, ForgetPassword.class);
//        startActivity(i);
        this.finish();

    }
}
