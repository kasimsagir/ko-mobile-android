package com.kinght.commerce.ui.MainActivity.SearchFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchFragmentPresenter<V extends SearchFragmentMvpView> extends BasePresenter<V> implements SearchFragmentMvpPresenter<V> {

    List<String> serverList = new ArrayList<>();
    List<Entry> entryList = new ArrayList<>();

    String selectedServerId;

    @Inject
    public SearchFragmentPresenter(DataManager dataManager) {
        super(dataManager);

    }

    @Override
    public void getServers() {
        serverList.clear();
        getDataManager().getServers(new ServiceCallback<List<Servers>>() {
            @Override
            public void onSuccess(List<Servers> response) {
                for (Servers server : response) {
                    serverList.add(server.getName());
                }

                getMvpView().showListDialog(serverList, "Server Seç", new ListSelectItem<Integer>() {
                    @Override
                    public void selectedItem(Integer select) {
                        selectedServerId = response.get(select).get_id();
                        getMvpView().showServerNameToUser(response.get(select).getName());
                        getServerEntries(response.get(select).get_id());

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
    public void getFilterEntries(String input) {
        List<Entry> entries = new ArrayList<>();
        for (Entry entry : entryList) {
            if (entry.getHeader().contains(input) || entry.getMessage().contains(input)) {
                entries.add(entry);
            }
        }
        getMvpView().loadDataToList(entries);
    }

    @Override
    public void getAllEntries() {
        getDataManager().getEntries(new ServiceCallback<List<Entry>>() {
            @Override
            public void onSuccess(List<Entry> response) {
                entryList = response;
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
    public void getMe() {
        getDataManager().getMe(new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                getMvpView().loadCoinData(response.getCoin().getValue());
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }

    public void getServerEntries(String serverId) {
        getMvpView().showLoading();
        getDataManager().getServerEntries(serverId, new ServiceCallback<List<Entry>>() {
            @Override
            public void onSuccess(List<Entry> response) {
                entryList = response;
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
}