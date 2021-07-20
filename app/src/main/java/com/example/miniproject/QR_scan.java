package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

public class QR_scan extends AppCompatActivity {
    ScannerLiveView scannerLiveView;
    TextView scannerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);

        scannerLiveView=findViewById(R.id.camview);
        scannerTV=findViewById(R.id.scanneddata);
        if(checkPermission()){
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermission();
        }
        scannerLiveView.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                Toast.makeText(QR_scan.this,"Scanner started",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                Toast.makeText(QR_scan.this,"Scanner Stopped",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onScannerError(Throwable err) {


            }

            @Override
            public void onCodeScanned(String data) {
                scannerTV.setText(data);
                scannerTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotourl(data);
                    }
                });

            }
        });

    }

    private void gotourl(String s){
        Uri ur=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,ur));
    }


    boolean checkPermission(){
        int camer_permission= ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA);
        int vibrate_permission= ContextCompat.checkSelfPermission(getApplicationContext(),VIBRATE);
        return camer_permission== PackageManager.PERMISSION_GRANTED && vibrate_permission==PackageManager.PERMISSION_GRANTED;

    }
    void requestPermission(){
        int PERMISSION_CODE=200;
        ActivityCompat.requestPermissions(this,new String[]{CAMERA,VIBRATE},PERMISSION_CODE);
    }

    @Override
    protected void onPause() {
        scannerLiveView.stopScanner();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder=new ZXDecoder();
        decoder.setScanAreaPercent(0.8);
        scannerLiveView.setDecoder(decoder);
        scannerLiveView.startScanner();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0){
            boolean camerAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
            boolean vibrationAccepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;

            if(camerAccepted&&vibrationAccepted){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Permission Denied\n Cannot use the app without permission",Toast.LENGTH_SHORT).show();
            }
        }
    }
}