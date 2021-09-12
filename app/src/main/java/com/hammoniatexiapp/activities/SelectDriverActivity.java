package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivitySelectDriverBinding;

public class SelectDriverActivity extends AppCompatActivity {

    ActivitySelectDriverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_driver);

        binding.btnContinue.setOnClickListener(v ->
                {

                    startActivity(new Intent(getBaseContext(), HomeActivity.class)
                            .putExtra("my_boolean_key", true).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();

                }

                );

        binding.rlFemale.setOnClickListener(v ->
                {
                    binding.rlFemale.setBackgroundResource(R.drawable.cirular_red_stroke);
                    binding.rlMale.setBackgroundResource(R.drawable.white_circular_bg);
                }
                );
        binding.rlMale.setOnClickListener(v ->
                {
                    binding.rlMale.setBackgroundResource(R.drawable.cirular_red_stroke);
                    binding.rlFemale.setBackgroundResource(R.drawable.white_circular_bg);
                }
        );
    }
}