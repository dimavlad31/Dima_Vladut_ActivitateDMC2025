package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivityLaborator9 extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laborator9);

        webView = findViewById(R.id.webView);

        String webUrl = getIntent().getStringExtra("webUrl");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(webUrl);
    }
}
