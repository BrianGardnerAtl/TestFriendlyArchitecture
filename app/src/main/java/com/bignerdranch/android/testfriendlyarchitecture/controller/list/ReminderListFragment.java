package com.bignerdranch.android.testfriendlyarchitecture.controller.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateRange;

public class ReminderListFragment extends Fragment{
    private static final String TAG = "ReminderListFragment";
    private static final String ARG_DATE_RANGE = "arg_date_range";

    public static ReminderListFragment newInstance(DateRange range) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE_RANGE, range);
        ReminderListFragment fragment = new ReminderListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
