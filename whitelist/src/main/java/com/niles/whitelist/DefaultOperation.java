package com.niles.whitelist;

import android.app.Application;

/**
 * Created by Negro
 * Date 2018/6/26
 * Email niulinguo@163.com
 */
class DefaultOperation implements Operation {

    final Application mApp;

    DefaultOperation(Application app) {
        mApp = app;
    }

    @Override
    public void openAutoLaunch() throws NotSupportException {
        throw new NotSupportException(getClass().getSimpleName(), null);
    }

    @Override
    public void openAppSleep() throws NotSupportException {
        throw new NotSupportException(getClass().getSimpleName(), null);
    }

    @Override
    public String getInfo() {
        return new NotSupportException(getClass().getSimpleName(), null).getMessage();
    }
}
