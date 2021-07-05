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

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    public EditText username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void initViews() {
        Button loginbtnb= findViewById(R.id.loginbtn);
        Button signupbtnb=findViewById(R.id.signupbtn);
         username=findViewById(R.id.usernamev);
         email=findViewById(R.id.emailv);
         password=findViewById(R.id.passwordv);

        signupbtnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupintent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(signupintent);
            }
        });
        loginbtnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
//                Intent loginintent=new Intent(LoginActivity.this,QrCode.class);
//                startActivity(loginintent);

                }




        });
    }

    private void validation() {
        boolean isValidEmail=true;
        boolean isValidPassword=true;
        boolean isValidUsername=true;
        String vemail=email.getText().toString();
        String vpassword=password.getText().toString();
        String vusername=username.getText().toString();
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
                username.setError("Username should have only letters and numbers");
                isValidUsername=false;
            }

        }
        else
        {
            username.setError("Username is empty");
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
                email.setError("Please enter valid Email-ID");
                isValidEmail=false;
            }
        }
        else
        {
            email.setError("Please enter valid Email-ID");
            isValidEmail=false;
        }
         if(vpassword.length()<8){
            isValidPassword=false;
            password.setError("Password Should have more than 8 characters");

        }
        if(isValidEmail&&isValidPassword&&isValidUsername)
        {
            Intent loginintent=new Intent(LoginActivity.this,QrCode.class);
            startActivity(loginintent);



        }


    }

}