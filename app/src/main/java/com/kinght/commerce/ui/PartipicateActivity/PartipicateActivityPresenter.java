package  com.kinght.commerce.ui.PartipicateActivity;
import  com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;
import  com.kinght.commerce.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;
public class PartipicateActivityPresenter<V extends PartipicateActivityMvpView> extends BasePresenter<V> implements PartipicateActivityMvpPresenter<V> {@Inject public PartipicateActivityPresenter(DataManager dataManager) {super(dataManager);}

    String lotteryId;
    @Override
    public void getPartipicate() {

    getMvpView().showLoading();

    getDataManager().getLotteries(new ServiceCallback<List<Lottery>>() {
        @Override
        public void onSuccess(List<Lottery> response) {
            getMvpView().hideLoading();
            getDataManager().getLottery(response.get(0).getId(), new ServiceCallback<Lottery>() {
                @Override
                public void onSuccess(Lottery response) {
                    lotteryId=response.getId();
                    getMvpView().loadDataOfLottery(response.getParticipants());
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
    public void partipicateLottery() {
        getMvpView().showLoading();
        getDataManager().partipicateLottery(lotteryId, new ServiceCallback<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                getPartipicate();
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