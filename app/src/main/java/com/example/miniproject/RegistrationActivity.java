package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private EditText remail,rpassword,rusername,rconfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        initViews();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void initViews() {
        Button regbtn= findViewById(R.id.registerbtn);
        rusername=findViewById(R.id.rusernamealpha);
        remail=findViewById(R.id.remailalpha);
        rpassword=findViewById(R.id.rpasswordalpha);
        rconfirmpassword=findViewById(R.id.confirmpassword);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });
    }

    private void validation() {
        boolean isValidEmail=true;
        boolean isValidPassword=true;
        boolean isValidUsername=true;
        boolean isValidCP=true;
        String vemail=remail.getText().toString();
        String vpassword=rpassword.getText().toString();
        String vusername=rusername.getText().toString();
        String vconfirmpassword=rconfirmpassword.getText().toString();
        String useregex="^[A-Za-z0-9]*$";
        Pattern userpattern=Pattern.compile(useregex);
        if(vusername!=null)
        {
            Matcher matcher=userpattern.matcher(vusername);
            if(matcher.matches())
            {

            }
            else
            {
                rusername.setError("Username should have only letters and numbers");
                isValidUsername=false;
            }

        }
        else
        {
            rusername.setError("Username is empty");
            isValidUsername=false;
        }
        String regex="^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern =Pattern.compile(regex);
        if(vemail.length()>5&&vemail!=null)
        {
            Matcher matcher=pattern.matcher(vemail);
            if(matcher.matches())
            {

            }
            else
            {
                remail.setError("Please enter valid Email-ID");
                isValidEmail=false;
            }
        }
        else
        {
            remail.setError("Please enter valid Email-ID");
            isValidEmail=false;
        }
        if(vpassword.length()<8){
            isValidPassword=false;
            rpassword.setError("Password Should have more than 8 characters");

        }
        if(!vpassword.equalsIgnoreCase(vconfirmpassword))
        {
            isValidCP=false;
            rconfirmpassword.setError("Passwords are not matching");

        }
        if(isValidEmail&&isValidPassword&&isValidUsername&&isValidCP)
        {
            Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
            Intent loginintent=new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(loginintent);
        }
    }
}