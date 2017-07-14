package com.bignerdranch.android.testfriendlyarchitecture.model;

import org.joda.time.LocalDate;

public class Reminder {

    private int id;
    private String title;
    private LocalDate date;

    public Reminder() {
        this(null, new LocalDate());
    }

    public Reminder(String title, LocalDate date) {
        this(0, title, date);
    }

    public Reminder(int id, String title, LocalDate date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}