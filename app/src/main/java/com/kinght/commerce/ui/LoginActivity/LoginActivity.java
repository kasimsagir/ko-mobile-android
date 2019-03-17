package com.kinght.commerce.ui.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityMvpView {


    @Inject
    LoginActivityMvpPresenter<LoginActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_login_phone_edit_text)
    EditText activityLoginCustomePhoneNumber;
    @BindView(R.id.activity_login_password_edit_text)
    EditText activityLoginPasswordEditText;
    @BindView(R.id.activity_login_forget_password_text_view)
    TextView activityLoginForgetPasswordTextView;

    @BindView(R.id.activitY_login_login_button)
    MaterialButton activitYLoginLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectLoginActivity(this);
        presenter.onAttach(this);
    }


    @OnClick({ R.id.activitY_login_login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.activitY_login_login_button:
                presenter.login(CommonUtils.regularText(activityLoginCustomePhoneNumber),CommonUtils.regularText(activityLoginPasswordEditText));
                break;
        }
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
