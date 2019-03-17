package com.kinght.commerce.ui.RegisterActivity;

import android.util.Log;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterActivityPresenter<V extends RegisterActivityMvpView> extends BasePresenter<V> implements RegisterActivityMvpPresenter<V> {

    List<String> serverList;
    String selectedServerId;


    @Inject
    public RegisterActivityPresenter(DataManager dataManager) {
        super(dataManager);
        serverList=new ArrayList<>();
    }

    @Override
    public void showServerList() {
        serverList.clear();
        getDataManager().getServers(new ServiceCallback<List<Servers>>() {
            @Override
            public void onSuccess(List<Servers> response) {
                for(Servers server:response){
                    serverList.add(server.getName());
                }

                getMvpView().showListDialog(serverList, "Server Seç", new ListSelectItem<Integer>() {
                    @Override
                    public void selectedItem(Integer select) {
                        selectedServerId=response.get(select).get_id();
                        getMvpView().showServerNameToUser(response.get(select).getName());
                    }
                });
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
            }
        });
    }

    @Override
    public void register(String name, String surname, String nickname, String phoneNumber, String password) {
        getMvpView().showLoading();
        phoneNumber=phoneNumber.replace("(","").replace(")","").replace("-","");

        getDataManager().registerStepOne(name, surname, password, selectedServerId, nickname, phoneNumber, true, CommonUtils.getPnsToken(), new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().hideLoading();
                getMvpView().openSmsVerificationActivity();

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().hideLoading();
                getMvpView().showError(errorResponse);
            }
        });
    }
}