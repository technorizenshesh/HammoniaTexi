package com.hammoniatexiapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityMainStartBinding;

public class MainStartActivity extends AppCompatActivity {

    ActivityMainStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_start);

        binding.btnAlearyhave.setOnClickListener(v -> {
                    startActivity(new Intent(MainStartActivity.this, LoginActivity.class));

                }
                );

        binding.btnStart.setOnClickListener(v ->

                {
                    startActivity(new Intent(MainStartActivity.this, NumberActivity.class));

                }
        );

    }
}