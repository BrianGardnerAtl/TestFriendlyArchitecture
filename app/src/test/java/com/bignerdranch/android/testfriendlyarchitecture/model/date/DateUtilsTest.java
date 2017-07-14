package com.bignerdranch.android.testfriendlyarchitecture.model.date;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class DateUtilsTest {

    private DateUtils subject;

    @Before
    public void setup() {
        subject = new DateUtils();
    }

    // Test isToday method
    @Test
    public void itReturnTrueForTodaysDate() {
        LocalDate today = new LocalDate();
        assertThat(subject.isToday(today), is(equalTo(true)));
    }

    @Test
    public void itReturnsFalseForDatesNotToday() {
        LocalDate today = new LocalDate();
        LocalDate yesterday = today.minusDays(1);
        assertThat(subject.isToday(yesterday), is(equalTo(false)));

        LocalDate tomorrow = today.plusDays(1);
        assertThat(subject.isToday(tomorrow), is(equalTo(false)));
    }

    // Test isWeek method
    @Test
    public void itReturnsTrueForDatesUpToSevenDaysFromNow() {
        LocalDate today = new LocalDate();
        LocalDate testDate;
        int weekDays = 7;
        for (int i = 0; i<=weekDays; i++) {
            testDate = today.plusDays(i);
            assertThat(subject.isThisWeek(testDate), is(equalTo(true)));
        }
    }

    @Test
    public void itReturnsFalseForYesterday() {
        LocalDate yesterday = new LocalDate().minusDays(1);
        assertThat(subject.isThisWeek(yesterday), is(equalTo(false)));
    }

    @Test
    public void itReturnsFalseForDatesMoreThanSevenDaysFromNow() {
        int weekPlusOne = 8;
        LocalDate date = new LocalDate().plusDays(weekPlusOne);
        assertThat(subject.isThisWeek(date), is(equalTo(false)));
    }
}

