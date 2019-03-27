package com.kinght.commerce.ui.MainActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragment;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.MainActivity.NotificationFragment.NotificationFragment;
import com.kinght.commerce.ui.MainActivity.ProfileFragment.ProfileFragment;
import com.kinght.commerce.ui.MainActivity.SearchFragment.SearchFragment;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> presenter;

    @Inject
    MainFragment mainFragment;

    @Inject
    SearchFragment searchFragment;

    @Inject
    CreateEntryFragment createEntryFragment;

    @Inject
    NotificationFragment notificationFragment;

    @Inject
    ProfileFragment profileFragment;


    Fragment active = mainFragment;

    final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MvpApp) getApplication()).getActivityComponent().injectMainActivity(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        presenter.onAttach(this);
        active=mainFragment;
        presenter.mainFragment();

        fm.beginTransaction().add(R.id.fragment, searchFragment, "2").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.fragment, createEntryFragment, "3").hide(createEntryFragment).commit();
        fm.beginTransaction().add(R.id.fragment, notificationFragment, "4").hide(notificationFragment).commit();
        fm.beginTransaction().add(R.id.fragment, profileFragment, "5").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.fragment, mainFragment, "1").commit();


      //  fm.beginTransaction().add(R.id.fragment, mainFragment, "1").commit();

       /* FirebaseInstanceId.getInstance().getInstanceId()
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
*/





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
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void openMainFragment() {
        fm.beginTransaction().hide(active).show(mainFragment).commit();
        active = mainFragment;
        CommonUtils.hideKeyboard(MainActivity.this);
    }

    @Override
    public void openChooseFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new ChooseFragment());

    }

    @Override
    public void openAccountFragment() {
        fm.beginTransaction().hide(active).show(profileFragment).commit();
        active = profileFragment;

    }

    @Override
    public void openSearchFragment() {
        fm.beginTransaction().hide(active).show(searchFragment).commit();
        active = searchFragment;
    }

    @Override
    public void openNotificationFragment() {
        fm.beginTransaction().hide(active).show(notificationFragment).commit();
        active = notificationFragment;
    }

    @Override
    public void createEntryFragment() {
        fm.beginTransaction().hide(active).show(createEntryFragment).commit();
        active = createEntryFragment;
    }

    @Override
    public void openLoginActivity() {
        CommonUtils.changeActivity(MainActivity.this, LoginActivity.class);

    }


}
