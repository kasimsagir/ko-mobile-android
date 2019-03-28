package com.kinght.commerce.ui.MainActivity.ProfileFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.SettingsActivity.SettingsActivity;
import com.kinght.commerce.ui.adapters.EntryRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseFragment;
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


public class ProfileFragment extends BaseFragment implements ProfileFragmentMvpView {

    @Inject
    ProfileFragmentMvpPresenter<ProfileFragmentMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_profile_user)
    ImageView fragmentProfileUser;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.fragment_profile_recylerview)
    RecyclerView fragmentProfileRecylerview;

    EntryRecylerViewAdapters entryRecylerViewAdapters;
    @BindView(R.id.fragment_profile_phone_number_text_view)
    TextView fragmentProfilePhoneNumberTextView;
    @BindView(R.id.fragment_profile_nickname_text_view)
    TextView fragmentProfileNicknameTextView;
    @BindView(R.id.fragment_profile_server_name_text_view)
    TextView fragmentProfileServerNameTextView;


    public ProfileFragment() {
        // Required empty public constructor
    }

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, root);
        entryRecylerViewAdapters = new EntryRecylerViewAdapters(new EntryRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Entry item) {
                Intent intent = new Intent(getActivity(), EntryDetailActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);
            }
        });

        if (presenter == null) {
            ((MvpApp) getActivity().getApplication()).getActivityComponent().injectProfileFragment(this);
            presenter.onAttach(this);
        }

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);


        return root;
    }


    @Override
    public void loadDataToList(List<Entry> entryList) {
        entryRecylerViewAdapters.setData(entryList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentProfileRecylerview.setLayoutManager(manager);
        fragmentProfileRecylerview.setAdapter(entryRecylerViewAdapters);
        fragmentProfileRecylerview.setHasFixedSize(true);
        fragmentProfileRecylerview.setItemViewCacheSize(20);
        fragmentProfileRecylerview.setDrawingCacheEnabled(true);
        fragmentProfileRecylerview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @Override
    public void loadUserDataToView(String phoneNumber, String nickname, String serverName) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Profilm");
        fragmentProfilePhoneNumberTextView.setText(phoneNumber);
        fragmentProfileNicknameTextView.setText(nickname);
        fragmentProfileServerNameTextView.setText(serverName);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
        } else {
            ((MvpApp) getActivity().getApplication()).getActivityComponent().injectProfileFragment(this);
            presenter.onAttach(this);
            presenter.getMe();


        }
    }


    @OnClick({R.id.fragment_profile_setting_image_view, R.id.fragment_profile_phone_number_text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_profile_setting_image_view:
                CommonUtils.changeActivity(getActivity(), SettingsActivity.class);
                break;
            case R.id.fragment_profile_phone_number_text_view:
                CommonUtils.callPhone(getActivity(), fragmentProfilePhoneNumberTextView.getText().toString());
                break;
        }
    }
}
