package com.niles.whitelist;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by Negro
 * Date 2018/6/26
 * Email niulinguo@163.com
 */
class DefaultOperation implements Operation {

    //    static final int RESOLVE_FLAG = PackageManager.GET_RESOLVED_FILTER;
    static final int RESOLVE_FLAG = 0;

    final Application mApp;

    DefaultOperation(Application app) {
        mApp = app;
    }

    @Override
    public void openSettings() throws NotSupportException {
        final Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        final ResolveInfo resolveInfo = mApp.getPackageManager().resolveActivity(intent, RESOLVE_FLAG);
        if (resolveInfo != null && resolveInfo.activityInfo.exported) {
            mApp.startActivity(intent);
        } else {
            throw new NotSupportException(getClass().getSimpleName(), null);
        }
    }

    @Override
    public void openAutoLaunch() throws NotSupportException {
        final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setData(Uri.fromParts("package", mApp.getPackageName(), null));
        final ResolveInfo resolveInfo = mApp.getPackageManager().resolveActivity(intent, RESOLVE_FLAG);
        if (resolveInfo != null && resolveInfo.activityInfo.exported) {
            mApp.startActivity(intent);
        } else {
            openSettings();
        }
    }

    @Override
    public void openAppSleep() throws NotSupportException {
        final Intent intent = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        final ResolveInfo resolveInfo = mApp.getPackageManager().resolveActivity(intent, RESOLVE_FLAG);
        if (resolveInfo != null && resolveInfo.activityInfo.exported) {
            mApp.startActivity(intent);
        } else {
            openSettings();
        }
    }

    @Override
    public String getInfo() {
        return new NotSupportException(getClass().getSimpleName(), null).getMessage();
    }
}
