package com.kinght.commerce.ui.MainActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivityPresenter<V extends MainActivityMvpView> extends BasePresenter<V> implements MainActivityMvpPresenter<V> {
    @Inject
    public MainActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void mainFragment() {
        getMvpView().openMainFragment();
    }

    @Override
    public void chooseFragment() {


        if(getDataManager().getAuthorizationKey() == ""){
            getMvpView().openChooseFragment();
        }else {
            getMvpView().openAccountFragment();
        }

        /*if(getDataManager().getAuthorizationKey() == null){
            getMvpView().showDialogWithOutChoose(getMvpView().getStringFromResourceId(R.string.description_info), getMvpView().getStringFromResourceId(R.string.description_if_use_this_func), getMvpView().getStringFromResourceId(R.string.button_ok), new DialogCallback() {
                @Override
                public void pressedPossitiveButton() {
                    getMvpView().openLoginActivity();
                }

                @Override
                public void pressedNegativeButton() {

                }
            });
        }else {
            getMvpView().openAccountFragment();
        }*/




    }

    @Override
    public void addFragment() {
        if(getDataManager().getAuthorizationKey() == null){
            getMvpView().openChooseFragment();
        }else {
            getMvpView().openAccountFragment();
        }
    }
}