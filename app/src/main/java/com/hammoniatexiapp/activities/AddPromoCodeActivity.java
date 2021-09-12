package com.hammoniatexiapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityAddPromoCodeBinding;

public class AddPromoCodeActivity extends AppCompatActivity {

    ActivityAddPromoCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_promo_code);
        binding.ivBack.setOnClickListener(v -> {
                    finish();
           }
        );

    }
}