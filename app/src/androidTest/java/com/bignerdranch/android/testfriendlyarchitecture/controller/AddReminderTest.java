package com.bignerdranch.android.testfriendlyarchitecture.controller;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.TestReminderApplication;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void addReminderTest() {
        // Click on FAB to add new reminder
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_reminder), isDisplayed()));
        floatingActionButton.perform(click());

        // Input the new reminder title
        ViewInteraction reminderTitleInput = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.support.design.widget.TextInputLayout")),
                                0),
                        0),
                        isDisplayed()));
        reminderTitleInput.perform(replaceText("test reminder"), closeSoftKeyboard());

        // Click on date set button to open date picker dialog
        ViewInteraction dateSelectionButton = onView(
                allOf(withId(R.id.set_date), isDisplayed()));
        dateSelectionButton.perform(click());

        // Set the date in date picker using PickerActions from espresso contrib library
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2017, 7, 13)); // July 13th 2017

        // click the ok button to dismiss the dialog
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.confirm), isDisplayed()));
        appCompatButton2.perform(click());

        // click save reminder button to go back to list screen
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_reminder), withText("Save Reminder"), isDisplayed()));
        appCompatButton3.perform(click());

        // verify that the reminder name is correct
        ViewInteraction textView = onView(
                allOf(withText("test reminder"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminder_list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("test reminder")));

        // verify that the reminder date is correct
        ViewInteraction textView2 = onView(
                allOf(withText("07/13/2017"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminder_list),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("07/13/2017")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
