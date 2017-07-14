package com.bignerdranch.android.testfriendlyarchitecture.inject;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.bignerdranch.android.testfriendlyarchitecture.TestReminderApplication;

public class ReminderTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl,
                                      String className,
                                      Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestReminderApplication.class.getName(), context);
    }
}
