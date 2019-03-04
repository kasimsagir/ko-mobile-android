package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.User;

public interface ApplicationServices {

    void startApplication(ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getIntermediaries(ServiceCallback<User> userServiceCallback);
}
