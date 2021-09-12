package com.hammoniatexiapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityForgotPasswordBinding;
import com.hammoniatexiapp.retrofit.ApiClient;
import com.hammoniatexiapp.retrofit.TaximobilityInterface;
import com.hammoniatexiapp.retrofit.models.SuccessResForgetPassword;
import com.hammoniatexiapp.utility.DataManager;
import com.hammoniatexiapp.utility.NetworkAvailablity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hammoniatexiapp.retrofit.Constant.isValidEmail;
import static com.hammoniatexiapp.retrofit.Constant.showToast;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    private String strEmail = "";
    TaximobilityInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        binding.ivBack.setOnClickListener(v -> finish());

        apiInterface = ApiClient.getClient().create(TaximobilityInterface.class);



        binding.btnSend.setOnClickListener(v ->
                {

                    strEmail = binding.etEmail.getText().toString().trim();

                    if(isValid())
                    {

                        if (NetworkAvailablity.getInstance(this).checkNetworkStatus()) {
                            forgotPass();
                        } else {
                            Toast.makeText(this, getResources().getString(R.string.msg_noInternet), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, getResources().getString(R.string.on_error), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    private void forgotPass() {

        DataManager.getInstance().showProgressMessage(ForgotPasswordActivity.this, getString(R.string.please_wait));
        Map<String,String> map = new HashMap<>();
        map.put("email",strEmail);
        Call<SuccessResForgetPassword> call = apiInterface.forgotPassword(map);
        call.enqueue(new Callback<SuccessResForgetPassword>() {
            @Override
            public void onResponse(Call<SuccessResForgetPassword> call, Response<SuccessResForgetPassword> response) {

                DataManager.getInstance().hideProgressMessage();
                try {
                    SuccessResForgetPassword data = response.body();
                    if (data.status.equals("1")) {
                        String dataResponse = new Gson().toJson(response.body());
                        Log.e("MapMap", "EDIT PROFILE RESPONSE" + dataResponse);
                        Toast.makeText(ForgotPasswordActivity.this, R.string.please_check_email,Toast.LENGTH_SHORT).show();
//                        SessionManager.writeString(RegisterAct.this, Constant.driver_id,data.result.id);
//                        App.showToast(RegisterAct.this, data.message, Toast.LENGTH_SHORT);
                        binding.etEmail.setText("");
                    } else if (data.status.equals("0")) {
                        showToast(ForgotPasswordActivity.this, data.message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessResForgetPassword> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }
        });

    }


    private boolean isValid() {
        if (strEmail.equalsIgnoreCase("")) {
            binding.etEmail.setError("Please enter email.");
            return false;
        } else if (!isValidEmail(strEmail)) {
            binding.etEmail.setError("Please enter valid email.");
            return false;
        }
        return true;
    }

}