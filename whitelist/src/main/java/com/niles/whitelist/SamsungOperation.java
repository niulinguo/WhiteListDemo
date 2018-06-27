package com.niles.whitelist;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Negro
 * Date 2018/6/26
 * Email niulinguo@163.com
 * <p>
 * 三星手机
 */
class SamsungOperation extends DefaultOperation {

    /**
     * 智能管理器包名
     */
    private static final String MANAGER_PACKAGE_NAME = "com.samsung.android.sm_cn";

    /**
     * 自启动设置相关页面
     */
    private static final List<String> AUTO_LAUNCH_ACTIVITY = new ArrayList<String>() {{
        // 智能管理器主页面 - 应用程序管理页面 - 管理自动运行页面
        add("com.samsung.android.sm.ui.ram.AutoRunActivity");
        // 智能管理器主页面 - 应用程序管理页面
        add("com.samsung.android.sm.ui.appmanagement.AppManagementActivity");
        // 智能管理器主页面
        add("com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity");
    }};

    /**
     * 应用休眠管理相关页面
     */
    private static final List<String> APP_SLEEP_ACTIVITY = new ArrayList<String>() {{
        // 智能管理器主页面 - 电池管理页面 - 休眠程序列表页面
        add("com.samsung.android.sm.ui.battery.AppSleepListActivity");
        // 智能管理器主页面 - 电池管理页面
        add("com.samsung.android.sm.ui.battery.BatteryActivity");
        // 智能管理器主页面
        add("com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity");
    }};

    private final List<String> mAutoLaunchActivities = new ArrayList<>();
    private final List<String> mAppSleepActivities = new ArrayList<>();

    SamsungOperation(Application app) {
        super(app);
        final PackageManager packageManager = app.getPackageManager();
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
    public void openAppSleep() throws NotSupportException {
        if (mAppSleepActivities.isEmpty()) {
            throw new NotSupportException("", null);
        }
        final Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(MANAGER_PACKAGE_NAME, mAppSleepActivities.get(0)));
        mApp.startActivity(intent);
    }
}
