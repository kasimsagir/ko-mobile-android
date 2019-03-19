package com.kinght.commerce.ui.MainActivity.MainFragment;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.ui.base.MvpView;

import java.util.List;

public interface MainFragmentMvpView extends MvpView {
    void loadDataToList(List<Entry> response);

    void getSelectedServerName(String name);

    void coinDetail(int value);
}