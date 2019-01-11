package com.kinght.commerce.di.modules;

import android.app.Application;
import android.content.Context;


import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.LoginActivity.LoginActivityMvpPresenter;
import com.kinght.commerce.ui.LoginActivity.LoginActivityMvpView;
import com.kinght.commerce.ui.LoginActivity.LoginActivityPresenter;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.MainActivityMvpPresenter;
import com.kinght.commerce.ui.MainActivity.MainActivityMvpView;
import com.kinght.commerce.ui.MainActivity.MainActivityPresenter;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentPresenter;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityMvpPresenter;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityMvpView;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModules {
    private Context context;

    public PresenterModules(Application app){
        this.context=app;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    MainActivityMvpPresenter<MainActivityMvpView> provideMainActivity(DataManager dataManager){
        return new MainActivityPresenter<>(dataManager);
    }

    @Provides
    MainFragmentMvpPresenter<MainFragmentMvpView> provideMainFragment(DataManager dataManager){
        return new MainFragmentPresenter<>(dataManager);
    }
    @Provides
    ChooseFragmentMvpPresenter<ChooseFragmentMvpView> provideLoginFragment(DataManager dataManager){
        return new ChooseFragmentPresenter<>(dataManager);
    }
    @Provides
    RegisterActivityMvpPresenter<RegisterActivityMvpView> provideRegisterActvity(DataManager dataManager){
        return new RegisterActivityPresenter<>(dataManager);
    }
    @Provides
    LoginActivityMvpPresenter<LoginActivityMvpView> provideLoginActivity(DataManager dataManager){
        return new LoginActivityPresenter<>(dataManager);
    }

}
