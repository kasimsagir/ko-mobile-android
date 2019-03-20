package com.kinght.commerce.ui.UpdateProfileActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UpdateProfileActivityPresenter<V extends UpdateProfileActivityMvpView> extends BasePresenter<V> implements UpdateProfileActivityMvpPresenter<V> {

    List<String> serverList;
    String selectedServerId;

    @Inject
    public UpdateProfileActivityPresenter(DataManager dataManager) {
        super(dataManager);
        serverList = new ArrayList<>();

    }

    @Override
    public void getMe() {
        getMvpView().showLoading();
        getDataManager().getMe(new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                getMvpView().loadDataUser(response.getName(), response.getSurname(), response.getNickname(), response.getServers().getName(), response.getPhoneNumber(),response.getIsShowPhoneNumber());
                selectedServerId=response.getServers().get_id();
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {

                getMvpView().hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void showServerList() {
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
    public void updateUser(String name, String surname, String nickname, boolean isSelectedShowNumber) {
        if(!CommonUtils.isRegularText(name)){
            getMvpView().showError("Lütfen tüm alanları doldurunuz");
        }else if(!CommonUtils.isRegularText(surname)){
            getMvpView().showError("Lütfen tüm alanları doldurunuz");
        }else if(!CommonUtils.isRegularText(name)){
            getMvpView().showError("Lütfen tüm alanları doldurunuz");
        }else {
            getMvpView().showLoading();
            getDataManager().updateProfile(nickname, name, surname, selectedServerId, isSelectedShowNumber, new ServiceCallback<CommonResponse>() {
                @Override
                public void onSuccess(CommonResponse response) {
                    getMvpView().getActivity().onBackPressed();
                    getMvpView().hideLoading();
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
}