package com.kinght.commerce.di.modules;


import android.content.Context;


import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.DataManagerImp;
import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ApiServicesImp;
import com.kinght.commerce.data.pref.PrefHelper;
import com.kinght.commerce.data.pref.PrefHelperImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModules {

    @Provides
    @Singleton
    DataManager provideDataManager(ApiServices apiServices, PrefHelper prefHelper) {
        return new DataManagerImp(apiServices, prefHelper);
    }

    @Provides
    @Singleton
    ApiServices provideApiServices() {
        return new ApiServicesImp();
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(PrefHelper prefHelper) {
        return new ApiClient(prefHelper);
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context) {
        return new PrefHelperImp(context);
    }


}
