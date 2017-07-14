package com.bignerdranch.android.testfriendlyarchitecture.inject;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ViewModelModule.class,
        FakeStoreModule.class, AdapterModule.class, ModelModule.class})
@Singleton
interface TestComponent extends AppComponent {
}