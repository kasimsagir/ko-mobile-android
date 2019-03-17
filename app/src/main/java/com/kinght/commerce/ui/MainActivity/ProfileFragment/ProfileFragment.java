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
import com.kinght.commerce.ui.adapters.EntryRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseFragment;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


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


    public ProfileFragment() {
        // Required empty public constructor
    }

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectProfileFragment(this);
        ButterKnife.bind(this,root);
        entryRecylerViewAdapters = new EntryRecylerViewAdapters(new EntryRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Entry item) {
                Intent intent = new Intent(getActivity(), EntryDetailActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);
            }
        });

        presenter.onAttach(this);
        presenter.getMe();



        return root;
    }


    @Override
    public void loadDataToList(List<Entry> entryList) {
        entryRecylerViewAdapters.setData(entryList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentProfileRecylerview.setLayoutManager(manager);
        fragmentProfileRecylerview.setAdapter(entryRecylerViewAdapters);
    }
}
