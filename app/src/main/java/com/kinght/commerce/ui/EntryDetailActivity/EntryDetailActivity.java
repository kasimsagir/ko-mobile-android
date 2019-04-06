package com.kinght.commerce.ui.EntryDetailActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.ReportActivity.ReportActivity;
import com.kinght.commerce.ui.UpdateEntryActivity.UpdateEntryActivity;
import com.kinght.commerce.ui.UserProfileActivity.UserProfileActivity;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class EntryDetailActivity extends BaseActivity implements EntryDetailActivityMvpView {
    @Inject
    EntryDetailActivityMvpPresenter<EntryDetailActivityMvpView> presenter;

    String entryId;
    @BindView(R.id.activity_entry_detail)
    ImageViewZoom activityEntryDetail;
    @BindView(R.id.activity_entry_detail_server_name_text_view)
    TextView activityEntryDetailServerNameTextView;
    @BindView(R.id.activity_entry_detail_advertisement_text_view)
    TextView activityEntryDetailAdvertisementTextView;
    @BindView(R.id.activity_entry_detail_title_text_view)
    TextView activityEntryDetailTitleTextView;
    @BindView(R.id.activity_entry_detail_description_text_view)
    TextView activityEntryDetailDescriptionTextView;
    @BindView(R.id.activity_entry_detail_price_text_view)
    TextView activityEntryDetailPriceTextView;
    @BindView(R.id.activity_entry_detail_published_text_view)
    TextView activityEntryDetailPublishedTextView;
    @BindView(R.id.activity_entry_detail_contact_button)
    MaterialButton activityEntryDetailContactButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_entry_report_button)
    ImageView activityEntryReportButton;
    @BindView(R.id.activity_entry_detail_entry_config_button)
    MaterialButton activityEntryDetailEntryConfigButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectEntryDetailActivity(this);
        presenter.onAttach(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            entryId = bundle.getString(Constant.BUNDLE_ENTRY_ID, "1");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        presenter.getEntryDetail(entryId);

    }

    @Override
    public void loadEntryData(String name, String surname, long createdDate, String nickname, String phoneNumber, String name1, String entryImageUrl, String header, String message, Integer price) {
        setTitle(header);
        CommonUtils.getImageWithCache(activityEntryDetail, entryImageUrl);
        activityEntryDetailServerNameTextView.setText(name1);
        activityEntryDetailAdvertisementTextView.setText(CommonUtils.longToddMMMyyyyHHMMss(createdDate));
        activityEntryDetailTitleTextView.setText(header);
        activityEntryDetailDescriptionTextView.setText(message);
        activityEntryDetailPriceTextView.setText(String.valueOf(price) + " " + getString(R.string.tl));
        activityEntryDetailPublishedTextView.setText(nickname);

    }

    @Override
    public void showUserProfileActivity(String userId) {
        Intent intent = new Intent(EntryDetailActivity.this, UserProfileActivity.class);
        intent.putExtra(Constant.BUNDLE_USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void entryOwnerConfiguration() {
        activityEntryDetailContactButton.setVisibility(View.GONE);
        activityEntryDetailEntryConfigButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void openEntryUpdateActivity(String id) {
        Intent intent = new Intent(EntryDetailActivity.this, UpdateEntryActivity.class);
        intent.putExtra(Constant.BUNDLE_ENTRY_ID, id);
        startActivity(intent);
    }

    @OnClick({R.id.activity_entry_detail_contact_button, R.id.activity_entry_report_button, R.id.activity_entry_detail_published_text_view,R.id.activity_entry_detail_entry_config_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_entry_detail_contact_button:
                presenter.showContactList();
                break;
            case R.id.activity_entry_report_button:
                Intent intent = new Intent(EntryDetailActivity.this, ReportActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, entryId);
                startActivity(intent);
                break;
            case R.id.activity_entry_detail_published_text_view:
                presenter.showPublishedProfile();
                break;
            case R.id.activity_entry_detail_entry_config_button:
                presenter.editEntry();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Constant.isOpenNotification){
            CommonUtils.changeActivity(EntryDetailActivity.this, MainActivity.class);
            Constant.isOpenNotification=false;
            this.finish();
        }
    }
}
