package com.kinght.commerce.ui.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashActivityMvpView {

    @Inject
    SplashActivityMvpPresenter<SplashActivityMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((MvpApp) getApplication()).getActivityComponent().injectSplashActivity(this);
        presenter.onAttach(this);

        presenter.splashActivity();

    }

    @Override
    public void openMainActivity() {
        CommonUtils.changeActivity(SplashActivity.this, MainActivity.class);
    }

    @Override
    public void openSmsVerificationActivity() {
        CommonUtils.changeActivity(SplashActivity.this, SmsVerificationActivity.class);
    }
}
