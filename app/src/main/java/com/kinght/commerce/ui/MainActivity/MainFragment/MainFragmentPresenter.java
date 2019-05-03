package com.kinght.commerce.ui.MainActivity.MainFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Promotion.Coin;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.ProductListActivity.ProductListActivity;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainFragmentPresenter<V extends MainFragmentMvpView> extends BasePresenter<V> implements MainFragmentMvpPresenter<V> {
    List<String> serverNameList;
    String serverId = null;
    List<Entry> entryList;
    Integer selectFilterType = null;

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
                        serverId = response.get(select).get_id();

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
                entryList = response;

                if (selectFilterType != null) {
                    for (int i = 1; i < entryList.size(); i++) {
                        filter(selectFilterType);
                    }
                }else {
                    getMvpView().loadDataToList(response);

                }

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

    @Override
    public void getCoinDetail() {

        if (getDataManager().getAuthorizationKey() != "") {
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
                    // getMvpView().showError(errorResponse);
                }
            });
        }

    }

    @Override
    public void refresh() {
        if (serverId == null) {
            getEntries();
        } else {
            getServerEntries(serverId);
        }
    }

    @Override
    public void showFilter() {
        List<String> options = new ArrayList<>();
        options.add("En Yeni");
        options.add("En Eski");
        options.add("En Ucuz");
        options.add("En Pahalı");
        getMvpView().showListDialog(options, "Filtreleme", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                selectFilterType=select;
                for (int i = 1; i < entryList.size(); i++) {
                    filter(select);
                }
                getMvpView().setFilterName(options.get(select));
            }
        });
    }

    @Override
    public void openProductList() {

        if(getDataManager().getAuthorizationKey().equals("")){
            getMvpView().showError("Lütfen giriş yapınız");
        }else {
            getMvpView().openProductListActivity();
        }

    }


    public void filter(int type) {
        Entry entry = null;
        int indexOfSwap = 0;
        for (int i = 0; i < entryList.size(); i++) {
            if (entry == null) {
                indexOfSwap = i;
                entry = entryList.get(i);
            } else {
                if (type == 1) {
                    if (entryList.get(i).getCreatedDate() < entry.getCreatedDate()) {
                        entryList.set(indexOfSwap, entryList.get(i));
                        entryList.set(i, entry);
                        indexOfSwap = i;
                        entry = entryList.get(i);
                    }

                } else if (type == 0) {
                    if (entryList.get(i).getCreatedDate() > entry.getCreatedDate()) {
                        entryList.set(indexOfSwap, entryList.get(i));
                        entryList.set(i, entry);
                        indexOfSwap = i;
                        entry = entryList.get(i);
                    }
                } else if (type == 3) {
                    if (entryList.get(i).getPrice() > entry.getPrice()) {
                        entryList.set(indexOfSwap, entryList.get(i));
                        entryList.set(i, entry);
                        indexOfSwap = i;
                        entry = entryList.get(i);
                    }
                } else if (type == 2) {
                    if (entryList.get(i).getPrice() < entry.getPrice()) {
                        entryList.set(indexOfSwap, entryList.get(i));
                        entryList.set(i, entry);
                        indexOfSwap = i;
                        entry = entryList.get(i);
                    }
                }
            }

        }
        getMvpView().loadDataToList(entryList);

    }

}