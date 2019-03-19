package com.kinght.commerce.ui.MainActivity.NotificationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Notification.Notifications;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.adapters.NotificiationRecylerviewAdapter;
import com.kinght.commerce.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationFragment extends BaseFragment implements NotificationFragmentMvpView {


    @Inject
    NotificationFragmentMvpPresenter<NotificationFragmentMvpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_notification_recyler_view)
    RecyclerView fragmentNotificationRecylerView;


    NotificiationRecylerviewAdapter adapter;
    public NotificationFragment() {
        // Required empty public constructor
    }

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,root);
        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectNotificationFragment(this);
        presenter.onAttach(this);

        presenter.getNotifications();
        adapter=new NotificiationRecylerviewAdapter(new NotificiationRecylerviewAdapter.ItemListener() {
            @Override
            public void onItemClick(Notifications item) {

            }
        });
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bildirimler");

        return root;
    }


    @Override
    public void loadDataToList(List<Notifications> response) {
        adapter.setData(response);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentNotificationRecylerView.setLayoutManager(manager);
        fragmentNotificationRecylerView.setAdapter(adapter);
    }
}
