package com.kinght.commerce.ui.UpdateEntryActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateEntryActivity extends BaseActivity implements UpdateEntryActivityMvpView {

    @Inject
    UpdateEntryActivityMvpPresenter<UpdateEntryActivityMvpView> presenter;

    String entryId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_update_entry_select_server_edit_text)
    EditText activityUpdateEntrySelectServerEditText;
    @BindView(R.id.activity_update_entry_header_edit_text)
    EditText activityUpdateEntryHeaderEditText;
    @BindView(R.id.activity_update_entry_message_edit_text)
    EditText activityUpdateEntryMessageEditText;
    @BindView(R.id.activity_update_entry_price_edit_text)
    EditText activityUpdateEntryPriceEditText;
    @BindView(R.id.activity_update_entry_update_button)
    MaterialButton activityUpdateEntryUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entry);
        ButterKnife.bind(this);


        ((MvpApp) getApplication()).getActivityComponent().injectUpdateEntryActivity(this);
        presenter.onAttach(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            entryId = bundle.getString(Constant.BUNDLE_ENTRY_ID, "1");
        }

        presenter.getEntryDetail(entryId);
    }

    @Override
    public void entryDetail(String header, String message, Integer price, String name) {
        activityUpdateEntrySelectServerEditText.setText(name);
        activityUpdateEntryHeaderEditText.setText(header);
        activityUpdateEntryMessageEditText.setText(message);
        activityUpdateEntryPriceEditText.setText(String.valueOf(price));
    }

    @Override
    public void getSelectedServerName(String name) {
        activityUpdateEntrySelectServerEditText.setText(name);
    }


    @OnClick({R.id.activity_update_entry_select_server_edit_text, R.id.activity_update_entry_update_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_update_entry_select_server_edit_text:
                presenter.getServers();
                break;
            case R.id.activity_update_entry_update_button:
                presenter.updateEntry(CommonUtils.regularText(activityUpdateEntryHeaderEditText), CommonUtils.regularText(activityUpdateEntryMessageEditText), CommonUtils.tryParse(activityUpdateEntryPriceEditText.getText().toString()));
                break;
        }
    }
}
