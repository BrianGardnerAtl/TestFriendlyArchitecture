package com.bignerdranch.android.testfriendlyarchitecture.controller;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddReminderTest {
    @Rule
    public ActivityTestRule<DrawerActivity> mActivityTestRule =
            new ActivityTestRule<>(DrawerActivity.class);
    
}
