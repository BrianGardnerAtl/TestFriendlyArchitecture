package com.bignerdranch.android.testfriendlyarchitecture.viewmodel;

import android.content.Context;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateFormatter;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CreateReminderViewModelTest {
    private CreateReminderViewModel subject;
    private Context context;
    private DateFormatter dateFormatter;
    private Reminder reminder;

    @Before
    public void setup() {
        context = mock(Context.class);
        dateFormatter = mock(DateFormatter.class);
        reminder = mock(Reminder.class);
        subject = new CreateReminderViewModel(context, dateFormatter, reminder);
    }

    @Test
    public void itReturnsTheTitleOfTheReminder() {
        String reminderTitle = "TEST_REMINDER_TITLE";
        when(reminder.getTitle()).thenReturn(reminderTitle);

        assertThat(subject.getTitle(), is(equalTo(reminderTitle)));
    }

    @Test
    public void itSetsTheTitleOnTheReminder() {
        String uiTitle = "TEST_TITLE_FROM_THE_UI";
        subject.setTitle(uiTitle);

        verify(reminder).setTitle(uiTitle);
    }

    @Test
    public void itUsesTheDateFormatterToFormatNonNullDate() {
        when(reminder.getDate()).thenReturn(new LocalDate());
        String dateFormatterReturnValue = "MOCK_RETURN_VALUE_FROM_DATE_FORMATTER";
        when(dateFormatter.format(any(LocalDate.class))).thenReturn(dateFormatterReturnValue);

        assertThat(subject.getDate(), is(equalTo(dateFormatterReturnValue)));
    }

    @Test
    public void itReturnsAStringResourceWhenGettingANullDate() {
        when(reminder.getDate()).thenReturn(null);
        String stringResourceReturnValue = "MOCK_RETURN_VALUE_FOR_CONTEXT_STRING_RESOURCE";
        when(context.getString(R.string.reminder_set_date)).thenReturn(stringResourceReturnValue);

        assertThat(subject.getDate(), is(equalTo(stringResourceReturnValue)));
    }

    @Test
    public void itSetsTheDateOnTheReminder() {
        LocalDate uiDate = new LocalDate();
        subject.setDate(uiDate);

        verify(reminder).setDate(uiDate);
    }
}
