package com.bignerdranch.android.testfriendlyarchitecture.controller;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bignerdranch.android.testfriendlyarchitecture.TestReminderApplication;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddReminderTest {
    @Rule
    public ActivityTestRule<DrawerActivity> mActivityTestRule =
            new ActivityTestRule<>(DrawerActivity.class);
    @Inject
    ReminderStore reminderStore;

    @Before
    public void setup() {
        TestReminderApplication.get(mActivityTestRule.getActivity())
                .getAppComponent().inject(this);
    }

    @After
    public void tearDown() {
        reminderStore.clearReminders();
    }
}
