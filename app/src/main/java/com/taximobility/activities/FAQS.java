package com.taximobility.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.taximobility.R;
import com.taximobility.databinding.ActivityFAQSBinding;

public class FAQS extends AppCompatActivity {

    ActivityFAQSBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_f_a_q_s);
        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );
    }
}