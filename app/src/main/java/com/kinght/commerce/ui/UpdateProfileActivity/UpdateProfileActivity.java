package com.kinght.commerce.ui.UpdateProfileActivity;

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
import br.com.sapereaude.maskedEditText.MaskedEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends BaseActivity implements UpdateProfileActivityMvpView {

    @Inject
    UpdateProfileActivityMvpPresenter<UpdateProfileActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_update_profile_customer_name_edit_text)
    EditText activityUpdateProfileCustomerNameEditText;
    @BindView(R.id.activity_update_profile_surname_edit_text)
    EditText activityUpdateProfileSurnameEditText;
    @BindView(R.id.activity_update_profile_customer_nickname_edit_text)
    EditText activityUpdateProfileCustomerNicknameEditText;
    @BindView(R.id.activity_update_profile_server_edit_text)
    EditText activityUpdateProfileServerEditText;
    @BindView(R.id.activity_update_profile_check_image_view)
    AppCompatImageView activityUpdateProfileCheckImageView;
    @BindView(R.id.activity_login_use_terms_text_view)
    TextView activityLoginUseTermsTextView;
    @BindView(R.id.activity_update_profile_register_button)
    MaterialButton activityUpdateProfileRegisterButton;

    boolean isSelectedShowNumber;
    @BindView(R.id.activity_update_profile_phone_edit_text)
    MaskedEditText activityUpdateProfilePhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectUpdateProfileActivity(this);
        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenter.getMe();
    }

    @OnClick({R.id.activity_update_profile_server_edit_text, R.id.activity_update_profile_check_image_view, R.id.activity_update_profile_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_update_profile_server_edit_text:
                presenter.showServerList();

                break;
            case R.id.activity_update_profile_check_image_view:
                if (!isSelectedShowNumber) {
                    activityUpdateProfileCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
                    isSelectedShowNumber = true;
                } else {
                    activityUpdateProfileCheckImageView.setImageResource(R.mipmap.ic_login_check_off);
                    isSelectedShowNumber = false;
                }

                break;
            case R.id.activity_update_profile_register_button:
                presenter.updateUser(activityUpdateProfileCustomerNameEditText.getText().toString(), activityUpdateProfileSurnameEditText.getText().toString(), activityUpdateProfileCustomerNicknameEditText.getText().toString(), isSelectedShowNumber);
                break;
        }
    }

    @Override
    public void loadDataUser(String name, String surname, String nickname, String name1,String phoneNumber, boolean isShowPhoneNumber) {
        activityUpdateProfileCustomerNameEditText.setText(name);
        activityUpdateProfileSurnameEditText.setText(surname);
        activityUpdateProfileCustomerNicknameEditText.setText(name);
        activityUpdateProfileServerEditText.setText(name1);
        activityUpdateProfilePhoneEditText.setText(phoneNumber);
        this.isSelectedShowNumber = isShowPhoneNumber;
        if (isShowPhoneNumber) {
            activityUpdateProfileCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
        } else {
            activityUpdateProfileCheckImageView.setImageResource(R.mipmap.ic_login_check_off);

        }

    }

    @Override
    public void showServerNameToUser(String name) {
        activityUpdateProfileServerEditText.setText(name);
    }
}
