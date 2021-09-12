package com.hammoniatexiapp.fragments;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hammoniatexiapp.R;
import com.hammoniatexiapp.utility.BottomSheetListener;

/**
 * Created by Ravindra Birla on 23,February,2021
 */
public class BottomSheetRateDriver extends BottomSheetDialogFragment {

    ImageView bottomSheet_cancelId;
    private BottomSheetListener bottomSheetListener;

    public static BottomSheetRateDriver newInstance() {
        BottomSheetRateDriver fragment = new BottomSheetRateDriver();
        return fragment;
    }
/*

    @Override public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
*/

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_rate_driver, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

}
