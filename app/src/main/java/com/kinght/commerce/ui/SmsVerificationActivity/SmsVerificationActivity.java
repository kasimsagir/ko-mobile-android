package com.kinght.commerce.ui.SmsVerificationActivity;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsVerificationActivity extends BaseActivity implements SmsVerificationActivityMvpView {
    @Inject
    SmsVerificationActivityMvpPresenter<SmsVerificationActivityMvpView> presenter;

    @BindView(R.id.activity_sms_verification_verificate_code_edit_text)
    AppCompatEditText activitySmsVerificationVerificateCodeEditText;
    @BindView(R.id.activity_sms_verification_verificate_button)
    MaterialButton activitySmsVerificationVerificateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);
        ButterKnife.bind(this);

       ((MvpApp) getApplication()).getActivityComponent().injectSmsVerificationActivity(this);
        presenter.onAttach(this);

    }

    @OnClick(R.id.activity_sms_verification_verificate_button)
    public void onViewClicked() {
        presenter.verificateAccount(CommonUtils.regularText(activitySmsVerificationVerificateCodeEditText));
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
