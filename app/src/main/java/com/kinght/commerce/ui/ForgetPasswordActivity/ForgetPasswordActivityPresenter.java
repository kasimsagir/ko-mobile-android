package com.kinght.commerce.ui.ForgetPasswordActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class ForgetPasswordActivityPresenter<V extends ForgetPasswordActivityMvpView> extends BasePresenter<V> implements ForgetPasswordActivityMvpPresenter<V> {@Inject public ForgetPasswordActivityPresenter(DataManager dataManager) {super(dataManager);}}