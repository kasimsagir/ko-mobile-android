package com.kinght.commerce.di.modules;


import android.content.Context;


import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.DataManagerImp;
import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.network.ApiServicesImp;
import com.kinght.commerce.data.network.services.ApplicationServices;
import com.kinght.commerce.data.network.services.ApplicationServicesImp;
import com.kinght.commerce.data.network.services.EntryServices;
import com.kinght.commerce.data.network.services.EntryServicesImp;
import com.kinght.commerce.data.network.services.EventServices;
import com.kinght.commerce.data.network.services.EventServicesImp;
import com.kinght.commerce.data.network.services.LotteryServices;
import com.kinght.commerce.data.network.services.LotteryServicesImp;
import com.kinght.commerce.data.network.services.NotificationServices;
import com.kinght.commerce.data.network.services.NotificationServicesImp;
import com.kinght.commerce.data.network.services.PromotionServices;
import com.kinght.commerce.data.network.services.PromotionServicesImp;
import com.kinght.commerce.data.network.services.UserServices;
import com.kinght.commerce.data.network.services.UserServicesImp;
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
    ApiServices provideApiServices(UserServices userServices, ServerServices serverServices, ApplicationServices applicationServices, PromotionServices promotionServices, EntryServices entryServices, LotteryServices lotteryServices, NotificationServices notificationServices, EventServices eventServices) {
        return new ApiServicesImp(userServices,serverServices,applicationServices,promotionServices,entryServices,lotteryServices,notificationServices,eventServices);
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
    UserServices provideRegisterServices(ApiClient apiClient){
        return new UserServicesImp(apiClient);
    }

    @Provides
    @Singleton
    ServerServices provideServerServices(ApiClient apiClient){
        return new ServerServicesImp(apiClient);
    }

    @Provides
    @Singleton
    ApplicationServices provideApplicationServices(ApiClient apiClient){
        return new ApplicationServicesImp(apiClient);
    }

    @Provides
    @Singleton
    PromotionServices providePromotionService(ApiClient apiClient){
        return new PromotionServicesImp(apiClient);
    }

    @Provides
    @Singleton
    EntryServices provideEntryService(ApiClient apiClient){
        return new EntryServicesImp(apiClient);
    }

    @Provides
    @Singleton
    LotteryServices provideLotteryService(ApiClient apiClient){
        return new LotteryServicesImp(apiClient);
    }

    @Provides
    @Singleton
    NotificationServices provideNotificationService(ApiClient apiClient){
        return new NotificationServicesImp(apiClient);
    }

    @Provides
    @Singleton
    EventServices provideEventService(ApiClient apiClient){
        return new EventServicesImp(apiClient);
    }

}
