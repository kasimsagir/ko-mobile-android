package com.kinght.commerce.ui.MainActivity.MainFragment;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class MainFragmentPresenter<V extends MainFragmentMvpView> extends BasePresenter<V> implements MainFragmentMvpPresenter<V> {@Inject public MainFragmentPresenter(DataManager dataManager) {super(dataManager);}}