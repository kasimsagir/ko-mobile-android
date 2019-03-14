package com.kinght.commerce.ui.EntryDetailActivity;


import android.os.Bundle;
import android.widget.TextView;

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
        activityEntryDetailPriceTextView.setText(String.valueOf(price));
        activityEntryDetailPublishedTextView.setText(nickname);

    }

    @OnClick(R.id.activity_entry_detail_contact_button)
    public void onViewClicked() {
        presenter.showContactList();
    }
}
