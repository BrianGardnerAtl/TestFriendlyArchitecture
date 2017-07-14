package com.bignerdranch.android.testfriendlyarchitecture;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.controller.ReminderApplication;
import com.bignerdranch.android.testfriendlyarchitecture.inject.AppModule;
import com.bignerdranch.android.testfriendlyarchitecture.inject.DaggerTestComponent;


public class TestReminderApplication extends ReminderApplication {

    public static TestReminderApplication get(Context context) {
        return (TestReminderApplication) context.getApplicationContext();
    }

    @Override
    protected void initializeDependencyInjection() {
        appComponent = DaggerTestComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public DaggerTestComponent getAppComponent() {
        return (DaggerTestComponent) appComponent;
    }


}