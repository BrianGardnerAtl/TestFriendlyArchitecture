package com.bignerdranch.android.testfriendlyarchitecture.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bignerdranch.android.testfriendlyarchitecture.R;
import com.bignerdranch.android.testfriendlyarchitecture.controller.list.ReminderListFragment;
import com.bignerdranch.android.testfriendlyarchitecture.databinding.ActivityDrawerBinding;
import com.bignerdranch.android.testfriendlyarchitecture.model.date.DateRange;

public class DrawerActivity extends AppCompatActivity {
    private ActivityDrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer);
        // setup toolbar from layout
        setSupportActionBar(binding.toolbar);
        // setup hamburger menu
        ActionBarDrawerToggle toggle = createDrawerToggle();
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // setup navigation item selection listener
        binding.navigation.setNavigationItemSelectedListener(listener);
        // set click listener on FAB
        binding.addReminder.setOnClickListener(addReminderListener);
        // setup initial screen on first launch
        if (savedInstanceState == null) {
            updateFragment(ReminderListFragment.newInstance(DateRange.ALL));
        }
    }

    private ActionBarDrawerToggle createDrawerToggle() {
        return new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
                R.string.content_description_open_drawer,
                R.string.content_description_close_drawer);
    }

    private NavigationView.OnNavigationItemSelectedListener listener = menuItem -> {
        binding.drawerLayout.closeDrawers();
        ReminderListFragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.reminders_all:
                fragment = ReminderListFragment.newInstance(DateRange.ALL);
                break;
            case R.id.reminders_today:
                fragment = ReminderListFragment.newInstance(DateRange.TODAY);
                break;
            case R.id.reminders_week:
                fragment = ReminderListFragment.newInstance(DateRange.WEEK);
                break;
            default:
                throw new IllegalArgumentException("Unknown navigation item selected!");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .commit();
        return true;
    };

    private View.OnClickListener addReminderListener = view -> {
        // start the create activity
    };

    private void updateFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .commit();
    }
}
