package com.kinght.commerce.ui.base;




import com.kinght.commerce.data.DataManager;

import javax.inject.Inject;

public class BasePresenter <V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;

    @Inject
    DataManager dataManager;

    @Inject
    public BasePresenter(DataManager dataManager){
        this.dataManager=dataManager;
    }


    public DataManager getDataManager(){
        return this.dataManager;
    }

    public V getMvpView() {
        return mvpView;
    }

    @Override
    public void onAttach(V mMvpView) {
        mvpView=mMvpView;
    }
}
