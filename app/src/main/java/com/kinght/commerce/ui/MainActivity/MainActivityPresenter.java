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

        getDataManager().login("05457878383", "123456", new ServiceCallback<CommonResponse>() {
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
       /* getDataManager().startApplication(new ServiceCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        }, new ServiceCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
      /*  getDataManager().registerStepOne("Serkan", "Özaydin", "12345", "5c714821776be3693af2c9b6", "gec", "123", true, "1234", new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });*/

     /* getDataManager().registerStepTwo("1234", new ServiceCallback<CommonResponse>() {
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
/*
        if(getDataManager().getAuthorizationKey() == null){
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


  /*   getDataManager().getServers(new ServiceCallback<List<Servers>>() {
         @Override
         public void onSuccess(List<Servers> response) {

         }

         @Override
         public void onSuccess() {

         }

         @Override
         public void onError(int code, String errorResponse) {

         }
     });*/

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