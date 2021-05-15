package com.taximobility.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.taximobility.Constent.BaseClass;
import com.taximobility.Constent.Config;
import com.taximobility.Constent.DrawPollyLine;
import com.taximobility.Dialogs.DialogMessage;
import com.taximobility.R;
import com.taximobility.databinding.ActivityTrackBinding;
import com.taximobility.pojos.ModelCurrentBooking;
import com.taximobility.pojos.ModelCurrentBookingResult;
import com.taximobility.pojos.UserDetail;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import www.develpoeramit.mapicall.ApiCallBuilder;

public class TrackActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityTrackBinding binding;
    private ModelCurrentBooking data;
    private ModelCurrentBookingResult result;
    private UserDetail DriverDetails;
    private LatLng PicLatLon,DropLatLon;
    private GoogleMap mMap;
    private MarkerOptions DriverMarker;
    private MarkerOptions DropOffMarker;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.get().updateResources(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_track);
        initView();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(getIntent()!=null) {
            data = (ModelCurrentBooking) getIntent().getSerializableExtra("data");
            result = data.getResult().get(0);
            DriverDetails = result.getUserDetails().get(0);
            PicLatLon = new LatLng(Double.parseDouble(result.getPicuplat()),Double.parseDouble(result.getPickuplon()));
            DropLatLon = new LatLng(Double.parseDouble(result.getDroplat()),Double.parseDouble(result.getDroplon()));
       binding.setDriver(DriverDetails);
            if (DriverDetails.getImage()!=null){
           Picasso.get().load(DriverDetails.getImage()).placeholder(R.drawable.user_ic).into(binding.driverImage);
       }
        }
    }

    private void initView() {
        session= SessionManager.get(this);
        DriverMarker=new MarkerOptions().title("Driver Here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_top));
        DropOffMarker=new MarkerOptions().title("Drop Off Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnRate.setOnClickListener(v -> {
              startActivity(new Intent(TrackActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
              finish();
        });

        binding.ivCancel.setOnClickListener(v -> {
//            startActivity(new Intent(TrackActivity.this,RideCancelAct.class));
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        drawRoute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                binding.titler.setText("Send Feedback");
                binding.btnBack.setVisibility(View.GONE);
                binding.rlDriver.setVisibility(View.GONE);
                binding.rlFeedback.setVisibility(View.VISIBLE);
            }
        }
    }

    BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

            }
            else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                String message = intent.getStringExtra("message");
                JSONObject data = null;
                try {
                    data = new JSONObject(message);
                    String keyMessage = data.getString("key").trim();
                    Log.e("KEY ACCEPT REJ", "" + keyMessage);
                   getCurrentBooking();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }
    private void drawRoute() {
        DriverMarker.position(PicLatLon);
        DropOffMarker.position(DropLatLon);
        mMap.addMarker(DriverMarker);
        mMap.addMarker(DropOffMarker);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PicLatLon)));
        DrawPollyLine.get(this)
                .setOrigin(PicLatLon)
                .setDestination(DropLatLon)
                .execute(new DrawPollyLine.onPolyLineResponse() {
                    @Override
                    public void Success(ArrayList<LatLng> latLngs) {
                        PolylineOptions options=new PolylineOptions();
                        options.addAll(latLngs);
                        options.color(Color.BLUE);
                        options.width(10);
                        options.startCap(new SquareCap());
                        options.endCap(new SquareCap());

                        Polyline line = mMap.addPolyline(options);


                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(TrackActivity.this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
        getCurrentBooking();

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(TrackActivity.this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
    private void getCurrentBooking(){
        HashMap<String,String> param=new HashMap<>();
        param.put("user_id", session.getUserID());
        param.put("type", "USER");
        param.put("timezone", Tools.get().getTimeZone());
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getCurrentBooking())
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

                                    } else if (result.getStatus().equalsIgnoreCase("Arrived")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Driver arrived").show();
                                    } else if (result.getStatus().equalsIgnoreCase("Start")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Trip Started").show();
                                    } else if (result.getStatus().equalsIgnoreCase("End")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Trip ended").show();
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