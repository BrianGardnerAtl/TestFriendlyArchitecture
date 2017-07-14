package com.bignerdranch.android.testfriendlyarchitecture.model.date;

import org.joda.time.LocalDate;

public interface DateSelectionListener {
    void onDateSelected(LocalDate date);
}

