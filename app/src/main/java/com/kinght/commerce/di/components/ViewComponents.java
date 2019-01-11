package com.kinght.commerce.di.components;






import com.kinght.commerce.di.modules.DataModules;
import com.kinght.commerce.di.modules.PresenterModules;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModules.class, DataModules.class})
public interface ViewComponents {
    void injectMainActivity(MainActivity mainActivity);
    void injectMainFragment(MainFragment mainFragment);


    void injectLoginFragment(ChooseFragment chooseFragment);

    void injectRegisterActivity(RegisterActivity registerActivity);

    void injectLoginActivity(LoginActivity loginActivity);
}
