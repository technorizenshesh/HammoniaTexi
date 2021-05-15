package com.taximobility.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.taximobility.R;
import com.taximobility.databinding.ActivitySupportBinding;

public class SupportActivity extends AppCompatActivity {

    ActivitySupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_support);

        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
                );

        binding.rlContactUs.setOnClickListener(v ->
                {
                    startActivity(new Intent(this,ContactUsActivity.class));
                }
                );

        binding.rlFaqs.setOnClickListener(v ->
                {
                    startActivity(new Intent(this,FAQS.class));
                }
        );

        binding.rlSupportsTicket.setOnClickListener(v ->
                {
                    startActivity(new Intent(this,YourSupportTicketsActivity.class));
                }
        );
    }
}