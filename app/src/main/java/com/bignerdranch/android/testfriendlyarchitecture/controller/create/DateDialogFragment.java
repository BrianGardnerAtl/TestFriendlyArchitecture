package com.bignerdranch.android.testfriendlyarchitecture.controller.create;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.databinding.FragmentDateDialogBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateSelectionListener;

import org.joda.time.LocalDate;

public class DateDialogFragment extends DialogFragment {
    private static final String ARG_DATE = "arg_date";

    private FragmentDateDialogBinding binding;
    private DateSelectionListener listener;
    private LocalDate currentDate;


    public static DateDialogFragment newInstance(LocalDate date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DateDialogFragment fragment = new DateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDate = (LocalDate) getArguments().getSerializable(ARG_DATE);
        if (currentDate == null) {
            currentDate = new LocalDate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date_dialog, container, false);
        binding.confirm.setOnClickListener(confirmListener);
        binding.datePicker.init(
                currentDate.getYear(),
                currentDate.getMonthOfYear() - 1, // date picker expects 0-11 month, jodatime supplies 1-12
                currentDate.getDayOfMonth(),
                dateChangedListener);

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DateSelectionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private View.OnClickListener confirmListener = view -> {
        listener.onDateSelected(currentDate);
        dismiss();
    };

    private DatePicker.OnDateChangedListener dateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int jodaMonth = monthOfYear + 1; // date picker expects 0-11 month, jodatime supplies 1-12
            currentDate = new LocalDate(year, jodaMonth, dayOfMonth);
        }
    };
}
