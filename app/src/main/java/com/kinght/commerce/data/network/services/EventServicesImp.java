package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ApiClient;
import com.kinght.commerce.data.network.ApiInterface;
import com.kinght.commerce.data.network.NetworkError;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Event.EventHours;
import com.kinght.commerce.data.network.entities.Event.EventResponse;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.data.network.entities.Event.HourList;
import com.kinght.commerce.data.network.entities.Event.HourRequest;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventServicesImp implements EventServices {

    ApiInterface apiInterface;

    @Inject
    public EventServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void getEvents(String userId,ServiceCallback<List<Events>> eventsServiceCallback) {
        Call<EventResponse> call=apiInterface.getEvents(userId);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.isSuccessful()){
                    eventsServiceCallback.onSuccess(response.body().getEvents());
                }else {
                    try {
                        eventsServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                eventsServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }

    @Override
    public void updateEventList(String userId, List<EventHours> hoursList, ServiceCallback<CommonResponse> commonResponseServiceCallback) {
        HourRequest hourRequest=new HourRequest();
        List<HourList> hourLists=new ArrayList<>();


        for(EventHours eventHours:hoursList){
            HourList hourList=new HourList();
            hourList.set_id(eventHours.get_id());
            hourLists.add(hourList);
        }
        hourRequest.setHourList(hourLists);

        Call<CommonResponse> call=apiInterface.updateEvents(userId,hourRequest);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    commonResponseServiceCallback.onSuccess(response.body());
                }else {
                    try {
                        commonResponseServiceCallback.onError(response.code(), CommonUtils.errorHandler(response.errorBody().string()).getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonResponseServiceCallback.onError(Constant.ERROR_CODE,new NetworkError(t).response());
            }
        });
    }
}
