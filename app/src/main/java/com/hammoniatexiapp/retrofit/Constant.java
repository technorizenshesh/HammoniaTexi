package com.hammoniatexiapp.retrofit;

import android.app.Activity;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {
    public static final String USER_INFO = "user_info";
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String driver_id = "driver_id";
    public final static String SIGNUP_API = "signup";
    public final static String LOGIN_API = "login";
    public final static String MOBILE_VERIFY = "mobile_verify";
    public final static String CHECK_OTP = "check_otp";
    public final static String SOCIAL_LOGIN = "social_login";
    public final static String GET_PROFILE = "get_profile";
    public final static String FORGET_PASSWORD = "forgot_password";
    public final static String SIGNUP_MOBILE_VERIFY = "signup_mobile_verify";
    public static final String USER_TYPE = "type";
    public static final String USER="USER";

    public static boolean isValidEmail(CharSequence target) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(target);

        return matcher.matches();
    }


    public static void showToast(Activity context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
