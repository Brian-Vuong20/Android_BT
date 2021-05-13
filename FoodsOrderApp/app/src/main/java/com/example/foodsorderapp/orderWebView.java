package com.example.foodsorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class orderWebView extends AppCompatActivity {


    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_web_view);
        webView = (WebView) findViewById(R.id.webView);
        String data = getIntent().getStringExtra("data");
        webView.setWebViewClient(new WebViewClient());
        webView.loadData("<html><body>" + data +"</body></html>","text/html", "UTF-8");

    }

}

