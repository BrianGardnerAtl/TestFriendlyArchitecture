package com.bignerdranch.android.testfriendlyarchitecture.model.store;

import android.content.ContentValues;
import android.database.Cursor;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class LiveReminderStore implements ReminderStore {

    private ReminderDatabase database;
    private List<Reminder> reminders;

    public LiveReminderStore(ReminderDatabase database) {
        this.database = database;
    }

    @Override
    public List<Reminder> getReminders() {
        if (reminders == null) {
            reminders = loadReminders();
        }
        return reminders;
    }

    @Override
    public void addReminder(Reminder reminder) {
        // add to in memory cache
        reminders.add(reminder);
        // write to database
        ContentValues values = getContentValues(reminder);
        database.getWritableDatabase()
                .insert(ReminderContract.TABLE_NAME, null, values);
    }

    @Override
    public void clearReminders() {
        reminders = new ArrayList<>();
        database.getWritableDatabase().execSQL("DELETE FROM " + ReminderContract.TABLE_NAME);
    }

    private List<Reminder> loadReminders() {
        Cursor cursor = database.getReadableDatabase()
                .query(ReminderContract.TABLE_NAME, null, null, null, null, null, null);
        ReminderCursorWrapper wrapper = new ReminderCursorWrapper(cursor);
        List<Reminder> reminders = new ArrayList<>();
        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()) {
                reminders.add(wrapper.getReminder());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
            cursor.close();
        }
        return reminders;
    }

    private ContentValues getContentValues(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(ReminderContract.Cols.TITLE, reminder.getTitle());
        Long date;
        if (reminder.getDate() != null) {
            date = reminder.getDate().toDate().getTime();
        } else {
            date = null;
        }
        values.put(ReminderContract.Cols.DATE, date);
        return values;
    }
}

