package com.projectbasedlearning.healthmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.zip.Inflater;

public class Account extends AppCompatActivity {

    ViewPager viewPager;
    Button loginbt, signupbt;
    TextInputLayout username_parent, password_parent, user, pass, phn, email;
    TextView forget;
    int currentposition;
    String valUser, valPass, valphn, valEmail;
    AccountAdaptor accountAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        viewPager = findViewById(R.id.account_pager);
        loginbt = findViewById(R.id.loginbt);
        signupbt = findViewById(R.id.signupbt);
        forget = findViewById(R.id.forget);
        accountAdaptor = new AccountAdaptor(this);
        viewPager.setAdapter(accountAdaptor);
    }

    public void Signup(View view) {
        user = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        phn = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        if (!ValidateUserName(user) | !ValidatePassword(pass) | !ValidatePhoneNumber() | !ValidateEmail()) {
            return;
        }
        Toast.makeText(this, "Username is "+valUser+", Password is "+valPass+", Email is "+valEmail+"and Phone Number is "+valphn, Toast.LENGTH_SHORT).show();
    }

    public void Login(View view) {
        username_parent = findViewById(R.id.username_parent);
        password_parent = findViewById(R.id.password_parent);

        if (!ValidateUserName(username_parent) | !ValidatePassword(password_parent)) {
            return;
        }
        Toast.makeText(this, "Username fetched is "+valUser+" and Password fetched is "+valPass, Toast.LENGTH_SHORT).show();
    }

    public boolean ValidateUserName(TextInputLayout username_parent) {
        valUser = username_parent.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (valUser.isEmpty()) {
            username_parent.setError("Field cannot be empty");
            return false;
        } else if (valUser.length()>20) {
            username_parent.setError("User name is too large");
            return false;
        } else if (!valUser.matches(checkspaces)) {
            username_parent.setError("No white spaces are allowed");
            return false;
        } else {
            username_parent.setError(null);
            username_parent.setErrorEnabled(false);
            return true;
        }
    }

    public boolean ValidatePassword(TextInputLayout password_parent) {
        valPass = password_parent.getEditText().getText().toString().trim();
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
            password_parent.setError("Password should contain atleast 4 characters");
            return false;
        } else {
            password_parent.setError(null);
            password_parent.setErrorEnabled(false);
            return true;
        }
    }

    public boolean ValidatePhoneNumber() {
        valphn = phn.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (valphn.isEmpty()) {
            phn.setError("Field cannot be empty");
            return false;
        } else if (valphn.length()<10) {
            phn.setError("atleast 10 characters required");
            return false;
        } else if (!valphn.matches(checkspaces)) {
            phn.setError("No white spaces are allowed");
            return false;
        } else {
            phn.setError(null);
           phn.setErrorEnabled(false);
            return true;
        }
    }

    public boolean ValidateEmail() {
        valEmail = email.getEditText().getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (valEmail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!valEmail.matches(checkspaces)) {
            email.setError("Not a valid email");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    public void next(View view){
        viewPager.setCurrentItem(currentposition+1);
    }

    public void prev(View view){
        viewPager.setCurrentItem(currentposition-1);

    }

    public void change(View view) {
        Intent i = new Intent(this, ForgetPassword.class);
        startActivity(i);
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentposition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
//        Intent i = new Intent(this, HomeScreen.class);
//        startActivity(i);
        this.finish();
    }
}
