package com.niles.whitelist;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Negro
 * Date 2018/6/27
 * Email niulinguo@163.com
 */
class OppoOperation extends DefaultOperation {

    private final List<Intent> mAutoLaunchIntents = new ArrayList<>();
    private final List<Intent> mAppSleepIntents = new ArrayList<>();

    OppoOperation(Application app) {
        super(app);
        final PackageManager packageManager = app.getPackageManager();
        {
            final List<ComponentName> componentNames = new ArrayList<ComponentName>() {{
                add(ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity"));
                // 手机管家主页面 - 权限隐私页面 - 自启动管理页面
                add(ComponentName.unflattenFromString("com.coloros.safecenter/.startupapp.StartupAppListActivity"));
                // 手机管家主页面 - 权限隐私页面
                add(new ComponentName("com.coloros.safecenter", "com.coloros.privacypermissionsentry.PermissionTopActivity"));
                // 手机管家主页面
                add(ComponentName.unflattenFromString("com.coloros.safecenter/.MainActivity"));
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
                // 电池页面 - 耗电保护页面 - 应用详情页面
                add(new ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerAppsBgSetting"));
                // 电池页面 - 耗电保护页面
                add(new ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"));
                // 电池页面
                add(new ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"));
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
