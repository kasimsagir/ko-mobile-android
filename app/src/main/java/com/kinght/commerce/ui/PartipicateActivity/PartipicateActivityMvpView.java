package  com.kinght.commerce.ui.PartipicateActivity;
import com.kinght.commerce.data.network.entities.Entries.User;
import  com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface PartipicateActivityMvpView extends MvpView {
    void loadDataOfLottery(List<User> participants);
}