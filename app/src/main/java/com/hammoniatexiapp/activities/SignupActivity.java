
package com.hammoniatexiapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivitySignupBinding;
import com.hammoniatexiapp.retrofit.ApiClient;
import com.hammoniatexiapp.retrofit.TaximobilityInterface;
import com.hammoniatexiapp.retrofit.models.SuccessResSignUp;
import com.hammoniatexiapp.utility.DataManager;
import com.hammoniatexiapp.utility.NetworkAvailablity;
import com.utils.Session.SessionManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hammoniatexiapp.retrofit.Constant.isValidEmail;
import static com.hammoniatexiapp.retrofit.Constant.showToast;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String strName = "", strEmail = "", strPass = "";
    TaximobilityInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        apiInterface = ApiClient.getClient().create(TaximobilityInterface.class);
        binding.ivBack.setOnClickListener(v -> finish());
        binding.btnSignup.setOnClickListener(v -> {
                    strName = binding.etName.getText().toString().trim();
                    strEmail = binding.etEmail.getText().toString().trim();
                    strPass = binding.etPassword.getText().toString().trim();
                    if (isValid()) {
                        if (NetworkAvailablity.getInstance(this).checkNetworkStatus()) {
                            signup();
                        } else {
                            Toast.makeText(this, getResources().getString(R.string.msg_noInternet), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.on_error), Toast.LENGTH_SHORT).show();

                    }

                }
        );
    }


    public void signup() {

        DataManager.getInstance().showProgressMessage(SignupActivity.this, getString(R.string.please_wait));
        Map<String,String> map = new HashMap<>();
        map.put("user_name",strName);
        map.put("email",strEmail);
        map.put("password",strPass);
        map.put("type","USER");
        map.put("register_id","");
        map.put("user_id", SessionManager.get(SignupActivity.this).getUserID());

        Call<SuccessResSignUp> signupCall = apiInterface.signup(map);
        signupCall.enqueue(new Callback<SuccessResSignUp>() {
            @Override
            public void onResponse(Call<SuccessResSignUp> call, Response<SuccessResSignUp> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    SuccessResSignUp data = response.body();
                    if (data.status.equals("1")) {
                        showToast(SignupActivity.this, data.message);
                        String dataResponse = new Gson().toJson(response.body());
                        Log.e("MapMap", "EDIT PROFILE RESPONSE" + dataResponse);
                        SessionManager.get(SignupActivity.this).CreateSession(dataResponse);
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else if (data.status.equals("0")) {
                        showToast(SignupActivity.this, data.message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessResSignUp> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }

        });
    }
    private boolean isValid() {

        if (strName.equalsIgnoreCase("")) {
            binding.etName.setError(getString(R.string.enter_name));
            return false;
        } else if (strEmail.equalsIgnoreCase("")) {
            binding.etEmail.setError(getString(R.string.enter_email));
            return false;
        }  else if (!isValidEmail(strEmail)) {
            binding.etEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (strPass.equalsIgnoreCase("")) {
            binding.etPassword.setError(getString(R.string.please_enter_pass));
            return false;
        }

        return true;

    }


}