package com.kinght.commerce.ui.RegisterActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivity;
import com.kinght.commerce.ui.UseTermsActivity.UseTermsActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
    @BindView(R.id.activity_login_check_image_view)
    AppCompatImageView activityLoginCheckImageView;
    @BindView(R.id.activity_register_phone_number_check_image_view)
    AppCompatImageView activityRegisterPhoneNumberCheckImageView;
    @BindView(R.id.activity_login_use_terms_text_view)
    AutoLinkTextView useTermsTextView;

    boolean isCheck = false;
    boolean isPhoneCheck= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectRegisterActivity(this);

        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        setTitle("Kayıt Ol");

        useTermsTextView.addAutoLinkMode(
                AutoLinkMode.MODE_CUSTOM);
        String burada = "Kullanıcı genel sözleşmesini";
        useTermsTextView.setCustomRegex("\\s" + burada + "\\b");
        useTermsTextView.setAutoLinkText("Bilgilerimi ekleyerek tarafıma ticari elektronik iletiler gönderilmesi için burada belirtilen şartlarda izin veriyorum ve Kullanıcı genel sözleşmesini inceledim.");

        useTermsTextView.setCustomModeColor(ContextCompat.getColor(this, R.color.white));
        useTermsTextView.setSelectedStateColor(ContextCompat.getColor(this, R.color.dark_grey));

        useTermsTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                CommonUtils.changeActivity(RegisterActivity.this, UseTermsActivity.class);
            }
        });
    }

    @OnClick({R.id.activity_register_server_edit_text, R.id.activity_register_register_button, R.id.activity_login_check_image_view,R.id.activity_register_phone_number_check_image_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_register_server_edit_text:
                presenter.showServerList();
                break;
            case R.id.activity_register_register_button:
                presenter.register(CommonUtils.regularText(activityRegisterCustomerNameEditText), CommonUtils.regularText(activityRegisterSurnameEditText), CommonUtils.regularText(activityRegisterCustomerNicknameEditText), CommonUtils.regularText(activityRegisterPhoneEditText), CommonUtils.regularText(activityRegisterPhonePasswordText),isPhoneCheck,isCheck);
                break;
            case R.id.activity_login_check_image_view:
                if (!isCheck) {
                    activityLoginCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
                    isCheck = true;
                } else {
                    activityLoginCheckImageView.setImageResource(R.mipmap.ic_login_check_off);
                    isCheck = false;
                }
                break;
            case R.id.activity_register_phone_number_check_image_view:
                if (!isPhoneCheck) {
                    activityRegisterPhoneNumberCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
                    isPhoneCheck = true;
                } else {
                    activityRegisterPhoneNumberCheckImageView.setImageResource(R.mipmap.ic_login_check_off);
                    isPhoneCheck = false;
                }
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
