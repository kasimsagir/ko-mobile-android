package com.kinght.commerce.ui.PartipicateActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.User;
import com.kinght.commerce.ui.EntryDetailActivity.EntryDetailActivity;
import com.kinght.commerce.ui.ReportActivity.ReportActivity;
import com.kinght.commerce.ui.UserProfileActivity.UserProfileActivity;
import com.kinght.commerce.ui.adapters.LotteryRecylerViewAdapter;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.Constant;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartipicateActivity extends BaseActivity implements PartipicateActivityMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_partipicate_recylerview)
    RecyclerView activityPartipicateRecylerview;
    @BindView(R.id.activity_partipicate_button)
    Button activityPartipicateButton;

    LotteryRecylerViewAdapter adapter;

    @Inject
    PartipicateActivityMvpPresenter<PartipicateActivityMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partipicate);
        ButterKnife.bind(this);
        ((MvpApp) getApplication()).getActivityComponent().injectPartipicateActivity(this);
        presenter.onAttach(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter.getPartipicate();
        adapter=new LotteryRecylerViewAdapter(new LotteryRecylerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(User item) {
                Intent intent=new Intent(PartipicateActivity.this, UserProfileActivity.class);
                intent.putExtra(Constant.BUNDLE_USER_ID,item.get_id());
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.activity_partipicate_button)
    public void onViewClicked() {
        presenter.partipicateLottery();
    }

    @Override
    public void loadDataOfLottery(List<User> participants) {
        adapter.setData(participants);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activityPartipicateRecylerview.setLayoutManager(manager);
        activityPartipicateRecylerview.setAdapter(adapter);
    }
}
