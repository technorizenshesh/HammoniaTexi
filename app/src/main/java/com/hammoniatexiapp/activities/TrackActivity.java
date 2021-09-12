package com.hammoniatexiapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hammoniatexiapp.Application.MyApplication;
import com.hammoniatexiapp.Utils.LatLngInterpolator;
import com.hammoniatexiapp.Utils.MarkerAnimation;
import com.squareup.picasso.Picasso;
import com.hammoniatexiapp.Constent.BaseClass;
import com.hammoniatexiapp.Constent.Config;
import com.hammoniatexiapp.Constent.DrawPollyLine;
import com.hammoniatexiapp.Dialogs.DialogMessage;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.databinding.ActivityTrackBinding;
import com.hammoniatexiapp.pojos.ModelCurrentBooking;
import com.hammoniatexiapp.pojos.ModelCurrentBookingResult;
import com.hammoniatexiapp.pojos.UserDetail;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

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
    private Marker driverMarkerCar;
    Timer timer = null;
    String driverId="",driverMobile="",driverName="",request_id="",status="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tools.get().updateResources(this);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_track);

        initView();

        if(getIntent() != null) {
            data = (ModelCurrentBooking) getIntent().getSerializableExtra("data");
            result = data.getResult().get(0);
            DriverDetails = result.getUserDetails().get(0);
            driverId = result.getDriverId();
            driverMobile = DriverDetails.getMobile();
            driverName = DriverDetails.getFirstName();
            PicLatLon = new LatLng(Double.parseDouble(result.getPicuplat()),Double.parseDouble(result.getPickuplon()));
            DropLatLon = new LatLng(Double.parseDouble(result.getDroplat()),Double.parseDouble(result.getDroplon()));
            binding.setDriver(DriverDetails);
            if (DriverDetails.getImage() != null) {
               Picasso.get().load(DriverDetails.getImage()).placeholder(R.drawable.user_ic).into(binding.driverImage);
            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MyApplication.get().update(this::getDriverLocation);

    }

    protected void zoomMapInitial(LatLng finalPlace, LatLng currenLoc) {
        try {
            int padding = 200;
            LatLngBounds.Builder bc = new LatLngBounds.Builder();
            bc.include(finalPlace);
            bc.include(currenLoc);
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), padding));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {

        session= SessionManager.get(this);

        DriverMarker = new MarkerOptions().title("Driver Here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_top));
        DropOffMarker = new MarkerOptions().title("Drop Off Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnRate.setOnClickListener(v -> {
            startActivity(new Intent(TrackActivity.this,HomeActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });

        binding.ivCancel.setOnClickListener(v -> {
            DialogMessage.get(TrackActivity.this).setMessage("Are you sure you want to cancel the the ride?").Callback(()->{ CancelRide(); }).show();
        });

        binding.icCall.setOnClickListener(v -> {
            callToDriver();
        });

        binding.layoutforprofileimage.setOnClickListener(v -> {
            startActivity(new Intent(TrackActivity.this,TaxiChatingActivity.class)
                    .putExtra("sender_id",SessionManager.get(TrackActivity.this).getUserID())
                    .putExtra("receiver_id",driverId)
                    .putExtra("name",driverName)
                    .putExtra("request_id",request_id)
            );
        });

    }

    private void callToDriver() {
        Uri number = Uri.parse("tel:" + driverMobile);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    private void CancelRide() {
        HashMap<String,String> parmas=new HashMap<>();
        parmas.put("request_id", session.getLastRequestID());
        Log.e("asdasdffffffff",session.getLastRequestID());
        Log.e("asdasdffffffff",BaseClass.get().cancelRide()+"?request_id=" + session.getLastRequestID());
        ApiCallBuilder.build(TrackActivity.this).setUrl(BaseClass.get().cancelRide())
                .isShowProgressBar(true)
                .setParam(parmas).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    boolean status = object.getString("status").contains("1");
                    Toast.makeText(TrackActivity.this, "" + object.getString("message"), Toast.LENGTH_SHORT).show();
                    if (status) {
                        finishAffinity();
                        startActivity(new Intent(TrackActivity.this,HomeActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Failed(String error) {}

        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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

    BroadcastReceiver statusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("gdsfdsfdsf","statusReceiver");
            if(intent.getAction().equals("Job_Status_Action")) {
                if(intent.getStringExtra("status").equals("Cancel")) {
                    finishAffinity();
                    startActivity(new Intent(TrackActivity.this,HomeActivity.class));
                    Toast.makeText(context, "Your request has been cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    getCurrentBooking();
                }
            }
        }
    };

    BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
            } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
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
        driverMarkerCar = mMap.addMarker(DriverMarker);
        mMap.addMarker(DropOffMarker);

        zoomMapInitial(DropLatLon,PicLatLon);

        mMap.animateCamera(CameraUpdateFactory.newLatLng(PicLatLon));

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

        registerReceiver(statusReceiver,new IntentFilter("Job_Status_Action"));

        getCurrentBooking();

        //        if(timer != null) {
//            timer.cancel();
//        } else {
//            timer = new Timer();
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    getDriverLocation();
//                }
//            },0,5000);
//        }

        LocalBroadcastManager.getInstance(TrackActivity.this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(statusReceiver);
        if(timer != null) timer.cancel();
        LocalBroadcastManager.getInstance(TrackActivity.this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer != null) timer.cancel();
    }

    private void getDriverLocation() {
        HashMap<String,String> param=new HashMap<>();
        param.put("user_id", driverId);
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getDriverLatLon())
                .setParam(param).isShowProgressBar(false)
                .execute(new ApiCallBuilder.onResponse() {
                    @Override
                    public void Success(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if (object.getString("status").equals("1")) {
                                double lat = Double.parseDouble(object.getString("lat"));
                                double lon = Double.parseDouble(object.getString("lon"));
                                Location location = new Location("");
                                location.setLatitude(lat);
                                location.setLongitude(lon);
                                driverMarkerCar.setRotation(location.getBearing());
                                Log.e("LatlonDriverwqw = " , " driver Location = " + new LatLng(lat,lon));
                                Log.e("LatlonDriverwqw = " , " driver Marker = " + driverMarkerCar);
                                MarkerAnimation.animateMarkerToGB(driverMarkerCar, new LatLng(lat,lon), new LatLngInterpolator.Spherical());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void Failed(String error) {}
                });
    }

    private void getCurrentBooking() {
        HashMap<String,String> param = new HashMap<>();
        param.put("user_id",session.getUserID());
        param.put("type","USER");
        param.put("timezone",Tools.get().getTimeZone());
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
                                    ModelCurrentBookingResult result = data.getResult().get(0);
                                    driverId = result.getDriverId();
                                    request_id = result.getId();
                                    if (result.getStatus().equalsIgnoreCase("Pending")) {
                                    } else if (result.getStatus().equalsIgnoreCase("Accept")) {
                                    } else if (result.getStatus().equalsIgnoreCase("Arrived")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Driver arrived").show();
                                    } else if (result.getStatus().equalsIgnoreCase("Start")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Trip Started").show();
                                    } else if (result.getStatus().equalsIgnoreCase("End")) {
                                        DialogMessage.get(TrackActivity.this).setMessage("Trip ended")
                                                .Callback(()->finish()).show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void Failed(String error) {}

                });
    }

}