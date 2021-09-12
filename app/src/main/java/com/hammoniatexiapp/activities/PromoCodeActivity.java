package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityPromoCodeBinding;

public class PromoCodeActivity extends AppCompatActivity {

    ActivityPromoCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_promo_code);
        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );

        binding.llPromocode.setOnClickListener(v ->
                {
                    startActivity(new Intent(this,AddPromoCodeActivity.class));
                }
                );

    }
}