package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class CreateEntryFragment extends BaseFragment implements CreateEntryFragmentMvpView{


    @BindView(R.id.fragment_create_entry_image_select_from_image_view)
    ImageView fragmentCreateEntryImageSelectFromImageView;
    @BindView(R.id.fragment_create_entry_select_server_edit_text)
    EditText fragmentCreateEntrySelectServerEditText;
    @BindView(R.id.fragment_create_entry_header_edit_text)
    EditText fragmentCreateEntryHeaderEditText;
    @BindView(R.id.fragment_create_entry_message_edit_text)
    EditText fragmentCreateEntryMessageEditText;
    @BindView(R.id.fragment_create_entry_price_edit_text)
    EditText fragmentCreateEntryPriceEditText;
    @BindView(R.id.fragment_create_entry_send_button)
    MaterialButton fragmentCreateEntrySendButton;


    @Inject
    CreateEntryFragmentMvpPresenter<CreateEntryFragmentMvpView> presenter;

    private int TAKE_PICTURE=0;
    private int SELECT_GALLERY=1;
    long pictureTimeMillis;

    public CreateEntryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectCreateEntryFragment(this);

        return inflater.inflate(R.layout.fragment_create_entry, container, false);
    }


    @OnClick({R.id.fragment_create_entry_image_select_from_image_view, R.id.fragment_create_entry_send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_create_entry_image_select_from_image_view:
                presenter.selectImageFrom();
                break;
            case R.id.fragment_create_entry_send_button:
                break;
        }
    }

    @Override
    public void showCamera() {

        pictureTimeMillis = System.currentTimeMillis();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + getStringFromResourceId(R.string.app_name));
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + getStringFromResourceId(R.string.app_name) + File.separator + pictureTimeMillis + ".jpg");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getActivity().startActivityForResult(Intent.createChooser(cameraIntent,"Galeriyi Aç" ), TAKE_PICTURE);
    }

    @Override
    public void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Galeriyi Aç"), SELECT_GALLERY);
    }
}
