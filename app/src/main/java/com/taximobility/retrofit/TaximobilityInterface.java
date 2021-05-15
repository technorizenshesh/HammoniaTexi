package com.taximobility.retrofit;


import com.taximobility.retrofit.models.SuccessResCheckOtp;
import com.taximobility.retrofit.models.SuccessResForgetPassword;
import com.taximobility.retrofit.models.SuccessResMobileVerify;
import com.taximobility.retrofit.models.SuccessResProfileData;
import com.taximobility.retrofit.models.SuccessResSignIn;
import com.taximobility.retrofit.models.SuccessResSignUp;
import com.taximobility.retrofit.models.SuccessResSignupMobileVerify;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.taximobility.retrofit.Constant.CHECK_OTP;
import static com.taximobility.retrofit.Constant.FORGET_PASSWORD;
import static com.taximobility.retrofit.Constant.GET_PROFILE;
import static com.taximobility.retrofit.Constant.LOGIN_API;
import static com.taximobility.retrofit.Constant.MOBILE_VERIFY;
import static com.taximobility.retrofit.Constant.SIGNUP_API;
import static com.taximobility.retrofit.Constant.SIGNUP_MOBILE_VERIFY;
import static com.taximobility.retrofit.Constant.SOCIAL_LOGIN;

public interface TaximobilityInterface {

    @FormUrlEncoded
    @POST(SIGNUP_API)
    Call<SuccessResSignUp> signup(@FieldMap Map<String, String> paramHashMap);

    @FormUrlEncoded
    @POST(LOGIN_API)
    Call<SuccessResSignIn> login(@FieldMap Map<String, String> paramHashMap);

    @FormUrlEncoded
    @POST(MOBILE_VERIFY)
    Call<SuccessResMobileVerify> mobileVerify(@FieldMap Map<String, String> paramHashMap);


    @FormUrlEncoded
    @POST(CHECK_OTP)
    Call<SuccessResCheckOtp> checkOtp(@FieldMap Map<String, String> paramHashMap);

    @Multipart
    @POST(SOCIAL_LOGIN)
    Call<SuccessResSignUp> socialLogin (@Part("user_name") RequestBody userName,
                                             @Part("phone_code") RequestBody phoneCode,
                                             @Part("mobile") RequestBody mobile,
                                             @Part("email") RequestBody email,
                                             @Part("type") RequestBody type,
                                             @Part("social_id") RequestBody socialId,
                                             @Part("register_id") RequestBody registerId);

    @FormUrlEncoded
    @POST(GET_PROFILE)
    Call<SuccessResProfileData> getSellerDetails(@FieldMap Map<String, String> paramHashMap);


    @FormUrlEncoded
    @POST(FORGET_PASSWORD)
    Call<SuccessResForgetPassword> forgotPassword(@FieldMap Map<String, String> paramHashMap);



    @FormUrlEncoded
    @POST(SIGNUP_MOBILE_VERIFY)
    Call<String> signupMobileVerify(@FieldMap Map<String, String> paramHashMap);



}
