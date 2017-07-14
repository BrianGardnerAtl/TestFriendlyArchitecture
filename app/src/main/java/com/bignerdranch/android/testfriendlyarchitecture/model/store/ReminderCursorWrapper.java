package com.bignerdranch.android.testfriendlyarchitecture.model.store;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;

import org.joda.time.LocalDate;

public class ReminderCursorWrapper extends CursorWrapper {
    public ReminderCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Reminder getReminder() {
        int id = getInt(getColumnIndex(ReminderContract.Cols.ID));
        String title = getString(getColumnIndex(ReminderContract.Cols.TITLE));
        long date = getLong(getColumnIndex(ReminderContract.Cols.DATE));

        return new Reminder(id, title, new LocalDate(date));
    }
}