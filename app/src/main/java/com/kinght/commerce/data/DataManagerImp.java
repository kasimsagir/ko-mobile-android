package com.kinght.commerce.data;



import com.kinght.commerce.data.network.ApiServices;
import com.kinght.commerce.data.pref.PrefHelper;

import javax.inject.Inject;

public class DataManagerImp implements DataManager{
    private ApiServices apiServices;
    private PrefHelper prefHelper;


    @Inject
    public DataManagerImp(ApiServices apiServices,PrefHelper prefHelper){
        this.apiServices=apiServices;
        this.prefHelper=prefHelper;
    }


    @Override
    public void saveAuthorizationKey(String authKey) {
        prefHelper.saveAuthorizationKey(authKey);
    }

    @Override
    public String getAuthorizationKey() {
        return prefHelper.getAuthorizationKey();
    }


}
