package com.bignerdranch.android.testfriendlyarchitecture.controller.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.databinding.ItemReminderBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

    private Context context;
    private List<Reminder> reminders;

    public ReminderAdapter(Context context) {
        this.context = context;
        this.reminders = new ArrayList<>();
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemReminderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(binding, context);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        holder.bindReminder(reminders.get(position));
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }
}
