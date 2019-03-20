package  com.kinght.commerce.ui.SettingsActivity;
import  com.kinght.commerce.data.DataManager;
import  com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.utility.Constant;

import javax.inject.Inject;
public class SettingsActivityPresenter<V extends SettingsActivityMvpView> extends BasePresenter<V> implements SettingsActivityMvpPresenter<V> {@Inject public SettingsActivityPresenter(DataManager dataManager) {super(dataManager);}

    @Override
    public void logOut() {
        Constant.authorizationKey="";
        getDataManager().saveAuthorizationKey("");
        getMvpView().openMainActivity();
    }
}