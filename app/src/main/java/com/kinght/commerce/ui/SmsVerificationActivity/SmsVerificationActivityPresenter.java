package com.kinght.commerce.ui.SmsVerificationActivity;
import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import javax.inject.Inject;
public class SmsVerificationActivityPresenter<V extends SmsVerificationActivityMvpView> extends BasePresenter<V> implements SmsVerificationActivityMvpPresenter<V> {@Inject public SmsVerificationActivityPresenter(DataManager dataManager) {super(dataManager);}}