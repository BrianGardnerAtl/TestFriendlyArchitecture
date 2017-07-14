package com.bignerdranch.android.testfriendlyarchitecture.controller;

import android.app.Application;
import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.inject.AdapterModule;
import com.bignerdranch.android.testfriendlyarchitecture.inject.AppComponent;
import com.bignerdranch.android.testfriendlyarchitecture.inject.AppModule;
import com.bignerdranch.android.testfriendlyarchitecture.inject.DaggerAppComponent;
import com.bignerdranch.android.testfriendlyarchitecture.inject.StoreModule;
import com.bignerdranch.android.testfriendlyarchitecture.inject.ViewModelModule;

public class ReminderApplication extends Application {

    protected AppComponent appComponent;

    public static ReminderApplication get(Context context) {
        return (ReminderApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjection();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected void initializeDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .viewModelModule(new ViewModelModule())
                .storeModule(new StoreModule())
                .adapterModule(new AdapterModule())
                .build();
    }
}