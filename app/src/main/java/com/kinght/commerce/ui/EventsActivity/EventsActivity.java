package com.kinght.commerce.ui.EventsActivity;

import android.os.Bundle;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.EventHours;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.ui.adapters.EventsRecylerViewAdapter;
import com.kinght.commerce.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends BaseActivity implements EventsActivityMvpView {

    @Inject
    EventsActivityMvpPresenter<EventsActivityMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_events_recyler_view)
    RecyclerView activityEventsRecylerView;

    EventsRecylerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        ((MvpApp) getApplication()).getActivityComponent().injectEventsActivity(this);

        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter = new EventsRecylerViewAdapter(EventsActivity.this, new EventsRecylerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(EventHours item, int position) {
                presenter.selectItem(item, position);


            }
        });

        presenter.getEvents();


    }


    @Override
    public void loadDataToList(List<Events> response) {
        adapter.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activityEventsRecylerView.setLayoutManager(manager);
        activityEventsRecylerView.setAdapter(adapter);

    }


    @OnClick(R.id.activity_events_save_button)
    public void onViewClicked() {
        presenter.save();
    }
}
