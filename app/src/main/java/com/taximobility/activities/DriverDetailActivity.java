package com.taximobility.activities;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.taximobility.R;
import com.taximobility.databinding.ActivityDriverDetailBinding;
import com.taximobility.pojos.UserDetail;
public class DriverDetailActivity extends AppCompatActivity {
    ActivityDriverDetailBinding binding;
    private UserDetail data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver_detail);
        data=(UserDetail)getIntent().getSerializableExtra("driver_data");
        binding.setData(data);
        binding.executePendingBindings();
        binding.ivBack.setOnClickListener(v -> { finish(); });
    }
}