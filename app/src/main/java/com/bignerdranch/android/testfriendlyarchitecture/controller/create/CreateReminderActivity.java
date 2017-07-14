package com.bignerdranch.android.testfriendlyarchitecture.controller.create;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.controller.ReminderApplication;
import com.bignerdranch.android.testfriendlyarchitecture.databinding.ActivityCreateReminderBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateSelectionListener;
import com.bignerdranch.android.testfriendlyarchitecture.model.store.ReminderStore;
import com.bignerdranch.android.testfriendlyarchitecture.viewmodel.CreateReminderViewModel;

import org.joda.time.LocalDate;

import javax.inject.Inject;

public class CreateReminderActivity extends AppCompatActivity
        implements DateSelectionListener {
    private static final String TAG_DATE_FRAGMENT = "tag_date_fragment";

    private ActivityCreateReminderBinding binding;
    @Inject
    protected CreateReminderViewModel viewModel;
    @Inject
    protected ReminderStore reminderStore;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateReminderActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inject dependencies
        ReminderApplication.get(this).getAppComponent().inject(this);
        // Setup view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_reminder);
        binding.setReminder(viewModel);
        binding.setDate.setOnClickListener(setDateListener);
        binding.saveReminder.setOnClickListener(saveReminderListener);
    }

    private View.OnClickListener setDateListener = view -> {
        DateDialogFragment fragment = DateDialogFragment.newInstance(viewModel.getReminder().getDate());
        getSupportFragmentManager().beginTransaction()
                .add(fragment, TAG_DATE_FRAGMENT)
                .commit();
    };

    private View.OnClickListener saveReminderListener = view -> {
        reminderStore.addReminder(viewModel.getReminder());
        finish();
    };

    @Override
    public void onDateSelected(LocalDate date) {
        viewModel.setDate(date);
    }
}
