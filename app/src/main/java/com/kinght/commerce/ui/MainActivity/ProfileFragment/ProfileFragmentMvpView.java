package com.kinght.commerce.ui.MainActivity.ProfileFragment;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface ProfileFragmentMvpView extends MvpView {
    void loadDataToList(List<Entry> entryList);

    void loadUserDataToView(String s, String nickname);
}