package com.bignerdranch.android.testfriendlyarchitecture.inject;

import com.bignerdranch.android.testfriendlyarchitecture.controller.AddReminderTest;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ViewModelModule.class,
        FakeStoreModule.class, AdapterModule.class, ModelModule.class})
@Singleton
interface TestComponent extends AppComponent {
    void inject(AddReminderTest test);
}