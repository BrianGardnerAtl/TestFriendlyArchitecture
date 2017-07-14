package com.bignerdranch.android.testfriendlyarchitecture.controller.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.android.testfriendlyarchitecture.databinding.ItemReminderBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;
import com.bignerdranch.android.testfriendlyarchitecture.viewmodel.ReminderItemViewModel;

public class ReminderViewHolder extends RecyclerView.ViewHolder {

    private ItemReminderBinding binding;
    ReminderItemViewModel viewModel;

    public ReminderViewHolder(ItemReminderBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindReminder(Reminder reminder) {
        viewModel.setReminder(reminder);
    }
}
