package com.niles.whitelist;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Negro
 * Date 2018/6/27
 * Email niulinguo@163.com
 */
class OppoOperation extends DefaultOperation {

    /**
     * 手机管家包名
     */
    private static final String MANAGER_PACKAGE_NAME = "com.coloros.safecenter";
    private static final String GUARADE_PACKAGE_NAME = "com.coloros.oppoguardelf";

    /**
     * 自启动设置相关页面
     */
    private static final List<String> AUTO_LAUNCH_ACTIVITY = new ArrayList<String>() {{
        // 手机管家主页面 - 权限隐私页面 - 自启动管理页面
        add(MANAGER_PACKAGE_NAME + ".startupapp.StartupAppListActivity");
        // 手机管家主页面 - 权限隐私页面
        add("com.coloros.privacypermissionsentry.PermissionTopActivity");
        // 手机管家主页面
        add(MANAGER_PACKAGE_NAME + ".MainActivity");
    }};

    /**
     * 耗电保护相关页面
     */
    private static final List<String> APP_SLEEP_ACTIVITY = new ArrayList<String>() {{
        // 电池页面 - 耗电保护页面 - 应用详情页面
        add("com.coloros.powermanager.fuelgaue.PowerAppsBgSetting");
        // 电池页面 - 耗电保护页面
        add("com.coloros.powermanager.fuelgaue.PowerUsageModelActivity");
        // 电池页面
        add("com.coloros.powermanager.fuelgaue.PowerConsumptionActivity");
    }};

    private final List<String> mAutoLaunchActivities = new ArrayList<>();
    private final List<String> mAppSleepActivities = new ArrayList<>();

    OppoOperation(Application app) {
        super(app);
        final PackageManager packageManager = app.getPackageManager();
        {
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(MANAGER_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            final ActivityInfo[] activities = packageInfo == null ? null : packageInfo.activities;
            // 遍历出自启动设置页面
            if (activities != null) {
                for (String supportActivityName : AUTO_LAUNCH_ACTIVITY) {
                    for (ActivityInfo activity : activities) {
                        if (supportActivityName.equals(activity.name)) {
                            if (activity.exported) {
                                mAutoLaunchActivities.add(activity.name);
                            }
                            break;
                        }
                    }
                }
            }
        }
        {
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(GUARADE_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            final ActivityInfo[] activities = packageInfo == null ? null : packageInfo.activities;
            // 遍历出应用休眠设置页面
            if (activities != null) {
                for (String supportActivityName : APP_SLEEP_ACTIVITY) {
                    for (ActivityInfo activity : activities) {
                        if (supportActivityName.equals(activity.name)) {
                            if (activity.exported) {
                                mAppSleepActivities.add(activity.name);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void openAutoLaunch() throws NotSupportException {
        if (mAutoLaunchActivities.isEmpty()) {
            throw new NotSupportException("", null);
        }
        final Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(MANAGER_PACKAGE_NAME, mAutoLaunchActivities.get(0)));
        mApp.startActivity(intent);
    }

    @Override
    public void openAppSleep() {
        if (mAppSleepActivities.isEmpty()) {
            final Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApp.startActivity(intent);
        } else {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName(GUARADE_PACKAGE_NAME, mAppSleepActivities.get(0)));
            mApp.startActivity(intent);
        }
    }
}
