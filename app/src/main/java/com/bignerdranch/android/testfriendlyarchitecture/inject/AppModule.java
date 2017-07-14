package com.bignerdranch.android.testfriendlyarchitecture.inject;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateFormatter;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {

    private Context appContext;

    public AppModule(Context context) {
        appContext = context.getApplicationContext();
    }

    @Provides
    Context providesAppContext() {
        return appContext;
    }

    @Provides
    DateFormatter providesDateFormatter() {
        return new DateFormatter();
    }

    @Provides
    DateUtils providesDateUtils() {
        return new DateUtils();
    }
}