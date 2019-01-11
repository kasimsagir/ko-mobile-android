package com.kinght.commerce;

import android.app.Application;

import com.kinght.commerce.di.components.DaggerViewComponents;
import com.kinght.commerce.di.components.ViewComponents;
import com.kinght.commerce.di.modules.PresenterModules;


public class MvpApp extends Application {

    private ViewComponents viewComponents;

    public ViewComponents getActivityComponent() {
        return viewComponents;
    }

    public void setActivityComponent(ViewComponents activityComponent) {
        this.viewComponents = activityComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        viewComponents= DaggerViewComponents.builder().presenterModules(new PresenterModules(this)).build();

    }
}
