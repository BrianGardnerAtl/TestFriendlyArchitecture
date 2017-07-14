package com.bignerdranch.android.testfriendlyarchitecture.fakes;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import java.util.ArrayList;
import java.util.List;

public class FakeReminderStore implements ReminderStore {

    private List<Reminder> reminders;

    public FakeReminderStore() {
        reminders = new ArrayList<>();
    }

    @Override
    public List<Reminder> getReminders() {
        return reminders;
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    @Override
    public void clearReminders() {
        reminders = new ArrayList<>();
    }
}
