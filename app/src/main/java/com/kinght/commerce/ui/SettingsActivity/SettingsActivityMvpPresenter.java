package  com.kinght.commerce.ui.SettingsActivity;
import  com.kinght.commerce.ui.base.MvpPresenter;
public interface SettingsActivityMvpPresenter<V extends SettingsActivityMvpView> extends MvpPresenter<V> {
    void logOut();
}