package com.niles.whitelistdemo;

import android.app.Application;

import com.niles.whitelist.WhiteListManager;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Niles
 * Date 2018/6/27
 * Email niulinguo@163.com
 */
public class MyApp extends Application {

    private static WhiteListManager sWhiteListManager;

    public static WhiteListManager getWhiteListManager() {
        return sWhiteListManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "3b7dad9631", true);

        sWhiteListManager = WhiteListManager.getInstance(this);
        BuglyLog.e("phoneInfo", sWhiteListManager.getPhoneInfo());
    }
}
