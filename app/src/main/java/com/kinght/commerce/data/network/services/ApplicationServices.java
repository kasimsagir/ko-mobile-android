package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;

public interface ApplicationServices {

    void startApplication(ServiceCallback<CommonResponse> commonResponseServiceCallback);
}
