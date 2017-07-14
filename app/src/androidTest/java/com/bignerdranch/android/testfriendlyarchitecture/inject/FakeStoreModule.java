package com.bignerdranch.android.testfriendlyarchitecture.inject;

import com.bignerdranch.android.testfriendlyarchitecture.fakes.FakeReminderStore;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class FakeStoreModule {

    @Provides
    @Singleton
    ReminderStore providesReminderStore() {
        return new FakeReminderStore();
    }
}
