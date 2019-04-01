package com.kinght.commerce.ui.EntryDetailActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivity;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.DialogCallback;
import com.kinght.commerce.ui.base.DialogStringCallback;
import com.kinght.commerce.ui.base.ListSelectItem;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EntryDetailActivityPresenter<V extends EntryDetailActivityMvpView> extends BasePresenter<V> implements EntryDetailActivityMvpPresenter<V> {
    String userId;
    Entry entry;
    @Inject
    public EntryDetailActivityPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getEntryDetail(String entryId) {
        getMvpView().showLoading();

        getDataManager().getEntry(entryId, new ServiceCallback<Entry>() {
            @Override
            public void onSuccess(Entry response) {
                entry=response;
                getMvpView().loadEntryData(response.getCreator().getName(),response.getCreator().getSurname(),response.getCreatedDate(),response.getCreator().getNickname(),response.getCreator().getPhoneNumber(),response.getServers().getName(),response.getEntryImageUrl(),response.getHeader(),response.getMessage(),response.getPrice());
                getMvpView().hideLoading();
                userId= response.getCreator().get_id();

                if(getDataManager().isItMe(response.getCreator())){
                    getMvpView().entryOwnerConfiguration();
                }
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

    @Override
    public void showContactList() {
        List<String> options=new ArrayList<>();
        options.add("Whatsapp İle İletişime Geç");
        options.add("Telefon İle İletişime Geç");
        options.add("İptal");
        getMvpView().showListDialog(options, "İletişime Geç", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                if(select==0){
                    CommonUtils.sendMessageToUserOnWhatsapp(getMvpView().getActivity(),entry.getCreator().getPhoneNumber());
                }
                if(select == 1){
                    CommonUtils.callPhone(getMvpView().getActivity(),entry.getCreator().getPhoneNumber());

                }
            }
        });
         /*   getDataManager().getIntermediaries(new ServiceCallback<User>() {
                @Override
                public void onSuccess(User response) {


                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int code, String errorResponse) {
                    getMvpView().showError(errorResponse);

                }
            });*/
    }

    @Override
    public void showPublishedProfile() {
        getMvpView().showUserProfileActivity(userId);
    }

    @Override
    public void editEntry() {
        List<String> options=new ArrayList<>();
        options.add("Gönderiyi Sil");
        options.add("Gönderiyi Düzenle");
        options.add("İptal");
        getMvpView().showListDialog(options, "Gönderi Ayarları", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                if(select==0){
                getMvpView().showDialogWithChoose("Emin misiniz?", "İşleme devam ederseniz gönderiyi sileceksiniz", "Evet", "Hayır", new DialogCallback() {
                    @Override
                    public void pressedPossitiveButton() {
                        getMvpView().showLoading();
                        getMvpView().hideLoading();

                        getDataManager().deleteEntry(entry.getId(), new ServiceCallback<CommonResponse>() {
                            @Override
                            public void onSuccess(CommonResponse response) {
                                getMvpView().showDialogWithOutChoose("Başarılı", "Gönderi silindi", "Tamam", new DialogCallback() {
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

                            }

                            @Override
                            public void onError(int code, String errorResponse) {
                                getMvpView().hideLoading();
                                getMvpView().showError(errorResponse);
                            }
                        });
                    }

                    @Override
                    public void pressedNegativeButton() {

                    }
                });

                }
                if(select == 1){
                    getMvpView().showInputDialog("Fiyat Düzenle", String.valueOf(entry.getPrice()), new DialogStringCallback() {
                        @Override
                        public void inputText(String input) {
                            getMvpView().showLoading();
                            entry.setPrice(Integer.parseInt(input));
                            getDataManager().updateEntry(entry, new ServiceCallback<CommonResponse>() {
                                @Override
                                public void onSuccess(CommonResponse response) {

                                    getMvpView().showDialogWithOutChoose("Başarılı", "Fiyat başarıyla güncellendi", "Tamam", new DialogCallback() {
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

                        @Override
                        public void clickCancelButton() {

                        }
                    });
                    //getMvpView().openEntryUpdateActivity(entry.getId());

                }
            }
        });

    }
}