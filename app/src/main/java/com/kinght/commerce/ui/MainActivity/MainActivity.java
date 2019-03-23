package com.kinght.commerce.ui.MainActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.firebase.NotificationReceiver;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragment;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragment;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragment;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragment;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import java.util.Calendar;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MvpApp) getApplication()).getActivityComponent().injectMainActivity(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        presenter.onAttach(this);
        presenter.mainFragment();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        Log.d("veri","veri");
                    }
                });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    presenter.navigateFragment("MAIN");
                    return true;
                case R.id.navigation_search:
                    presenter.navigateFragment("SEARCH");
                    return true;
                case R.id.navigation_add:
                    presenter.navigateFragment("ADD");
                    return true;
                case R.id.navigation_notificaion:
                    presenter.navigateFragment("NOTIFICATION");
                    return true;
                case R.id.navigation_account:
                    presenter.navigateFragment("ACCOUNT");
                    return true;
            }
            return false;
        }
    };

    @Override
    public void openMainFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new MainFragment());
    }

    @Override
    public void openChooseFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new ChooseFragment());

    }

    @Override
    public void openAccountFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new ProfileFragment());

    }

    @Override
    public void openSearchFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new SearchFragment());

    }

    @Override
    public void openNotificationFragment() {
        CommonUtils.switchToFragment(MainActivity.this,new NotificationFragment());
    }

    @Override
    public void createEntryFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new CreateEntryFragment());

    }

    @Override
    public void openLoginActivity() {
        CommonUtils.changeActivity(MainActivity.this, LoginActivity.class);

    }


}
