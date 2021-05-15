package com.taximobility.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.taximobility.R;
import com.taximobility.databinding.ActivityProfileBinding;
import com.taximobility.retrofit.ApiClient;
import com.taximobility.retrofit.TaximobilityInterface;
import com.taximobility.retrofit.models.SuccessResProfileData;
import com.taximobility.utility.DataManager;
import com.taximobility.utility.SharedPreferenceUtility;
import com.utils.Session.ModelUser;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        session= SessionManager.get(this);
        binding.ivBack.setOnClickListener(v -> {finish();});
        ModelUser user=session.getValue();
        binding.tvMail.setText(user.getEmail());
        binding.tvName.setText(user.getFirstName());
        binding.tvPlace.setText(user.getAddress());
        binding.tvMobile.setText(user.getMobile());
    }

}