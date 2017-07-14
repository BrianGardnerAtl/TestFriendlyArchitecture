package com.bignerdranch.android.testfriendlyarchitecture.inject;

import com.bignerdranch.android.testfriendlyarchitecture.controller.list.ReminderListFragment;
import com.bignerdranch.android.testfriendlyarchitecture.controller.list.ReminderViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ViewModelModule.class,
        StoreModule.class, AdapterModule.class, ModelModule.class})
@Singleton
public interface AppComponent {
    // Fragment injection
    void inject(ReminderListFragment fragment);

    // View holder injection
    void inject(ReminderViewHolder viewHolder);
}
