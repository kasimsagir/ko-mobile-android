package com.kinght.commerce.ui.UpdateProfileActivity;
import com.kinght.commerce.ui.base.MvpView;
public interface UpdateProfileActivityMvpView extends MvpView {
    void loadDataUser(String name, String surname, String nickname, String name1,String phoneNumber, boolean isShowPhoneNumber);

    void showServerNameToUser(String name);
}