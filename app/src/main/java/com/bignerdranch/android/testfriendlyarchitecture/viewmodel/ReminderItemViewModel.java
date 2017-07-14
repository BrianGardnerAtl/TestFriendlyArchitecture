package com.bignerdranch.android.testfriendlyarchitecture.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateFormatter;

public class ReminderItemViewModel extends BaseObservable {

    private DateFormatter dateFormatter;
    private Reminder reminder;

    public ReminderItemViewModel(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return reminder.getTitle();
    }

    @Bindable
    public String getDate() {
        return dateFormatter.format(reminder.getDate());
    }
}