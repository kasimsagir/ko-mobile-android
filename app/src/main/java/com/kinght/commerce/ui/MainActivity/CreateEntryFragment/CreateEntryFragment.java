package com.kinght.commerce.ui.MainActivity.CreateEntryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.base.BaseFragment;
import com.kinght.commerce.utility.CommonUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreateEntryFragment extends BaseFragment implements CreateEntryFragmentMvpView {


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
    View root;
    private int TAKE_PICTURE = 0;
    private int SELECT_GALLERY = 1;
    long pictureTimeMillis;
    private String base64Image = null;

    public CreateEntryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_create_entry, container, false);
        ButterKnife.bind(this, root);

        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectCreateEntryFragment(this);
        presenter.onAttach(this);

        return root;
    }


    @OnClick({R.id.fragment_create_entry_image_select_from_image_view, R.id.fragment_create_entry_send_button, R.id.fragment_create_entry_select_server_edit_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_create_entry_image_select_from_image_view:
                presenter.selectImageFrom();
                break;
            case R.id.fragment_create_entry_send_button:
                presenter.createEntry(base64Image,CommonUtils.regularText(fragmentCreateEntryHeaderEditText),CommonUtils.regularText(fragmentCreateEntryMessageEditText),Integer.parseInt(fragmentCreateEntryPriceEditText.getText().toString()));
                break;
            case R.id.fragment_create_entry_select_server_edit_text:
                presenter.getServerList();
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
        startActivityForResult(Intent.createChooser(cameraIntent, "Galeriyi Aç"), TAKE_PICTURE);
    }

    @Override
    public void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Galeriyi Aç"), SELECT_GALLERY);
    }

    @Override
    public void getSelectedServerName(String name) {
        fragmentCreateEntrySelectServerEditText.setText(name);
    }

    @Override
    public void openMainFragment() {
        CommonUtils.switchToFragment((AppCompatActivity) getActivity(),new MainFragment());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageUri = null;
        File file = null;
        if (requestCode == SELECT_GALLERY && data != null) {
            imageUri = data.getData();

        } else if (requestCode == TAKE_PICTURE) {
            if (data != null) {
                if (data.getData() != null) {
                    imageUri = data.getData();

                } else {
                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + getStringFromResourceId(R.string.app_name) + File.separator + pictureTimeMillis + ".jpg");

                    imageUri = Uri.fromFile(file);


                }
            } else {
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + getStringFromResourceId(R.string.app_name) + File.separator + pictureTimeMillis + ".jpg");

                imageUri = Uri.fromFile(file);
            }
        }
        if(data != null){
            loadImage(imageUri);

        }

    }

    private void loadImage(Uri imageUri) {
        Picasso.get().load(imageUri).into(fragmentCreateEntryImageSelectFromImageView);
         InputStream imageStream = null;
        try {
            imageStream = getActivity().getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        base64Image = CommonUtils.getImageBase64(selectedImage);
    }


}
