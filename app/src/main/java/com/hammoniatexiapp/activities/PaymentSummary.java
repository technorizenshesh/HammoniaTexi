package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hammoniatexiapp.Constent.BaseClass;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityPaymentSummaryBinding;
import com.hammoniatexiapp.pojos.ModelCurrentBooking;
import com.hammoniatexiapp.pojos.ModelCurrentBookingResult;
import com.hammoniatexiapp.pojos.ModelPayment;
import com.utils.Utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import www.develpoeramit.mapicall.ApiCallBuilder;

public class PaymentSummary extends AppCompatActivity {

    ActivityPaymentSummaryBinding binding;
    private ModelCurrentBooking data;
    private ModelCurrentBookingResult result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_summary);
        if(getIntent()!=null) {
            data = (ModelCurrentBooking)getIntent().getSerializableExtra("data");
            result = data.getResult().get(0);
        }
        initView();
    }

    private void initView() {

       binding.btnCollect.setOnClickListener(v -> {
           binding.rlFeedback.setVisibility(View.VISIBLE);
       });

        binding.btnRate.setOnClickListener(v -> {
           AddReview();
        });

        GetFare();
    }

    private void GetFare() {

        HashMap<String,String> param=new HashMap<>();
        param.put("request_id",result.getId());

        ApiCallBuilder.build(this).setUrl(BaseClass.get().getPayment())
                .setParam(param).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                Log.e("Payment",response);
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("1")){
                        Type listType = new TypeToken<ModelPayment>(){}.getType();
                        ModelPayment data = new GsonBuilder().create().fromJson(object.getJSONArray("result").getJSONObject(0).toString(), listType);
                        binding.setPayment(data);
                        binding.executePendingBindings();
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
    private void AddReview() {

        HashMap<String,String> params=new HashMap<>();
        params.put("request_id", result.getId());
        params.put("user_id", result.getUserId());
        params.put("driver_id", result.getDriverId());
        params.put("rating", ""+binding.ratingBar.getRating());
        params.put("timezone", Tools.get().getTimeZone());
        params.put("review", ""+binding.editText.getText().toString());
        ApiCallBuilder.build(this).setUrl(BaseClass.get().addRatingReview())
                .isShowProgressBar(true)
                .setParam(params).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                Log.e("addRatingReview",response);
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("1")){
                        startActivity(new Intent(PaymentSummary.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void Failed(String error) {
                Log.e("addRatingReview",error);
            }
        });
    }
}
