package com.taximobility.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.taximobility.Constent.BaseClass;
import com.taximobility.R;
import com.taximobility.adapters.AdapterRideHistory;
import com.taximobility.databinding.ActivityRideHistoryBinding;
import com.taximobility.pojos.ModelRideHistory;
import com.utils.Session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import www.develpoeramit.mapicall.ApiCallBuilder;

public class RideHistoryActivity extends AppCompatActivity {
    ActivityRideHistoryBinding binding;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_history);
        session= SessionManager.get(this);
        initView();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(v -> {finish();});
        GetHistory();
    }
    private void GetHistory(){
        HashMap<String,String> param=new HashMap<>();
        param.put("user_id", session.getUserID());
        param.put("type", "USER");
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getUserHistory())
                .setParam(param).isShowProgressBar(true)
                .execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                Log.e("RideHistory","==>"+response);
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("1")){
                        binding.tvNoResult.setVisibility(View.GONE);
                        Type listType = new TypeToken<ModelRideHistory>(){}.getType();
                        ModelRideHistory data = new GsonBuilder().create().fromJson(response, listType);
                        binding.rvRideHistory.setAdapter(new AdapterRideHistory(RideHistoryActivity.this,data.getResult(),RideHistoryActivity.this::onViewDetails));
                    }else {
                        binding.tvNoResult.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void Failed(String error) {
            }
        });
    }

    private void onViewDetails(ModelRideHistory.Result result) {
        startActivity(new Intent(this,RideDetailActivity.class).putExtra("data",result));
    }
}