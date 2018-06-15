package com.example.xornor_pc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        String url = getIntent().getExtras().getString("urlString");
        WebView  mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(url);
    }
}
