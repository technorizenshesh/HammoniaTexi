package com.taximobility.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.braintreepayments.cardform.view.CardForm;
import com.taximobility.R;
import com.taximobility.databinding.ActivityAddCardBinding;

public class AddCard extends AppCompatActivity {

    ActivityAddCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_card);
        binding.ivBack.setOnClickListener(v ->
                {
                    finish();
                }
        );

        binding.btnAdd.setOnClickListener(v ->
                {
                    startActivity(new Intent(this,CardAdded.class));
                }
                );

        binding.cardForm.cardRequired(true)
                .maskCardNumber(true)
                .maskCvv(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(false)
                .mobileNumberRequired(false)
                .saveCardCheckBoxChecked(false)
                .saveCardCheckBoxVisible(false)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .mobileNumberExplanation("Make sure SMS is enabled for this mobile number")
                .actionLabel(getString(R.string.purchase))
                .setup(this);

    }
}