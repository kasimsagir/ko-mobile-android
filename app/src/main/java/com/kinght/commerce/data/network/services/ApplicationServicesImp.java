package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;

import javax.inject.Inject;

public class ApplicationServicesImp implements ApplicationServices {
    ApiInterface apiInterface;

    @Inject
    public ApplicationServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void startApplication(ServiceCallback<CommonResponse> commonResponseServiceCallback) {

    }
}
