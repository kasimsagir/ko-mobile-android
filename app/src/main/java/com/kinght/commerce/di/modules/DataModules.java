package com.kinght.commerce.di.modules;


import android.content.Context;


import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.DataManagerImp;
import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ApiServicesImp;
import com.kinght.commerce.data.network.services.RegisterServices;
import com.kinght.commerce.data.network.services.RegisterServicesImp;
import com.kinght.commerce.data.network.services.ServerServices;
import com.kinght.commerce.data.network.services.ServerServicesImp;
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
    ApiServices provideApiServices(RegisterServices registerServices,ServerServices serverServices) {
        return new ApiServicesImp(registerServices,serverServices);
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(PrefHelper prefHelper,Context context) {
        return new ApiClient(prefHelper,context);
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context) {
        return new PrefHelperImp(context);
    }

    @Provides
    @Singleton
    RegisterServices provideRegisterServices(ApiClient apiClient){
        return new RegisterServicesImp(apiClient);
    }

    @Provides
    @Singleton
    ServerServices provideServerServices(ApiClient apiClient){
        return new ServerServicesImp(apiClient);
    }
}
