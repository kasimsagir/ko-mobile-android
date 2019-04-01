package com.kinght.commerce.ui.NotificationSettingsActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.data.network.entities.Settings.Settings;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.DialogCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotificationSettingsActivityPresenter<V extends NotificationSettingsActivityMvpView> extends BasePresenter<V> implements NotificationSettingsActivityMvpPresenter<V> {
    List<Settings> settingsList;

    @Inject
    public NotificationSettingsActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getSettings() {
        getMvpView().showLoading();
        getDataManager().getSettings(new ServiceCallback<List<Settings>>() {
            @Override
            public void onSuccess(List<Settings> response) {
                settingsList=response;
                getMvpView().loadDataToList(response);
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
    public void setPositionData(Settings item, int position) {
        settingsList.set(position,item);
    }

    @Override
    public void updateSettings() {
        getMvpView().showLoading();
        List<Settings> selectedList=new ArrayList<>();

        for(Settings settings:settingsList){
            if(settings.getIsLiked()){
                selectedList.add(settings);
            }
        }

        getDataManager().updateSettings(selectedList, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getMvpView().hideLoading();

                getMvpView().showDialogWithOutChoose("Başarılı", "Bildirim ayarlarınız başarıyla kaydedildi", "Tamam", new DialogCallback() {
                    @Override
                    public void pressedPossitiveButton() {
                        getMvpView().getActivity().onBackPressed();

                    }

                    @Override
                    public void pressedNegativeButton() {

                    }
                });


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
}