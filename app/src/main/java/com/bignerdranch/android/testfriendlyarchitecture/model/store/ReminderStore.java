package com.bignerdranch.android.testfriendlyarchitecture.model.store;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;

import java.util.List;

public interface ReminderStore {
    List<Reminder> getReminders();
    void addReminder(Reminder reminder);
    void clearReminders();
}

