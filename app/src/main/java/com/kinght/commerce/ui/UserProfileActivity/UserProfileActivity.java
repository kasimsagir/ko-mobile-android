package com.kinght.commerce.ui.UserProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.adapters.EntryRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends BaseActivity implements UserProfileActivityMvpView {

    @Inject
    UserProfileActivityMvpPresenter<UserProfileActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_user_profile_user)
    ImageView activityUserProfileUser;
    @BindView(R.id.activity_user_profile_nickname_text_view)
    TextView activityUserProfileNicknameTextView;
    @BindView(R.id.activity_user_profile_phone_number_text_view)
    TextView activityUserProfilePhoneNumberTextView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.activity_user_profile_recylerview)
    RecyclerView activityUserProfileRecylerview;

    EntryRecylerViewAdapters entryRecylerViewAdapters;

    String userId;
    @BindView(R.id.activity_user_profile_server_name_text_view)
    TextView activityUserProfileServerNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        ((MvpApp) getApplication()).getActivityComponent().injectUserProfileActivity(this);
        presenter.onAttach(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            userId = bundle.getString(Constant.BUNDLE_USER_ID, "");
        }


        presenter.getUser(userId);
        entryRecylerViewAdapters = new EntryRecylerViewAdapters(new EntryRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Entry item) {
                Intent intent = new Intent(getActivity(), EntryDetailActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void loadDataToList(List<Entry> entryList) {
        entryRecylerViewAdapters.setData(entryList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activityUserProfileRecylerview.setLayoutManager(manager);
        activityUserProfileRecylerview.setAdapter(entryRecylerViewAdapters);
    }

    @Override
    public void loadUserDataToView(String s, String nickname, String serverName) {
        activityUserProfilePhoneNumberTextView.setText(s);
        activityUserProfileNicknameTextView.setText(nickname);
        activityUserProfileServerNameTextView.setText(serverName);
        setTitle(nickname);
    }

    @OnClick(R.id.activity_user_profile_phone_number_text_view)
    public void onViewClicked() {
        CommonUtils.callPhone(getActivity(), activityUserProfilePhoneNumberTextView.getText().toString());
    }
}
