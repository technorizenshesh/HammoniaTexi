package com.hammoniatexiapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityRideCancelationBinding;

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