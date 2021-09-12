package com.hammoniatexiapp.fragments;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hammoniatexiapp.Interfaces.onAddressSelectListener;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.adapters.AutoCompleteAddressAdapter;
import com.hammoniatexiapp.databinding.FragmentDialogLocationSearchBinding;
import com.hammoniatexiapp.pojos.ModelAutoAddress;
import com.hammoniatexiapp.utility.GPSTracker;
import com.utils.Session.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import www.develpoeramit.mapicall.ApiCallBuilder;


public class SerachPlaceBottumSheetFragment extends
        BottomSheetDialogFragment
        implements onAddressSelectListener {

    private FragmentDialogLocationSearchBinding binding;
    private ArrayList<ModelAutoAddress>arrayList=new ArrayList<>();
    private GPSTracker gps;
    private AutoCompleteAddressAdapter adapter;
    private SessionManager session;
    private BottomSheetBehavior<View> mBehavior;
    private onAddressSelectListener listener;

    public static SerachPlaceBottumSheetFragment get() {
        return new SerachPlaceBottumSheetFragment();
    }
    @Override public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
    public SerachPlaceBottumSheetFragment Callback(onAddressSelectListener listener){
        this.listener=listener;
        return this;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog=(BottomSheetDialog)super.onCreateDialog(savedInstanceState);
        binding= DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_dialog_location_search, null,false);
        dialog.setContentView(binding.getRoot());
        mBehavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        BindView();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    private void BindView() {
        gps=new GPSTracker(getContext());
        session= SessionManager.get(getContext());
        adapter=new AutoCompleteAddressAdapter(getContext(),arrayList,this);
        binding.recycleView.setAdapter(adapter);
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count%2==0){
                    getAddress(s.toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getAddress(session.getLastSearch());
    }

    private void getAddress(String address){
        session.setLastSearch(address);
        String key=getResources().getString(R.string.googlekey_other);
        String URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key="+key+"&input="
                + address + "&location=" + gps.getLatitude() + "," + gps.getLongitude() + "+&radius=1000&types=geocode&sensor=true";
        ApiCallBuilder.build(getContext())
                .setUrl(URL)
                .execute(new ApiCallBuilder.onResponse() {
            @Override
            public void Success(String response) {
                Log.e("ArrayData","=====>"+response);
                try {
                    arrayList.clear();
                    JSONObject jk = new JSONObject(response);
                    JSONArray predictions = jk.getJSONArray("predictions");
                    for (int i = 0; i < predictions.length(); i++) {
                        JSONObject js = predictions.getJSONObject(i);
                        ModelAutoAddress address=new ModelAutoAddress();
                        address.setDescription(js.getString("description"));
                        address.setPlace_id(js.getString("place_id"));
                        address.setTitle(js.getJSONObject("structured_formatting").getString("main_text"));
                        arrayList.add(address);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Failed(String error) {

            }
        });
    }

    @Override
    public void onSelectedAddress(ModelAutoAddress address) {
        listener.onSelectedAddress(address);
        dismiss();
    }

}
