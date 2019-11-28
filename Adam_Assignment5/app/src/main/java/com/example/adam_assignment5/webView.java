package com.example.adam_assignment5;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class webView extends AppCompatActivity {
    WebView webView;
    Intent intent;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        intent = getIntent();
        url = intent.getStringExtra("url");
        webView.loadUrl(url);

    }
}
