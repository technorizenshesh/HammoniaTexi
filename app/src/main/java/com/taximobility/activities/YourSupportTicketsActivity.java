package com.taximobility.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.taximobility.R;
import com.taximobility.databinding.ActivityYourSupportTicketsBinding;

public class YourSupportTicketsActivity extends AppCompatActivity {

    ActivityYourSupportTicketsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_your_support_tickets);

        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );

    }
}