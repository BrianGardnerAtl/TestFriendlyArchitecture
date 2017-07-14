package com.bignerdranch.android.testfriendlyarchitecture.model.date;

import org.joda.time.LocalDate;

public class DateUtils {

    public boolean isToday(LocalDate date) {
        LocalDate today = new LocalDate();
        return date.equals(today);
    }

    public boolean isThisWeek(LocalDate date) {
        LocalDate today = new LocalDate();
        LocalDate nextWeek = today.plusDays(7);
        return !date.isBefore(today) && !date.isAfter(nextWeek);
    }
}