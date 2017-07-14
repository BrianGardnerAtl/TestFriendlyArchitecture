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
public class DateFormatterTest {

    private DateFormatter dateFormatter;

    @Before
    public void setup() {
        dateFormatter = new DateFormatter();
    }

    @Test
    public void itCorrectlyFormatsADate() {
        LocalDate date = new LocalDate(2017, 7, 13);
        String expectedDateFormat = "07/13/2017";
        assertThat(dateFormatter.format(date), is(equalTo(expectedDateFormat)));
    }

    @Test
    public void itReturnsAnEmptyStringOnNullParameter() {
        String expectedDateFormat = "";
        assertThat(dateFormatter.format(null), is(equalTo(expectedDateFormat)));
    }
}