package com.bignerdranch.android.testfriendlyarchitecture.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.bignerdranch.android.testfriendlyarchitecture.BR;
import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateFormatter;

import org.joda.time.LocalDate;

public class CreateReminderViewModel extends BaseObservable {
    private Context context;
    private DateFormatter dateFormatter;
    private Reminder reminder;

    public CreateReminderViewModel(Context context,
                                   DateFormatter dateFormatter,
                                   Reminder reminder) {
        this.context = context;
        this.dateFormatter = dateFormatter;
        this.reminder = reminder;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public String getTitle() {
        return reminder.getTitle();
    }

    public void setTitle(String title) {
        reminder.setTitle(title);
    }

    @Bindable
    public String getDate() {
        LocalDate date = reminder.getDate();
        if (date != null) {
            return dateFormatter.format(date);
        }
        return context.getString(R.string.reminder_set_date);
    }

    public void setDate(LocalDate date) {
        reminder.setDate(date);
        notifyPropertyChanged(BR.date);
    }
}

