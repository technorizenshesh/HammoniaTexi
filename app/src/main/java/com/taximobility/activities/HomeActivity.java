package com.taximobility.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.taximobility.Constent.BaseClass;
import com.taximobility.Constent.Config;
import com.taximobility.Constent.DrawPollyLine;
import com.taximobility.Dialogs.DialogMessage;
import com.taximobility.Dialogs.DialogSearchingDriver;
import com.taximobility.Interfaces.onSearchingDialogListener;
import com.taximobility.R;
import com.taximobility.databinding.ActivityHomeBinding;
import com.taximobility.fragments.SerachPlaceBottumSheetFragment;
import com.taximobility.pojos.ModelAvailableDriver;
import com.taximobility.pojos.ModelCurrentBooking;
import com.taximobility.pojos.ModelCurrentBookingResult;
import com.taximobility.utility.GPSTracker;
import com.utils.Session.SessionManager;
import com.utils.Utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import www.develpoeramit.mapicall.ApiCallBuilder;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, onSearchingDialogListener {
    ActivityHomeBinding binding;
    GoogleMap mMap;
    GPSTracker gpsTracker;
    int PERMISSION_ID = 44;
    private SessionManager session;
    List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.ADDRESS);
    private PlacesClient placesClient;
    private static final String TAG = "HomeAct";
    private LatLng PickUpLatLng,DropOffLatLng;
    private MarkerOptions PicUpMarker,DropOffMarker;
    private boolean isAddedMarker2=false,isAddedMarker1=false;
    private PolylineOptions lineOptions;
    private ScheduledExecutorService scheduleTaskExecutor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.get().updateResources(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        BindData();
        initViews();
    }

    private void BindData() {
        gpsTracker = new GPSTracker(HomeActivity.this);
        PickUpLatLng=new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude());
        session=SessionManager.get(this);
        Log.e("UserDetails","==>"+session.getAllDetails());
        binding.chlidDashboard.tvFrom.setText(Tools.getCompleteAddressString(this,gpsTracker.getLatitude(),gpsTracker.getLongitude()));
        binding.childNavDrawer.tvUsername.setText(session.getValue().getFirstName());
        binding.childNavDrawer.tvEmail.setText(session.getValue().getEmail());
        Picasso.get().load(session.getValue().getImage()).placeholder(R.drawable.user_ic).into(binding.childNavDrawer.userImg);
    }

    private void initViews() {
        placesClient = Places.createClient(this);
        PicUpMarker=new MarkerOptions().title("Pick Up Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));
        DropOffMarker=new MarkerOptions().title("Drop Off Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));
        binding.chlidDashboard.navbar.setOnClickListener(v -> {
            navmenu();
        });

        binding.childNavDrawer.btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        binding.childNavDrawer.btnHistory.setOnClickListener(v -> {
            startActivity(new Intent(this, RideHistoryActivity.class));
        });


        binding.childNavDrawer.btnPayment.setOnClickListener(v -> {
            startActivity(new Intent(this, CardAdded.class));
        });

        binding.childNavDrawer.btnPromocode.setOnClickListener(v -> {
            startActivity(new Intent(this, PromoCodeActivity.class));
        });

        binding.childNavDrawer.btnSupport.setOnClickListener(v -> {
            startActivity(new Intent(this, SupportActivity.class));
        });
        binding.childNavDrawer.btnWallet.setOnClickListener(v -> {
//            startActivity(new Intent(this, WalletAct.class));
        });

        binding.chlidDashboard.btnNext.setOnClickListener(v -> {
            if (PickUpLatLng==null){
                Toast.makeText(this, "Please select Pickup Location", Toast.LENGTH_SHORT).show();
                return;
            }
            if (DropOffLatLng==null){
                Toast.makeText(this, "Please select Dropoff Location", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent=new Intent(this, RideOptionActivity.class);
            intent.putExtra("pollyLine",lineOptions);
            intent.putExtra("PickUp",PickUpLatLng);
            intent.putExtra("DropOff",DropOffLatLng);
            startActivity(intent);
        });

        binding.chlidDashboard.tvFrom.setOnClickListener(v -> {
            SerachPlaceBottumSheetFragment.get().Callback(address -> {
                FindLatLng(1,address.getPlace_id());
            }).show(getSupportFragmentManager(),"");
        });
        binding.chlidDashboard.tvDestination.setOnClickListener(v -> {
            SerachPlaceBottumSheetFragment.get().Callback(address -> {
                FindLatLng(2,address.getPlace_id());
            }).show(getSupportFragmentManager(),"");
        });



    }

    public void navmenu() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =googleMap;
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                PicUpMarker.position(new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude()));
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PickUpLatLng)));
                if (!isAddedMarker1) {
                    mMap.addMarker(PicUpMarker);
                    isAddedMarker1 = true;
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (session.getUserID().isEmpty()){
            DialogMessage.get(this).setMessage(getString(R.string.session_expire)).Callback(()->{
                session.Logout();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
            }).show();
        }
        getProfile();
        BindExecutor();
        getNearDriver();
        getCurrentBooking();
        LocalBroadcastManager.getInstance(HomeActivity.this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        scheduleTaskExecutor.shutdownNow();
        LocalBroadcastManager.getInstance(HomeActivity.this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduleTaskExecutor.shutdownNow();
    }

    private void BindExecutor() {
        scheduleTaskExecutor = Executors.newScheduledThreadPool(5);
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                getNearDriver();
            }
        }, 0, 8, TimeUnit.MINUTES);
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        if (latLng==null) {
            latLng=new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude());
        }
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PicUpMarker.position(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()));
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PickUpLatLng)));
                if (!isAddedMarker1) {
                    isAddedMarker1 = true;
                    mMap.addMarker(PicUpMarker);
                }
            }
        }
    }

    private void getProfile(){
        HashMap<String,String> param=new HashMap<>();
        param.put("user_id",session.getUserID());
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getProfile())
                .setParam(param).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("1")){
                        session.CreateSession(object.getString("result"));
                        BindData();
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
    private void FindLatLng(int type,String placeId){
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields)
                .build();
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            Log.i(TAG, "Place found: " + place.getName());
            if (type==1){
                binding.chlidDashboard.tvFrom.setText(place.getAddress());
                PickUpLatLng=place.getLatLng();
                if (PickUpLatLng!=null){
                    PicUpMarker.position(PickUpLatLng);
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PickUpLatLng)));
                    if (!isAddedMarker1) {
                        mMap.addMarker(PicUpMarker);
                        isAddedMarker1=true;
                    }
                }
            }
            if (type==2){
                binding.chlidDashboard.tvDestination.setText(place.getAddress());
                DropOffLatLng=place.getLatLng();
                if (DropOffLatLng!=null){
                    DropOffMarker.position(DropOffLatLng);
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(DropOffLatLng)));
                    if (!isAddedMarker2) {
                        isAddedMarker2=true;
                        mMap.addMarker(DropOffMarker);
                    }
                }
            }
            if (PickUpLatLng!=null&DropOffLatLng!=null){
                DrawPolyLine();
            }
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.e(TAG, "Place not found: " + exception.getMessage());
            }
        });
    }
    private void DrawPolyLine(){
        DrawPollyLine.get(this).setOrigin(PickUpLatLng)
                .setDestination(DropOffLatLng)
                .execute(new DrawPollyLine.onPolyLineResponse() {
                    @Override
                    public void Success(ArrayList<LatLng> latLngs) {
                        mMap.clear();
                        lineOptions = new PolylineOptions();
                        lineOptions.addAll(latLngs);
                        lineOptions.width(10);
                        lineOptions.color(Color.BLUE);
                        AddDefaultMarker();
                    }
                });
    }
    public void AddDefaultMarker(){
        if (mMap!=null) {
            mMap.clear();
            if (lineOptions!=null)
                mMap.addPolyline(lineOptions);
            if (PickUpLatLng!=null) {
                PicUpMarker.position(PickUpLatLng);
                mMap.addMarker(PicUpMarker);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PickUpLatLng)));
            }
            if (DropOffLatLng!=null){
                DropOffMarker.position(DropOffLatLng);
                mMap.addMarker(DropOffMarker);
            }
        }
    }
    private void getNearDriver(){
        if (PickUpLatLng==null){
            return;
        }
        HashMap<String,String>param=new HashMap<>();
        param.put("latitude",""+PickUpLatLng.latitude);
        param.put("longitude",""+PickUpLatLng.longitude);
        param.put("user_id",session.getUserID());
        param.put("timezone", Tools.get().getTimeZone());
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getNearAllDriver())
                .setParam(param).execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("1")){
                        Type listType = new TypeToken<ArrayList<ModelAvailableDriver>>() {}.getType();
                        ArrayList<ModelAvailableDriver> drivers = new GsonBuilder().create().fromJson(object.getString("result"), listType);
                        if (mMap!=null) {
                            AddDefaultMarker();
                            for (ModelAvailableDriver driver : drivers) {
                                MarkerOptions car=new MarkerOptions()
                                        .position(new LatLng(Double.valueOf(driver.getLat()),Double.valueOf(driver.getLon())))
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_top));
                                mMap.addMarker(car);
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
    private void getCurrentBooking(){
        HashMap<String,String>param=new HashMap<>();
        param.put("user_id", session.getUserID());
        param.put("type", "USER");
        param.put("timezone", Tools.get().getTimeZone());
        ApiCallBuilder.build(this).setUrl(BaseClass.get().getCurrentBooking())
                .setParam(param).isShowProgressBar(true)
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
                                        DialogSearchingDriver.get(HomeActivity.this).Callback(HomeActivity.this).show();
                                    }else if (result.getStatus().equalsIgnoreCase("Accept")) {
                                        Intent k = new Intent(HomeActivity.this, TrackActivity.class);
                                        k.putExtra("data",data);
                                        startActivity(k);
                                    } else if (result.getStatus().equalsIgnoreCase("Arrived")) {
                                        Intent j = new Intent(HomeActivity.this, TrackActivity.class);
                                        j.putExtra("data",data);
                                        startActivity(j);
                                    } else if (result.getStatus().equalsIgnoreCase("Start")) {
                                        Intent j = new Intent(HomeActivity.this, TrackActivity.class);
                                        j.putExtra("data",data);
                                        startActivity(j);
                                    } else if (result.getStatus().equalsIgnoreCase("End")) {
                                       /* Intent j = new Intent(HomeActivity.this, PaymentSummary.class);
                                        j.putExtra("data",data);
                                        startActivity(j);*/
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
    @Override
    public void onRequestAccepted(ModelCurrentBooking data) {
        DialogMessage.get(this).setMessage("Your request accepted successfully!").Callback(()->{

        }).show();
    }

    @Override
    public void onRequestCancel() {
        DialogMessage.get(this).setMessage("Your request has been canceled.").Callback(()->finish()).show();
    }

    @Override
    public void onDriverNotFound() {
        DialogMessage.get(this).setMessage("No driver available near you!").show();
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
                    if (keyMessage.equalsIgnoreCase("your booking request is ACCEPT")) {
                        String request_id = data.getString("request_id");
                        String driver_id = data.getString("driver_id");
                    }
                    if (keyMessage.equalsIgnoreCase("your booking request is Cancel")) {
                        String request_id = data.getString("request_id");
                        onRequestCancel();
                    }
                    if (keyMessage.equalsIgnoreCase("no driver available")) {
                        String request_id = data.getString("request_id");
                        onDriverNotFound();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}