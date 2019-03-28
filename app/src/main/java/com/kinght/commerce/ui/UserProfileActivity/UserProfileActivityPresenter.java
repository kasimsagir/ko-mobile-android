package com.kinght.commerce.ui.UserProfileActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class UserProfileActivityPresenter<V extends UserProfileActivityMvpView> extends BasePresenter<V> implements UserProfileActivityMvpPresenter<V> {@Inject public UserProfileActivityPresenter(DataManager dataManager) {super(dataManager);}

    @Override
    public void getUser(String userId) {
        getMvpView().showLoading();
        getDataManager().getUser(userId,new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                getMvpView().loadDataToList(response.getEntryList());
                if(response.isShowPhoneNumber()){
                    getMvpView().loadUserDataToView(response.getPhoneNumber(),response.getNickname(),response.getServers().getName());
                }else {
                    getMvpView().loadUserDataToView("",response.getNickname(), response.getServers().getName());
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