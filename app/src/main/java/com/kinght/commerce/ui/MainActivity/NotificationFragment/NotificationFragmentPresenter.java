package com.kinght.commerce.ui.MainActivity.NotificationFragment;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;
public class NotificationFragmentPresenter<V extends NotificationFragmentMvpView> extends BasePresenter<V> implements NotificationFragmentMvpPresenter<V> {@Inject public NotificationFragmentPresenter(DataManager dataManager) {super(dataManager);}

    @Override
    public void getNotifications() {
        getMvpView().showLoading();
        getDataManager().getNotifications(new ServiceCallback<List<Notifications>>() {
            @Override
            public void onSuccess(List<Notifications> response) {

                getMvpView().loadDataToList(response);
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {
                getMvpView().showError(errorResponse);
                getMvpView().hideLoading();
            }
        });
    }
}