package com.kinght.commerce.ui.MainActivity.SearchFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.adapters.EntryRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseFragment;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchFragment extends BaseFragment implements SearchFragmentMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.fragment_search_server_name_button)
    MaterialButton fragmentSearchServerNameButton;
    @BindView(R.id.fragment_search_entry_recyler_View)
    RecyclerView fragmentSearchEntryRecylerView;


    EntryRecylerViewAdapters entryRecylerViewAdapters;
    @BindView(R.id.logo_toolbar_coin_text_view)
    TextView logoToolbarCoinTextView;


    public SearchFragment() {
        // Required empty public constructor
    }

    private View root;

    @Inject
    SearchFragmentMvpPresenter<SearchFragmentMvpView> presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);


        if(presenter == null){
            ((MvpApp) getActivity().getApplication()).getActivityComponent().injectSearchFragment(this);
            presenter.onAttach(this);

        }

        presenter.getAllEntries();



        ButterKnife.bind(this, root);

        entryRecylerViewAdapters = new EntryRecylerViewAdapters(new EntryRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Entry item) {
                Intent intent = new Intent(getActivity(), EntryDetailActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String input = newText.toLowerCase();
                presenter.getFilterEntries(input);


                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                presenter.getFilterEntries("");
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewExpanded();
            }
        });
        return root;
    }

    @Override
    public void loadDataToList(List<Entry> response) {
        entryRecylerViewAdapters.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentSearchEntryRecylerView.setLayoutManager(manager);
        fragmentSearchEntryRecylerView.setAdapter(entryRecylerViewAdapters);
        fragmentSearchEntryRecylerView.setHasFixedSize(true);
        fragmentSearchEntryRecylerView.setItemViewCacheSize(20);
        fragmentSearchEntryRecylerView.setDrawingCacheEnabled(true);
        fragmentSearchEntryRecylerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
        } else {
            if(presenter== null){
                ((MvpApp) getActivity().getApplication()).getActivityComponent().injectSearchFragment(this);
                presenter.onAttach(this);
            }
            presenter.getMe();


        }
    }

    @Override
    public void loadCoinData(int value) {
        logoToolbarCoinTextView.setText(String.valueOf(value));
    }

    @OnClick(R.id.fragment_search_server_name_button)
    public void onViewClicked() {
        presenter.getServers();
    }

    @Override
    public void showServerNameToUser(String name) {
        fragmentSearchServerNameButton.setText(name);
    }
}
