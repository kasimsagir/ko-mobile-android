package com.kinght.commerce.ui.ForgetPasswordActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import br.com.sapereaude.maskedEditText.MaskedEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordActivityMvpView {

    @Inject
    ForgetPasswordActivityMvpPresenter<ForgetPasswordActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_register_phone_edit_text)
    MaskedEditText activityRegisterPhoneEditText;
    @BindView(R.id.activity_forget_password_step_one_button)
    MaterialButton activityForgetPasswordStepOneButton;
    @BindView(R.id.activity_forget_password_step_one_linear_layout)
    LinearLayout activityForgetPasswordStepOneLinearLayout;
    @BindView(R.id.activity_register_sms_code_edit_text)
    EditText activityRegisterSmsCodeEditText;
    @BindView(R.id.activity_forget_password_step_two_button)
    MaterialButton activityForgetPasswordStepTwoButton;
    @BindView(R.id.activity_forget_password_step_two_linear_layout)
    LinearLayout activityForgetPasswordStepTwoLinearLayout;
    @BindView(R.id.activity_register_password_password_one_edit_text)
    EditText activityRegisterPasswordPasswordOneEditText;
    @BindView(R.id.activity_register_password_password_two_edit_text)
    EditText activityRegisterPasswordPasswordTwoEditText;
    @BindView(R.id.activity_forget_password_step_three_button)
    MaterialButton activityForgetPasswordStepThreeButton;
    @BindView(R.id.activity_forget_password_step_three_linear_layout)
    LinearLayout activityForgetPasswordStepThreeLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectForgetPasswordActivity(this);
        presenter.onAttach(this);
    }

    @OnClick({R.id.activity_forget_password_step_one_button, R.id.activity_forget_password_step_two_button, R.id.activity_forget_password_step_three_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_forget_password_step_one_button:
                presenter.forgetPasswordStepOne(activityRegisterPhoneEditText.getText().toString());
                activityForgetPasswordStepOneLinearLayout.setVisibility(View.GONE);
                activityForgetPasswordStepTwoLinearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_forget_password_step_two_button:
                presenter.forgetPasswordStepTwo(activityRegisterSmsCodeEditText.getText().toString());
                activityForgetPasswordStepTwoLinearLayout.setVisibility(View.GONE);
                activityForgetPasswordStepThreeLinearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_forget_password_step_three_button:
                presenter.forgetPasswordStepThree(activityRegisterPasswordPasswordOneEditText.getText().toString(),activityRegisterPasswordPasswordTwoEditText.getText().toString());

                break;
        }
    }

    @Override
    public void openLoginActivity() {

    }
}
