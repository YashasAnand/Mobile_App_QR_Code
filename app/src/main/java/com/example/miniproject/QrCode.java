package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QrCode extends AppCompatActivity {

    Button createQrBtn, scanQrBtn;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        createQrBtn = findViewById(R.id.createQrBtn);
        scanQrBtn = findViewById(R.id.scanQrBtn);
        welcomeText = findViewById(R.id.welcomeText);

        createQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(QrCode.this, createQrActivity.class);
                startActivity(createIntent);
            }
        });

        scanQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(QrCode.this,QR_scan.class);
                startActivity(i);

            }
        });
    }
}