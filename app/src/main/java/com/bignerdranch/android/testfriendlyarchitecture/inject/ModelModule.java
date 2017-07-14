package com.bignerdranch.android.testfriendlyarchitecture.inject;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class ModelModule {

    @Provides
    Reminder providesReminder() {
        return new Reminder();
    }
}
