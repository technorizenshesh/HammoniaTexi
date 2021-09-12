package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityRideDetailBinding;
import com.hammoniatexiapp.fragments.RaiseIssueBottomSheet;
import com.hammoniatexiapp.pojos.ModelRideHistory;

public class RideDetailActivity extends AppCompatActivity {

    ActivityRideDetailBinding binding;
    private ModelRideHistory.Result data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_detail);
        data = (ModelRideHistory.Result) getIntent().getSerializableExtra("data");
        binding.setData(data);
        binding.executePendingBindings();
        binding.ivBack.setOnClickListener(v->finish());
        binding.GoToDriver.setOnClickListener(v -> {
                    startActivity(new Intent(this, DriverDetailActivity.class).putExtra("driver_data", data.getDriverDetails().get(0)));
                }
        );

        binding.btnRaiseIssue.setOnClickListener(v -> {
                    RaiseIssueBottomSheet bottomSheetFragment = new RaiseIssueBottomSheet();
                    Bundle bundle = new Bundle();
                    bundle.putString("link", "Hello Friends");
                    bottomSheetFragment.setArguments(bundle);
                    bottomSheetFragment.show(getSupportFragmentManager(), "ModalBottomSheet");
                }
        );

    }
}