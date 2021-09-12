package com.hammoniatexiapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityCardAddedBinding;

public class CardAdded extends AppCompatActivity {

    ActivityCardAddedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_added);
        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );
    }
}