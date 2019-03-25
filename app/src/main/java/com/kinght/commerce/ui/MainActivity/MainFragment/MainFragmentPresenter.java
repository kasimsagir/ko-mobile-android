package com.kinght.commerce.ui.MainActivity.MainFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainFragmentPresenter<V extends MainFragmentMvpView> extends BasePresenter<V> implements MainFragmentMvpPresenter<V> {
    List<String> serverNameList;

    @Inject
    public MainFragmentPresenter(DataManager dataManager) {
        super(dataManager);
        serverNameList = new ArrayList<>();
    }

    @Override
    public void getServerList() {


        serverNameList.clear();
        getDataManager().getServers(new ServiceCallback<List<Servers>>() {
            @Override
            public void onSuccess(List<Servers> response) {
                for (Servers server : response) {
                    serverNameList.add(server.getName());
                }

                getMvpView().showListDialog(serverNameList, "Server Seç", new ListSelectItem<Integer>() {
                    @Override
                    public void selectedItem(Integer select) {
                        getMvpView().getSelectedServerName(response.get(select).getName());
                        getServerEntries(response.get(select).get_id());

                    }
                });
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }

    @Override
    public void getEntries() {
        getMvpView().showLoading();
        getDataManager().getEntries(new ServiceCallback<List<Entry>>() {
            @Override
            public void onSuccess(List<Entry> response) {
                getMvpView().loadDataToList(response);
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {
                getMvpView().hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {

                getMvpView().hideLoading();
                getMvpView().showError(errorResponse);

            }
        });
    }

    @Override
    public void getServerEntries(String serverId) {
        getMvpView().showLoading();
        getDataManager().getServerEntries(serverId,new ServiceCallback<List<Entry>>() {
            @Override
            public void onSuccess(List<Entry> response) {
                getMvpView().loadDataToList(response);
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {
                getMvpView().hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {

                getMvpView().hideLoading();
                getMvpView().showError(errorResponse);

            }
        });
    }

    @Override
    public void getCoinDetail() {
        getDataManager().startApplication(CommonUtils.getPnsToken(), new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });

        if(getDataManager().getAuthorizationKey() != ""){
            getDataManager().getMe(new ServiceCallback<User>() {
                @Override
                public void onSuccess(User response) {
                    getMvpView().coinDetail(response.getCoin().getValue());

                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {

                }
            });
        }

    }
}