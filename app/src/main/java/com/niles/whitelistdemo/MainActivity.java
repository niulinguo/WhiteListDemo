package com.niles.whitelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.niles.whitelist.NotSupportException;
import com.niles.whitelist.Operation;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity implements Operation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        openAutoLaunch();
        openAppSleep();
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
}
