package com.hammoniatexiapp.Dialogs;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hammoniatexiapp.Constent.BaseClass;
import com.hammoniatexiapp.Constent.Config;
import com.hammoniatexiapp.Interfaces.onSearchingDialogListener;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.DialogSearchDriverBinding;
import com.hammoniatexiapp.pojos.ModelCurrentBooking;
import com.hammoniatexiapp.pojos.ModelCurrentBookingResult;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import www.develpoeramit.mapicall.ApiCallBuilder;


public class DialogSearchingDriver extends Dialog {

    private DialogSearchDriverBinding binding;
    private onSearchingDialogListener listener;
    private SessionManager session;
    private CountDownTimer timer;

    public static DialogSearchingDriver get(Context context) {
        return new DialogSearchingDriver(context);
    }

    public DialogSearchingDriver(Context context) {
        super(context);
    }

    public DialogSearchingDriver Callback(onSearchingDialogListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_search_driver, null, false);
        setContentView(binding.getRoot());
        session = SessionManager.get(getContext());
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);
        binding.ripple.startRippleAnimation();
        binding.btnCancel.setOnClickListener(v->onBackPressed());
        timer = new CountDownTimer(1000,50000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getCurrentBooking();
            }

            @Override
            public void onFinish() {
                timer.start();
            }
        }.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        DialogMessage.get(getContext()).setMessage("Are you sure want to cancel Request?")
                .Callback(this::CancelRide).show();
    }

    private void CancelRide() {
        HashMap<String,String> parmas=new HashMap<>();
        parmas.put("request_id", session.getLastRequestID());
        ApiCallBuilder.build(getContext()).setUrl(BaseClass.get().cancelRide())
                .isShowProgressBar(true)
                .setParam(parmas).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    boolean status=object.getString("status").contains("1");
                    Toast.makeText(getContext(), ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                    if (status){
                        listener.onRequestCancel();
                       dismiss();
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

    BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                String message = intent.getStringExtra("message");
                JSONObject data = null;
                try {
                    data = new JSONObject(message);
                    String keyMessage = data.getString("key").trim();
                    Log.e("Notification_key", "" + keyMessage);
                    String request_id = data.getString("request_id");
                    session.setLastRequestID(request_id);
                    if (keyMessage.equalsIgnoreCase("your booking request is ACCEPT")) {
                        String driver_id = data.getString("driver_id");
                       getCurrentBooking();
                        timer.cancel();
                        dismiss();
                    }
                    if (keyMessage.equalsIgnoreCase("your booking request is Cancel")) {
                        listener.onRequestCancel();
                        timer.cancel();
                        dismiss();
                    }
                    if (keyMessage.equalsIgnoreCase("no driver available")) {
                        listener.onDriverNotFound();
                        timer.cancel();
                        dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void getCurrentBooking() {
        HashMap<String,String>param=new HashMap<>();
        param.put("user_id", session.getUserID());
        param.put("type", "USER");
        param.put("timezone", Tools.get().getTimeZone());
        ApiCallBuilder.build(getContext()).setUrl(BaseClass.get().getCurrentBooking())
                .setParam(param).isShowProgressBar(false)
                .execute(new ApiCallBuilder.onResponse() {
                    @Override
                    public void Success(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if (object.getString("status").equals("1")){
                                Type listType = new TypeToken<ModelCurrentBooking>(){}.getType();
                                ModelCurrentBooking data = new GsonBuilder().create().fromJson(response, listType);
                                if (data.getStatus().equals(1)) {
                                    ModelCurrentBookingResult result=data.getResult().get(0);
                                    if (result.getStatus().equalsIgnoreCase("Pending")) {
                                    }else if (result.getStatus().equalsIgnoreCase("Accept")) {
                                        listener.onRequestAccepted(data);
                                        timer.cancel();
                                        dismiss();
                                    }
                                }
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

}
