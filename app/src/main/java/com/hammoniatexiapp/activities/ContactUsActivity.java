package com.hammoniatexiapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {

    ActivityContactUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );
    }
}