package com.kinght.commerce.ui.MainActivity.ProfileFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.ui.base.BasePresenter;

import javax.inject.Inject;

public class ProfileFragmentPresenter<V extends ProfileFragmentMvpView> extends BasePresenter<V> implements ProfileFragmentMvpPresenter<V> {
    @Inject
    public ProfileFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getMe() {
        getMvpView().showLoading();
        getDataManager().getMe(new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                getMvpView().loadDataToList(response.getEntryList());
                if(response.isShowPhoneNumber()){
                    getMvpView().loadUserDataToView(response.getPhoneNumber(),response.getNickname());
                }else {
                    getMvpView().loadUserDataToView("",response.getNickname());
                }
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