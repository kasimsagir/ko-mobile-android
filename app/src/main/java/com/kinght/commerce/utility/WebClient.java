package com.kinght.commerce.utility;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class WebClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView view, String url ) {

    }

    @Override
    public boolean shouldOverrideUrlLoading( WebView view, final String url ) {
        if(!url.contains("as"))
        {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }
}