package com.kinght.commerce.ui.MainActivity.SearchFragment;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface SearchFragmentMvpView extends MvpView {
    void showServerNameToUser(String name);

    void loadDataToList(List<Entry> response);

    void loadCoinData(int value);
}