package com.taximobility.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.taximobility.R;
import com.taximobility.databinding.ActivityLoginBinding;
import com.taximobility.retrofit.ApiClient;
import com.taximobility.retrofit.Constant;
import com.taximobility.retrofit.TaximobilityInterface;
import com.taximobility.retrofit.models.SuccessResSignIn;
import com.taximobility.retrofit.models.SuccessResSignUp;
import com.taximobility.utility.DataManager;
import com.taximobility.utility.NetworkAvailablity;
import com.taximobility.utility.SharedPreferenceUtility;
import com.utils.Session.SessionManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.taximobility.retrofit.Constant.USER;
import static com.taximobility.retrofit.Constant.USER_TYPE;
import static com.taximobility.retrofit.Constant.isValidEmail;
import static com.taximobility.retrofit.Constant.showToast;

public class LoginActivity extends AppCompatActivity {

    TaximobilityInterface apiInterface;
    public static String TAG = "LoginActivity";

    private ActivityLoginBinding binding;
    private String strPhone ="",strPassword= "",deviceToken = "";

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    String strName = "", strSocialId ="",strMobile = "",strPhoneCode= "";
    String str_image_path = "";
    private CallbackManager mCallbackManager;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        apiInterface = ApiClient.getClient().create(TaximobilityInterface.class);

        getToken();


        binding.rlForgotPass.setOnClickListener(v -> {
            startActivity(new Intent(this,ForgotPasswordActivity.class));
        });

        binding.rlLinkSignup.setOnClickListener(v -> {
            startActivity(new Intent(this,NumberActivity.class));
        });

        binding.btnSignin.setOnClickListener(v -> {
            strPhone = binding.etPhone.getText().toString().trim();
            strPassword = binding.etPassword.getText().toString().trim();
            if(isValid()) {
                if (NetworkAvailablity.getInstance(this).checkNetworkStatus()) {
                    login();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.msg_noInternet), Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,  getResources().getString(R.string.on_error), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void login() {
        DataManager.getInstance().showProgressMessage(LoginActivity.this, getString(R.string.please_wait));
        Map<String,String> map = new HashMap<>();
        map.put("mobile",strPhone);
        map.put("password",strPassword);
        map.put(USER_TYPE,USER);
        map.put("register_id",deviceToken);
      Call<SuccessResSignIn> call = apiInterface.login(map);
          call.enqueue(new Callback<SuccessResSignIn>() {
            @Override
            public void onResponse(Call<SuccessResSignIn> call, Response<SuccessResSignIn> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    SuccessResSignIn data = response.body();
                    Log.e("data",data.status);
                    if (data.status.equals("1")) {
                        showToast(LoginActivity.this, data.message);
                        String dataResponse = new Gson().toJson(data.result);
                        Log.e("MapMap", "EDIT PROFILE RESPONSE" + dataResponse);
                        SessionManager.get(LoginActivity.this).CreateSession(dataResponse);
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();
                    } else if (data.status.equals("0")) {
                        showToast(LoginActivity.this, data.message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessResSignIn> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }
        });
    }

    private boolean isValid() {
        if (strPhone.equalsIgnoreCase("")) {
            binding.etPhone.setError(getString(R.string.enter_mobile_number));
            return false;
        } else if (strPassword.equalsIgnoreCase("")) {
            binding.etPassword.setError(getString(R.string.please_enter_pass));
            return false;
        }
        return true;
    }
  /*  *
     * This method is used to get fcm token
     */
    private void getToken() {
        try {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            // Get new FCM registration token
                            String token = task.getResult();
                            deviceToken = token;
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Error=>" + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}