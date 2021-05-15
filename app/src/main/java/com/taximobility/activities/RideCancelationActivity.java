package com.taximobility.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.taximobility.R;
import com.taximobility.databinding.ActivityRideCancelationBinding;

public class RideCancelationActivity extends AppCompatActivity {

    ActivityRideCancelationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_cancelation);
        binding.btnSubmit.setOnClickListener(v ->

                {
                    finish();

                }
                );

        binding.ivBack.setOnClickListener(v ->

                {
                    finish();
                }
        );



    }
}