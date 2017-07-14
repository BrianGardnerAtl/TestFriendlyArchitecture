package com.bignerdranch.android.testfriendlyarchitecture.inject;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.controller.list.ReminderAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AdapterModule {

    @Provides
    ReminderAdapter providesReminderAdapter(Context context) {
        return new ReminderAdapter(context);
    }
}