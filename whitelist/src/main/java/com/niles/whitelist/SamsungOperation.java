package com.niles.whitelist;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

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

    private final List<Intent> mAutoLaunchIntents = new ArrayList<>();
    private final List<Intent> mAppSleepIntents = new ArrayList<>();

    SamsungOperation(@NonNull Application app) {
        super(app);
        final PackageManager packageManager = app.getPackageManager();
        {
            final List<ComponentName> componentNames = new ArrayList<ComponentName>() {{
                // 智能管理器主页面 - 应用程序管理页面 - 管理自动运行页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity"));
                // 智能管理器主页面 - 应用程序管理页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.appmanagement.AppManagementActivity"));
                // 智能管理器主页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity"));
            }};
            for (ComponentName componentName : componentNames) {
                final Intent intent = new Intent()
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .setComponent(componentName);
                final ResolveInfo resolveInfo = packageManager.resolveActivity(intent, RESOLVE_FLAG);
                if (resolveInfo != null && resolveInfo.activityInfo.exported) {
                    mAutoLaunchIntents.add(intent);
                }
            }
        }
        {
            final List<ComponentName> componentNames = new ArrayList<ComponentName>() {{
                // 智能管理器主页面 - 电池管理页面 - 休眠程序列表页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.AppSleepListActivity"));
                // 智能管理器主页面 - 电池管理页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity"));
                // 智能管理器主页面
                add(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity"));
            }};
            for (ComponentName componentName : componentNames) {
                final Intent intent = new Intent()
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .setComponent(componentName);
                final ResolveInfo resolveInfo = packageManager.resolveActivity(intent, RESOLVE_FLAG);
                if (resolveInfo != null && resolveInfo.activityInfo.exported) {
                    mAppSleepIntents.add(intent);
                }
            }
        }
    }

    @Override
    public void openAutoLaunch() throws NotSupportException {
        if (mAutoLaunchIntents.isEmpty()) {
            super.openAutoLaunch();
        } else {
            mApp.startActivity(mAutoLaunchIntents.get(0));
        }
    }

    @Override
    public void openAppSleep() throws NotSupportException {
        if (mAppSleepIntents.isEmpty()) {
            super.openAppSleep();
        } else {
            mApp.startActivity(mAppSleepIntents.get(0));
        }
    }
}
