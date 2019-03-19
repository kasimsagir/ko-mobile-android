package com.kinght.commerce.ui.ReportActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.Constant;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends BaseActivity implements ReportActivityMvpView {

    String entryId;

    @Inject
    ReportActivityMvpPresenter<ReportActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_report_header_edit_text)
    EditText activityReportHeaderEditText;
    @BindView(R.id.activity_report_message_edit_text)
    EditText activityReportMessageEditText;
    @BindView(R.id.activity_report_report_button)
    MaterialButton activityReportReportButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectReportActivity(this);
        presenter.onAttach(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            entryId = bundle.getString(Constant.BUNDLE_ENTRY_ID, "1");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @OnClick(R.id.activity_report_report_button)
    public void onViewClicked() {
        presenter.reportEntry(entryId, activityReportHeaderEditText.getText().toString(), activityReportMessageEditText.getText().toString());

    }
}
