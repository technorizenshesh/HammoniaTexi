package com.hammoniatexiapp.Application;

import android.app.Application;
import android.os.CountDownTimer;

import com.google.android.libraries.places.api.Places;
import com.hammoniatexiapp.Interfaces.onRefreshSchedule;
import com.hammoniatexiapp.R;

public class MyApplication extends Application {

    private onRefreshSchedule schedule;
    private CountDownTimer downTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        Places.initialize(getApplicationContext(), getResources().getString(R.string.googlekey_other));
    }

    public MyApplication update(onRefreshSchedule schedule) {
        this.schedule = schedule;
        downTimer = new CountDownTimer(5000, 50000) {
            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println("Running....");
                if (schedule != null) {
                    schedule.run();
                    System.out.println("schedule Running....");
                }
            }

            @Override
            public void onFinish() {
                downTimer.start();
            }
        };
        downTimer.start();
        return this;
    }

    public static MyApplication get() {
        return new MyApplication();
    }

}
