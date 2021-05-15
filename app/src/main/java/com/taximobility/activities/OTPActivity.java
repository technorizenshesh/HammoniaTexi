package com.taximobility.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.taximobility.R;
import com.taximobility.databinding.ActivityOTPBinding;
import com.taximobility.retrofit.ApiClient;
import com.taximobility.retrofit.Constant;
import com.taximobility.retrofit.TaximobilityInterface;
import com.taximobility.retrofit.models.SuccessResCheckOtp;
import com.taximobility.retrofit.models.SuccessResMobileVerify;
import com.taximobility.utility.DataManager;
import com.taximobility.utility.NetworkAvailablity;
import com.taximobility.utility.SharedPreferenceUtility;
import com.utils.Session.SessionManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.taximobility.retrofit.Constant.isValidEmail;
import static com.taximobility.retrofit.Constant.showToast;

public class OTPActivity extends AppCompatActivity {
    ActivityOTPBinding binding;
    String strOtp="";
    String str1 = "",str2 = "",str3 = "",str4 = "",finalOtp="";

    TaximobilityInterface apiInterface;
    Context mContext;
    String strPhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_o_t_p);
        apiInterface = ApiClient.getClient().create(TaximobilityInterface.class);
        mContext = OTPActivity.this;
        strPhone = getIntent().getStringExtra("mobile");
        init();

        binding.btnVerify.setOnClickListener(v ->
                {

                    if (TextUtils.isEmpty(binding.et1.getText().toString().trim())) {
                        Toast.makeText(mContext, getString(R.string.please_enter_com_otp), Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(binding.et2.getText().toString().trim())) {
                        Toast.makeText(mContext, getString(R.string.please_enter_com_otp), Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(binding.et3.getText().toString().trim())) {
                        Toast.makeText(mContext, getString(R.string.please_enter_com_otp), Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(binding.et4.getText().toString().trim())) {
                        Toast.makeText(mContext, getString(R.string.please_enter_com_otp), Toast.LENGTH_SHORT).show();
                    } else {
                        finalOtp =
                                binding.et1.getText().toString().trim() +
                                        binding.et2.getText().toString().trim() +
                                        binding.et3.getText().toString().trim() +
                                        binding.et4.getText().toString().trim()
                        ;
                        verifyOtp(finalOtp);

                    }
                }
        );
    }

    public void mobileVerify(String phone) {

        DataManager.getInstance().showProgressMessage(OTPActivity.this, getString(R.string.please_wait));
        Map<String,String> map = new HashMap<>();
        map.put("mobile",phone);

        Call<SuccessResMobileVerify> signupCall = apiInterface.mobileVerify(map);
        signupCall.enqueue(new Callback<SuccessResMobileVerify>() {
            @Override
            public void onResponse(Call<SuccessResMobileVerify> call, Response<SuccessResMobileVerify> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    SuccessResMobileVerify data = response.body();
                    if (data.status.equals("1")) {
                        showToast(OTPActivity.this, data.message);
                        String dataResponse = new Gson().toJson(data.result);
                        Log.e("MapMap", "EDIT PROFILE RESPONSE" + dataResponse);
                    } else if (data.status.equals("0")) {
                        showToast(OTPActivity.this, data.message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessResMobileVerify> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }

        });

    }

    private boolean isValid() {
        if (str1.equalsIgnoreCase("")) {

            return false;
        }
        return true;
    }

    private void init() {


        binding.et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    binding.et2.setText("");
                    binding.et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });

        binding.et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    binding.et3.setText("");
                    binding.et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });

        binding.et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    binding.et4.setText("");
                    binding.et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });

        binding.et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });



    }

    public void verifyOtp(String otp) {
        DataManager.getInstance().showProgressMessage(OTPActivity.this, getString(R.string.please_wait));
        Map<String,String> map = new HashMap<>();
        map.put("mobile",strPhone);
        map.put("otp",otp);

        Call<SuccessResCheckOtp> signupCall = apiInterface.checkOtp(map);
        signupCall.enqueue(new Callback<SuccessResCheckOtp>() {
            @Override
            public void onResponse(Call<SuccessResCheckOtp> call, Response<SuccessResCheckOtp> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    SuccessResCheckOtp data = response.body();
                    if (data.status.equals("1")) {
                        String dataResponse = new Gson().toJson(response.body());
                        Log.e("MapMap", "EDIT PROFILE RESPONSE" + dataResponse);
                        startActivity(new Intent(OTPActivity.this,SignupActivity.class));
                    } else if (data.status.equals("0")) {
                        showToast(OTPActivity.this, data.message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessResCheckOtp> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }

        });

    }

}