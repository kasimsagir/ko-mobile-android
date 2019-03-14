package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;
import com.kinght.commerce.ui.base.MvpView;
public interface CreateEntryFragmentMvpView extends MvpView {
    void showCamera();

    void openGallery();

    void getSelectedServerName(String name);

    void openMainFragment();
}