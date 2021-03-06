package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.adapters.PaymentAdapter;
import com.hammoniatexiapp.databinding.ActivityPaymentBinding;

public class PaymentAct extends AppCompatActivity {
    ActivityPaymentBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_payment);
        initView();
    }

    private void initView() {
        binding.rvPayinfo.setAdapter(new PaymentAdapter(PaymentAct.this));
        binding.actionAdd.setOnClickListener(v -> {startActivity(new Intent(this,AddCard.class));});
        binding.ivBack.setOnClickListener(v -> {finish();});
    }


}
