package com.kinght.commerce.di.components;






import com.kinght.commerce.di.modules.DataModules;
import com.kinght.commerce.di.modules.PresenterModules;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragment;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragment;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragment;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragment;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivity;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivity;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivity;

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
}
