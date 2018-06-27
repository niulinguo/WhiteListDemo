package com.niles.whitelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.niles.whitelist.NotSupportException;
import com.niles.whitelist.Operation;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity implements Operation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void openSettings() {
        try {
            MyApp.getWhiteListManager().openSettings();
        } catch (NotSupportException e) {
            e.printStackTrace();
            CrashReport.postCatchedException(e);
        }
    }

    @Override
    public void openAutoLaunch() {
        try {
            MyApp.getWhiteListManager().openAutoLaunch();
        } catch (NotSupportException e) {
            e.printStackTrace();
            CrashReport.postCatchedException(e);
        }
    }

    @Override
    public void openAppSleep() {
        try {
            MyApp.getWhiteListManager().openAppSleep();
        } catch (NotSupportException e) {
            e.printStackTrace();
            CrashReport.postCatchedException(e);
        }
    }

    @Override
    public String getInfo() {
        return MyApp.getWhiteListManager().getInfo();
    }

    public void openAutoLaunchClicked(View view) {
        openAutoLaunch();
    }

    public void openAppSleepClicked(View view) {
        openAppSleep();
    }

    public void openSettingsClicked(View view) {
        openSettings();
    }
}
