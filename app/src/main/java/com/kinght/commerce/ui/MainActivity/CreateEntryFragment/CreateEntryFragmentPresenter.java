package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.DialogCallback;
import com.kinght.commerce.ui.base.ListSelectItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CreateEntryFragmentPresenter<V extends CreateEntryFragmentMvpView> extends BasePresenter<V> implements CreateEntryFragmentMvpPresenter<V> {
    List<String> serverNameList;
    String serverId;

    @Inject
    public CreateEntryFragmentPresenter(DataManager dataManager) {
        super(dataManager);
        serverNameList = new ArrayList<>();

    }

    @Override
    public void selectImageFrom() {
        List<String> strings = new ArrayList<>();
        strings.add("Fotoğraf Çek");
        strings.add("Fotoğraf Yükle");

        getMvpView().showListDialog(strings, "Fotoğraf Yükle", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                if (select == 0) {
                    getMvpView().showCamera();
                } else if (select == 1) {
                    getMvpView().openGallery();
                }
            }
        });

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
                        serverId=response.get(select).get_id();

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
    public void createEntry(String base64Image, String header, String message,Integer price) {
        if(base64Image.isEmpty()){
            getMvpView().showError("Lütfen item fotoğrafını ekleyiniiz");
        }else if (header.isEmpty()){
            getMvpView().showError("Lütfen başlık ekleyiniz");
        }else if(message.isEmpty()){
            getMvpView().showError("Lütfen mesaj ekleyiniz");
        }else if (serverId == null){
            getMvpView().showError("Lütfen gönderiyi paylaşmak istediğiniz serverı seçiniz");
        } else{
            if(price == null){
                price=0;
            }
            getMvpView().showLoading();
            getDataManager().createEntry(serverId, header, message, price, base64Image, new ServiceCallback<CommonResponse>() {
                @Override
                public void onSuccess(CommonResponse response) {
                    getMvpView().showDialogWithOutChoose("Başarılı", "Gönderinizin onaylanması için bekleyiniz", "Tamam", new DialogCallback() {
                        @Override
                        public void pressedPossitiveButton() {
                            getMvpView().openMainFragment();
                        }

                        @Override
                        public void pressedNegativeButton() {

                        }
                    });
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

    @Override
    public void clearSelectedServerId() {
        serverId=null;
    }
}