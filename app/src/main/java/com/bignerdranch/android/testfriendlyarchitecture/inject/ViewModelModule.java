package com.bignerdranch.android.testfriendlyarchitecture.inject;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateFormatter;
import com.bignerdranch.android.testfriendlyarchitecture.viewmodel.CreateReminderViewModel;
import com.bignerdranch.android.testfriendlyarchitecture.viewmodel.ReminderItemViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class ViewModelModule {
    @Provides
    CreateReminderViewModel providesCreateReminderViewModel(Context context,
                                                            DateFormatter dateFormatter,
                                                            Reminder reminder) {
        return new CreateReminderViewModel(context, dateFormatter, reminder);
    }

    @Provides
    ReminderItemViewModel providesReminderItemViewModel(DateFormatter dateFormatter) {
        return new ReminderItemViewModel(dateFormatter);
    }
}
