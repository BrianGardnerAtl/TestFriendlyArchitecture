package com.bignerdranch.android.testfriendlyarchitecture.model.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDatabase extends SQLiteOpenHelper {

    public ReminderDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ReminderContract.TABLE_NAME + "(" +
                ReminderContract.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReminderContract.Cols.TITLE + " TEXT," +
                ReminderContract.Cols.DATE + " INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
