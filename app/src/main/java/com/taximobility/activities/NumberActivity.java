package com.taximobility.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.taximobility.Constent.BaseClass;
import com.taximobility.R;
import com.taximobility.databinding.ActivityNumberBinding;
import com.taximobility.retrofit.ApiClient;
import com.taximobility.retrofit.TaximobilityInterface;
import com.taximobility.retrofit.models.SuccessResMobileVerify;
import com.taximobility.retrofit.models.SuccessResSignupMobileVerify;
import com.taximobility.utility.DataManager;
import com.utils.Session.SessionManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.develpoeramit.mapicall.ApiCallBuilder;

import static com.taximobility.retrofit.Constant.USER;
import static com.taximobility.retrofit.Constant.USER_TYPE;
import static com.taximobility.retrofit.Constant.showToast;

public class NumberActivity extends AppCompatActivity {

    ActivityNumberBinding binding;

    TaximobilityInterface taximobilityInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_number);

        taximobilityInterface = ApiClient.getClient().create(TaximobilityInterface.class);

         binding.btnNext.setOnClickListener(v ->
                 {
                     if(binding.etPhone.getText().toString().trim().equalsIgnoreCase(""))
                     {
                         Toast.makeText(NumberActivity.this,""+R.string.enter_mobile_number,Toast.LENGTH_SHORT).show();
                     }else
                     {
                         numberSignup(binding.ccp.getSelectedCountryCode(),binding.etPhone.getText().toString().trim());
                     }
                 }
                 );

    }

    public void numberSignup(String countryCode,String number) {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile",number);
        map.put("type","USER");
        map.put("phone_code",countryCode);

        ApiCallBuilder.build(this)
                .setUrl(BaseClass.get().signupMobileVerify())
                .setParam(map)
                .isShowProgressBar(true)
                .execute(new ApiCallBuilder.onResponse() {
                    @Override
                    public void Success(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            String message=object.getString("message");
                            String result=object.getString("result");
                            Log.e("result", "==>" + result);
                            if (object.getString("status").equals("1")) {
                                SessionManager.get(NumberActivity.this).CreateSession(result,false);
                                startActivity(new Intent(NumberActivity.this,OTPActivity.class).putExtra("mobile",number));
                            } else if (object.getString("status").equals("2")) {
                                SessionManager.get(NumberActivity.this).CreateSession(result,false);
                                startActivity(new Intent(NumberActivity.this,SignupActivity.class));
                            }else  if (object.getString("status").equals("4")) {
                                SessionManager.get(NumberActivity.this).CreateSession(result,false);
                                startActivity(new Intent(NumberActivity.this,OTPActivity.class).putExtra("mobile",number));
                            }
                            showToast(NumberActivity.this, message);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void Failed(String error) {
                        showToast(NumberActivity.this, error);
                    }
                });


    }



}