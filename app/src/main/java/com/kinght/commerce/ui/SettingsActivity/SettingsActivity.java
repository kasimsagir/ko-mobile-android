package com.kinght.commerce.ui.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.ForgetPasswordActivity.ForgetPasswordActivity;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.NotificationSettingsActivity.NotificationSettinsActivity;
import com.kinght.commerce.ui.UpdateProfileActivity.UpdateProfileActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity implements SettingsActivityMvpView {

    @Inject
    SettingsActivityPresenter<SettingsActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_settings_profile_update_frame_layout)
    FrameLayout activitySettingsProfileUpdateFrameLayout;
    @BindView(R.id.activity_settings_password_update_frame_layout)
    FrameLayout activitySettingsPasswordUpdateFrameLayout;
    @BindView(R.id.activity_settings_notification_configuration_frame_layout)
    FrameLayout activitySettingsNotificationConfigurationFrameLayout;
    @BindView(R.id.activity_settings_logout_frame_layout)
    FrameLayout activitySettingsLogoutFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectSettingsActivity(this);
        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Ayarlar");
    }

    @OnClick({R.id.activity_settings_profile_update_frame_layout, R.id.activity_settings_password_update_frame_layout, R.id.activity_settings_notification_configuration_frame_layout, R.id.activity_settings_logout_frame_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_settings_profile_update_frame_layout:
                CommonUtils.changeActivity(SettingsActivity.this, UpdateProfileActivity.class);
                break;
            case R.id.activity_settings_password_update_frame_layout:
                Intent intent=new Intent(SettingsActivity.this, ForgetPasswordActivity.class);
                intent.putExtra(Constant.BUNDLE_IS_FROM_SETTING,true);
                startActivity(intent);
                break;
            case R.id.activity_settings_notification_configuration_frame_layout:
                CommonUtils.changeActivity(SettingsActivity.this, NotificationSettinsActivity.class);
                break;
            case R.id.activity_settings_logout_frame_layout:
                presenter.logOut();
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
