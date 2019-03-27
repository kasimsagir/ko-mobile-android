package com.kinght.commerce.ui.MainActivity.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.PartipicateActivity.PartipicateActivity;
import com.kinght.commerce.ui.ProductListActivity.ProductListActivity;
import com.kinght.commerce.ui.adapters.EntryRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseFragment;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements MainFragmentMvpView {

    @Inject
    MainFragmentMvpPresenter<MainFragmentMvpView> presenter;

    View root;
    @BindView(R.id.fragment_main_recyler_view)
    RecyclerView fragmentMainRecylerView;

    @BindView(R.id.fragment_main_server_name_button)
    Button fragmentMainServerNameButton;
    @BindView(R.id.fragment_main_title_text_view)
    ImageView fragmentMainTitleTextView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_main_add_left_button)
    Button fragmentMainAddLeftButton;

    EntryRecylerViewAdapters entryRecylerViewAdapters;
    @BindView(R.id.fragment_main_gold_image_view)
    ImageView fragmentMainGoldImageView;
    @BindView(R.id.fragment_main_partipicate_image_view)
    ImageView fragmentMainPartipicateImageView;
    @BindView(R.id.fragment_main_coin_text_view)
    TextView fragmentMainCoinTextView;
    @BindView(R.id.fragment_main_swipe_refresh_layout)
    SwipeRefreshLayout fragmentMainSwipeRefreshLayout;


    @Inject
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectMainFragment(this);


        fragmentMainServerNameButton.setTypeface(getRegularMyriadFont());
        fragmentMainAddLeftButton.setTypeface(getRegularMyriadFont());
        presenter.onAttach(this);

        presenter.getEntries();
        entryRecylerViewAdapters = new EntryRecylerViewAdapters(new EntryRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Entry item) {
                Intent intent = new Intent(getActivity(), EntryDetailActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);

               /* Intent intent = new Intent(getActivity(), ProductListActivity.class);
                intent.putExtra(Constant.BUNDLE_ENTRY_ID, item.getId());
                startActivity(intent);*/
            }
        });

        presenter.getCoinDetail();

        fragmentMainSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        return root;

    }

    @Override
    public void loadDataToList(List<Entry> response) {
        if(fragmentMainSwipeRefreshLayout.isRefreshing()){
            fragmentMainSwipeRefreshLayout.setRefreshing(false);
        }
        entryRecylerViewAdapters.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentMainRecylerView.setLayoutManager(manager);
        fragmentMainRecylerView.setAdapter(entryRecylerViewAdapters);

    }

    @Override
    public void getSelectedServerName(String name) {
        fragmentMainServerNameButton.setText(name);
    }

    @Override
    public void coinDetail(int value) {
        fragmentMainCoinTextView.setText(String.valueOf(value));
    }

    @OnClick({R.id.fragment_main_server_name_button, R.id.fragment_main_add_left_button, R.id.fragment_main_gold_image_view, R.id.fragment_main_partipicate_image_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_main_server_name_button:
                presenter.getServerList();
                break;
            case R.id.fragment_main_add_left_button:
                presenter.showFilter();
                break;
            case R.id.fragment_main_gold_image_view:
                CommonUtils.changeActivity(getActivity(), ProductListActivity.class);
                break;
            case R.id.fragment_main_partipicate_image_view:
                Intent intent = new Intent(getActivity(), PartipicateActivity.class);
                startActivity(intent);
                break;
        }
    }


}
