package com.bignerdranch.android.testfriendlyarchitecture.model.date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatter {
    private static final String FORMAT_STRING = "MM/dd/yyyy";
    private static final String NULL_RETURN = "";

    public String format(LocalDate date) {
        if (date == null) {
            return NULL_RETURN;
        }
        DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_STRING);
        return formatter.print(date);
    }
}