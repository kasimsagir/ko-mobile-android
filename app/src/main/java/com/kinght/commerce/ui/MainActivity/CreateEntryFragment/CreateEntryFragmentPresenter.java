package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.ui.base.BasePresenter;
import com.kinght.commerce.ui.base.ListSelectItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CreateEntryFragmentPresenter<V extends CreateEntryFragmentMvpView> extends BasePresenter<V> implements CreateEntryFragmentMvpPresenter<V> {
    @Inject
    public CreateEntryFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void selectImageFrom() {
        List<String> strings = new ArrayList<>();
        strings.add("Fotoğraf Çek");
        strings.add("Fotoğraf Yükle");

        getMvpView().showListDialog(strings, "Fotoğraf Yükle", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                if (select == 0) {
                    getMvpView().showCamera();
                } else if (select == 1) {
                    getMvpView().openGallery();
                }
            }
        });

    }
}