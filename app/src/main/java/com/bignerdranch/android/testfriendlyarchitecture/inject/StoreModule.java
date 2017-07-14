package com.bignerdranch.android.testfriendlyarchitecture.inject;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.model.store.LiveReminderStore;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderDatabase;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class StoreModule {
    private static final String DATABASE_FILE_NAME = "reminders.db";
    private static final int DATABASE_VERSION_NUMBER = 1;

    @Provides
    @Singleton
    ReminderStore providesReminderStore(ReminderDatabase database) {
        return new LiveReminderStore(database);
    }

    /**
     * The provides methods below are internal to this module.
     * They are only used to supply the ReminderDatabase parameter to the ReminderStore
     */
    @Provides
    ReminderDatabase providesReminderDatabase(Context context,
                                              @Named("database_file") String filename,
                                              @Named("database_version") int version) {
        return new ReminderDatabase(context, filename, null, version);
    }

    @Provides
    @Named("database_file")
    String providesDatabaseFileName() {
        return DATABASE_FILE_NAME;
    }

    @Provides
    @Named("database_version")
    int providesDatabaseVersion() {
        return DATABASE_VERSION_NUMBER;
    }
}

