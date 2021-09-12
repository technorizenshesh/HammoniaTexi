package com.hammoniatexiapp.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityProfileBinding;
import com.utils.Session.ModelUser;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;


public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.get().updateResources(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        initView();
    }

    private void initView() {
        session = SessionManager.get(this);
        binding.ivBack.setOnClickListener(v -> {finish();});
        ModelUser user=session.getValue();
        binding.tvMail.setText(user.getEmail());
        binding.tvName.setText(user.getFirstName());
        binding.tvPlace.setText(user.getAddress());
        binding.tvMobile.setText(user.getMobile());
    }


}