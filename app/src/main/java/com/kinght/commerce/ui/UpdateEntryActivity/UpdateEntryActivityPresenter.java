package com.kinght.commerce.ui.UpdateEntryActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.DialogCallback;
import com.kinght.commerce.ui.base.ListSelectItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UpdateEntryActivityPresenter<V extends UpdateEntryActivityMvpView> extends BasePresenter<V> implements UpdateEntryActivityMvpPresenter<V> {
    Entry entry;
    String entryId;
    String serverId=null;
    List<String> serverNameList;

    @Inject
    public UpdateEntryActivityPresenter(DataManager dataManager) {
        super(dataManager);
        serverNameList=new ArrayList<>();
    }

    @Override
    public void getEntryDetail(String entryId) {
        this.entryId=entryId;
        getMvpView().showLoading();
        getDataManager().getEntry(entryId, new ServiceCallback<Entry>() {
            @Override
            public void onSuccess(Entry response) {
                entry=response;
                serverId=response.getServers().get_id();
                getMvpView().entryDetail(response.getHeader(),response.getMessage(),response.getPrice(),response.getServers().getName());
                getMvpView().hideLoading();
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

                getMvpView().showError(entryId);
                getMvpView().hideLoading();
            }
        });

    }

    @Override
    public void updateEntry(String regularText, String regularText1, Integer tryParse) {
        if (regularText.isEmpty()){
            getMvpView().showError("Lütfen başlık ekleyiniz");
        }else if(regularText1.isEmpty()){
            getMvpView().showError("Lütfen mesaj ekleyiniz");
        }else if(tryParse == null){
            getMvpView().showError("Lütfen itemin ücretini belirleyiniz");
        }else if (serverId == null){
            getMvpView().showError("Lütfen gönderiyi paylaşmak istediğiniz serverı seçiniz");
        } else{
            getMvpView().showLoading();
            entry.setHeader(regularText);
            entry.setMessage(regularText1);
            entry.setPrice(tryParse);
            Servers servers=new Servers();
            servers.set_id(serverId);
            entry.setServers(servers);

            getDataManager().updateEntry(entry, new ServiceCallback<CommonResponse>() {
                @Override
                public void onSuccess(CommonResponse response) {
                    getMvpView().showDialogWithOutChoose("Başarılı", "Gönderiniz başarıyla güncellendi. Yönetici onayından geçtikten sonra yayınlanacaktır", "Tamam", new DialogCallback() {
                        @Override
                        public void pressedPossitiveButton() {
                            getMvpView().getActivity().onBackPressed();
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
    public void getServers() {
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
}