package com.kinght.commerce.ui.RegisterActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterActivityMvpView {

    @Inject
    RegisterActivityPresenter<RegisterActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_register_customer_name_edit_text)
    EditText activityRegisterCustomerNameEditText;
    @BindView(R.id.activity_register_surname_edit_text)
    EditText activityRegisterSurnameEditText;
    @BindView(R.id.activity_register_phone_edit_text)
    EditText activityRegisterPhoneEditText;
    @BindView(R.id.activity_register_server_edit_text)
    EditText activityRegisterServer;
    @BindView(R.id.activity_register_register_button)
    MaterialButton activityRegisterRegisterButton;
    @BindView(R.id.activity_register_customer_nickname_edit_text)
    EditText activityRegisterCustomerNicknameEditText;
    @BindView(R.id.activity_register_phone_password_text)
    EditText activityRegisterPhonePasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectRegisterActivity(this);

        presenter.onAttach(this);

    }

    @OnClick({R.id.activity_register_server_edit_text, R.id.activity_register_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_register_server_edit_text:
                presenter.showServerList();
                break;
            case R.id.activity_register_register_button:
                presenter.register(CommonUtils.regularText(activityRegisterCustomerNameEditText),CommonUtils.regularText(activityRegisterSurnameEditText),CommonUtils.regularText(activityRegisterCustomerNicknameEditText),CommonUtils.regularText(activityRegisterPhoneEditText),CommonUtils.regularText(activityRegisterPhonePasswordText));
                break;
        }
    }

    @Override
    public void showServerNameToUser(String name) {
        activityRegisterServer.setText(name);
    }

    @Override
    public void openSmsVerificationActivity() {
        CommonUtils.changeActivity(RegisterActivity.this, SmsVerificationActivity.class);
    }
}
