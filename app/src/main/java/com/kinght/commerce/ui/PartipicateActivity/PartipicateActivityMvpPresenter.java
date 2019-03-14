package  com.kinght.commerce.ui.PartipicateActivity;
import  com.kinght.commerce.ui.base.MvpPresenter;
public interface PartipicateActivityMvpPresenter<V extends PartipicateActivityMvpView> extends MvpPresenter<V> {
    void getPartipicate();

    void partipicateLottery();
}