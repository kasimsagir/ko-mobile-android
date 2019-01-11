package com.kinght.commerce.ui.MainActivity.ChooseFragment;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class ChooseFragmentPresenter<V extends ChooseFragmentMvpView> extends BasePresenter<V> implements ChooseFragmentMvpPresenter<V> {@Inject public ChooseFragmentPresenter(DataManager dataManager) {super(dataManager);}

    @Override
    public void openLogin() {
        getMvpView().openLoginActivity();
    }

    @Override
    public void openRegister() {
        getMvpView().openRegisterActivity();
    }
}