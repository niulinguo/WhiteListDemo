package com.niles.whitelist;

import android.app.Application;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Created by Negro
 * Date 2018/6/26
 * Email niulinguo@163.com
 */
public final class WhiteListManager implements Operation {

    private static WhiteListManager sInstance;
    @NonNull
    private final Operation mOperation;

    private WhiteListManager(@NonNull Application app) {
        final String manufacturer = Build.MANUFACTURER;
        switch (manufacturer) {
            case "samsung": {
                mOperation = new SamsungOperation(app);
                break;
            }
            case "OPPO": {
                mOperation = new OppoOperation(app);
                break;
            }
            default: {
                mOperation = new DefaultOperation(app);
            }
        }
    }

    @NonNull
    public static WhiteListManager getInstance(@NonNull Application app) {
        if (sInstance == null) {
            synchronized (WhiteListManager.class) {
                if (sInstance == null) {
                    sInstance = new WhiteListManager(app);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void openAutoLaunch() throws NotSupportException {
        mOperation.openAutoLaunch();
    }

    @Override
    public void openAppSleep() throws NotSupportException {
        mOperation.openAppSleep();
    }

    @Override
    public String getInfo() {
        return mOperation.getInfo();
    }
}
