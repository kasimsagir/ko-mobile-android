package com.kinght.commerce.di.components;






import com.kinght.commerce.di.modules.DataModules;
import com.kinght.commerce.di.modules.PresenterModules;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.EventsActivity.EventsActivity;
import com.kinght.commerce.ui.ForgetPasswordActivity.ForgetPasswordActivity;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragment;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragment;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragment;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragment;
import com.kinght.commerce.ui.NotificationSettingsActivity.NotificationSettinsActivity;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivity;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivity;
import com.kinght.commerce.ui.ReportActivity.ReportActivity;
import com.kinght.commerce.ui.SettingsActivity.SettingsActivity;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivity;
import com.kinght.commerce.ui.UpdateProfileActivity.UpdateProfileActivity;
import com.kinght.commerce.ui.UserProfileActivity.UserProfileActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModules.class, DataModules.class})
public interface ViewComponents {
    void injectMainActivity(MainActivity mainActivity);


    void injectLoginFragment(ChooseFragment chooseFragment);

    void injectRegisterActivity(RegisterActivity registerActivity);

    void injectLoginActivity(LoginActivity loginActivity);

    void injectSmsVerificationActivity(SmsVerificationActivity smsVerificationActivity);
    void injectMainFragment(MainFragment mainFragment);

    void injectCreateEntryFragment(CreateEntryFragment createEntryFragment);

    void injectEntryDetailActivity(EntryDetailActivity entryDetailActivity);

    void injectPartipicateActivity(PartipicateActivity partipicateActivity);

    void injectSearchFragment(SearchFragment searchFragment);

    void injectNotificationFragment(NotificationFragment notificationFragment);

    void injectProfileFragment(ProfileFragment profileFragment);

    void injectReportActivity(ReportActivity reportActivity);

    void injectForgetPasswordActivity(ForgetPasswordActivity forgetPasswordActivity);

    void injectUserProfileActivity(UserProfileActivity userProfileActivity);

    void injectSettingsActivity(SettingsActivity settingsActivity);


    void injectUpdateProfileActivity(UpdateProfileActivity updateProfileActivity);

    void injectNotificationSettinsActivity(NotificationSettinsActivity notificationSettinsActivity);

    void injectEventsActivity(EventsActivity eventsActivity);

    void injectNotificationReceiver(NotificationReceiver notificationReceiver);
}
