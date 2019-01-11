package com.kinght.commerce.ui.LoginActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;

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
    @BindView(R.id.activity_login_customer_id_edit_text)
    EditText activityLoginCustomerIdEditText;
    @BindView(R.id.activity_login_password_edit_text)
    EditText activityLoginPasswordEditText;
    @BindView(R.id.activity_login_forget_password_text_view)
    TextView activityLoginForgetPasswordTextView;
    @BindView(R.id.activity_login_check_image_view)
    AppCompatImageView activityLoginCheckImageView;
    @BindView(R.id.activity_login_use_terms_text_view)
    TextView activityLoginUseTermsTextView;
    boolean isCheck = false;
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


    @OnClick({R.id.activity_login_check_image_view, R.id.activitY_login_login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_login_check_image_view:
                if (!isCheck) {
                    activityLoginCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
                    isCheck = true;
                } else {
                    activityLoginCheckImageView.setImageResource(R.mipmap.ic_login_check_off);
                    isCheck = false;
                }
                break;
            case R.id.activitY_login_login_button:
                break;
        }
    }
}
