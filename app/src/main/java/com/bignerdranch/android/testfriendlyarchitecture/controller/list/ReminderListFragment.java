package com.bignerdranch.android.testfriendlyarchitecture.controller.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.controller.ReminderApplication;
import com.bignerdranch.android.testfriendlyarchitecture.databinding.FragmentReminderListBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateRange;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateUtils;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ReminderListFragment extends Fragment {
    private static final String TAG = "ReminderListFragment";
    private static final String ARG_DATE_RANGE = "arg_date_range";

    private FragmentReminderListBinding binding;
    private DateRange range;
    @Inject
    ReminderAdapter adapter;
    @Inject
    ReminderStore reminderStore;
    @Inject
    DateUtils dateUtils;

    public static ReminderListFragment newInstance(DateRange range) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE_RANGE, range);
        ReminderListFragment fragment = new ReminderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReminderApplication.get(getContext()).getAppComponent().inject(this);
        range = (DateRange) getArguments().getSerializable(ARG_DATE_RANGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reminder_list, container, false);
        binding.reminderList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.reminderList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }

    private void setupAdapter() {
        Observable.fromIterable(reminderStore.getReminders())
                .filter(this::isReminderInRange)
                .toList()
                .subscribe(new SingleObserver<List<Reminder>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Reminder> reminders) {
                        adapter.setReminders(reminders);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: could not setup adapter", e);
                    }
                });
    }

    private boolean isReminderInRange(Reminder reminder) {
        switch (range) {
            case ALL:
                return true;
            case TODAY:
                return dateUtils.isToday(reminder.getDate());
            case WEEK:
                return dateUtils.isThisWeek(reminder.getDate());
            default:
                throw new IllegalArgumentException("Unknown date range specified");
        }
    }
}
