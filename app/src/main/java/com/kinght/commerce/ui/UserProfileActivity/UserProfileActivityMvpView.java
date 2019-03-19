package com.kinght.commerce.ui.UserProfileActivity;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface UserProfileActivityMvpView extends MvpView {
    void loadDataToList(List<Entry> entryList);

    void loadUserDataToView(String phoneNumber, String nickname);
}