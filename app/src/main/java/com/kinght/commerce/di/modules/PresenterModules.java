package com.kinght.commerce.di.modules;

import android.app.Application;
import android.content.Context;


import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivityMvpPresenter;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivityMvpView;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivityPresenter;
import com.kinght.commerce.ui.ForgetPasswordActivity.ForgetPasswordActivityMvpPresenter;
import com.kinght.commerce.ui.ForgetPasswordActivity.ForgetPasswordActivityMvpView;
import com.kinght.commerce.ui.ForgetPasswordActivity.ForgetPasswordActivityPresenter;
import com.kinght.commerce.ui.LoginActivity.LoginActivityMvpPresenter;
import com.kinght.commerce.ui.LoginActivity.LoginActivityMvpView;
import com.kinght.commerce.ui.LoginActivity.LoginActivityPresenter;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.MainActivityMvpPresenter;
import com.kinght.commerce.ui.MainActivity.MainActivityMvpView;
import com.kinght.commerce.ui.MainActivity.MainActivityPresenter;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragmentPresenter;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragmentMvpPresenter;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragmentMvpView;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragmentPresenter;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivityMvpPresenter;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivityMvpView;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivityPresenter;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityMvpPresenter;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityMvpView;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivityPresenter;
import com.kinght.commerce.ui.ReportActivity.ReportActivityMvpPresenter;
import com.kinght.commerce.ui.ReportActivity.ReportActivityMvpView;
import com.kinght.commerce.ui.ReportActivity.ReportActivityPresenter;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivityMvpPresenter;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivityMvpView;
import com.kinght.commerce.ui.SmsVerificationActivity.SmsVerificationActivityPresenter;

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

    @Provides
    SmsVerificationActivityMvpPresenter<SmsVerificationActivityMvpView> provideSmsVerificationActivity(DataManager dataManager){
        return new SmsVerificationActivityPresenter<>(dataManager);
    }

    @Provides
    CreateEntryFragmentMvpPresenter<CreateEntryFragmentMvpView> provideCreateEntryFragment(DataManager dataManager){
        return new CreateEntryFragmentPresenter<>(dataManager);
    }

    @Provides
    EntryDetailActivityMvpPresenter<EntryDetailActivityMvpView> provideEntryDetailActivity(DataManager dataManager){
        return new EntryDetailActivityPresenter<>(dataManager);
    }

    @Provides
    PartipicateActivityMvpPresenter<PartipicateActivityMvpView> providePartipicateActivity(DataManager dataManager){
        return new PartipicateActivityPresenter<>(dataManager);
    }

    @Provides
    SearchFragmentMvpPresenter<SearchFragmentMvpView> provideSearchFragment(DataManager dataManager){
        return new SearchFragmentPresenter<>(dataManager);
    }

    @Provides
    NotificationFragmentMvpPresenter<NotificationFragmentMvpView> provideNotficationFragment(DataManager dataManager){
        return new NotificationFragmentPresenter<>(dataManager);
    }

    @Provides
    ProfileFragmentMvpPresenter<ProfileFragmentMvpView> provideProfileFragment(DataManager dataManager){
        return new ProfileFragmentPresenter<>(dataManager);
    }

    @Provides
    ReportActivityMvpPresenter<ReportActivityMvpView> provideReportActivity(DataManager dataManager){
        return new ReportActivityPresenter<>(dataManager);
    }

    @Provides
    ForgetPasswordActivityMvpPresenter<ForgetPasswordActivityMvpView> provideForgetPasswordActivity(DataManager dataManager){
        return new ForgetPasswordActivityPresenter<>(dataManager);
    }
}
