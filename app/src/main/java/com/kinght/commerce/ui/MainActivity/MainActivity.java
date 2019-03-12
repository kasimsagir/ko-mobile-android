package com.kinght.commerce.ui.MainActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.MainActivity.ChooseFragment.ChooseFragment;
import com.kinght.commerce.ui.MainActivity.CreateEntryFragment.CreateEntryFragment;
import com.kinght.commerce.ui.MainActivity.MainFragment.MainFragment;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.CommonUtils;
import android.view.MenuItem;
import javax.inject.Inject;

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
        CommonUtils.switchToFragment(MainActivity.this, new MainFragment());

    }

    @Override
    public void openSearchFragment() {

    }

    @Override
    public void openNotificationFragment() {

    }

    @Override
    public void createEntryFragment() {
        CommonUtils.switchToFragment(MainActivity.this, new CreateEntryFragment());

    }


}
