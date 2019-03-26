package com.kinght.commerce.ui.ReportActivity;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.DialogCallback;

import javax.inject.Inject;
public class ReportActivityPresenter<V extends ReportActivityMvpView> extends BasePresenter<V> implements ReportActivityMvpPresenter<V> {@Inject public ReportActivityPresenter(DataManager dataManager) {super(dataManager);}

    @Override
    public void reportEntry(String entryId, String text, String text1) {

    getMvpView().showLoading();
    getDataManager().createReport(entryId, text, text1, new ServiceCallback<CommonResponse>() {
        @Override
        public void onSuccess(CommonResponse response) {

            getMvpView().hideLoading();

            getMvpView().showDialogWithOutChoose("Başarılı", "Oluşturduğunuz rapor tarafımıza iletildi", "Tamam", new DialogCallback() {
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