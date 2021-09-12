package com.hammoniatexiapp.retrofit;


import com.hammoniatexiapp.retrofit.models.SuccessResCheckOtp;
import com.hammoniatexiapp.retrofit.models.SuccessResForgetPassword;
import com.hammoniatexiapp.retrofit.models.SuccessResMobileVerify;
import com.hammoniatexiapp.retrofit.models.SuccessResProfileData;
import com.hammoniatexiapp.retrofit.models.SuccessResSignIn;
import com.hammoniatexiapp.retrofit.models.SuccessResSignUp;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.hammoniatexiapp.retrofit.Constant.CHECK_OTP;
import static com.hammoniatexiapp.retrofit.Constant.FORGET_PASSWORD;
import static com.hammoniatexiapp.retrofit.Constant.GET_PROFILE;
import static com.hammoniatexiapp.retrofit.Constant.LOGIN_API;
import static com.hammoniatexiapp.retrofit.Constant.MOBILE_VERIFY;
import static com.hammoniatexiapp.retrofit.Constant.SIGNUP_API;
import static com.hammoniatexiapp.retrofit.Constant.SIGNUP_MOBILE_VERIFY;
import static com.hammoniatexiapp.retrofit.Constant.SOCIAL_LOGIN;

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

    @FormUrlEncoded
    @POST("insert_chat")
    Call<ResponseBody> insertChatBookingCall(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("get_chat_detail")
    Call<ResponseBody> getChatBookingCall(@FieldMap Map<String,String> params);

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
