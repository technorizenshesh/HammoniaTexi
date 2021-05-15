package com.taximobility.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.taximobility.R;
import com.taximobility.adapters.PaymentItemAdapter;
import com.taximobility.databinding.ActivityPaymentOptionBinding;

public class PaymentOptionActivity extends AppCompatActivity {

    ActivityPaymentOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_option);

        binding.rvCar.setHasFixedSize(true);
        binding.rvCar.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCar.setAdapter(new PaymentItemAdapter(this));

    }
}