package com.kinght.commerce.ui.UseTermsActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UseTermsActivity extends BaseActivity {

    @BindView(R.id.activitY_use_terms_web_view)
    WebView activitYUseTermsWebView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String html="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_terms);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        WebSettings settings = activitYUseTermsWebView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");

        activitYUseTermsWebView.loadUrl("file:///android_asset/useterms.html");   // now it will not fail here


    }
}
