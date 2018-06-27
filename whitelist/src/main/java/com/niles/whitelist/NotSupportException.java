package com.niles.whitelist;

import android.os.Build;

/**
 * Created by Negro
 * Date 2018/6/27
 * Email niulinguo@163.com
 */
public class NotSupportException extends Exception {

    NotSupportException(String message, Throwable cause) {
        super(
                message + "\n" +
                        "MANUFACTURER:" + Build.MANUFACTURER + "," +
                        "MODEL:" + Build.MODEL + "," +
                        "SDK_INT:" + Build.VERSION.SDK_INT + "," +
                        "RELEASE:" + Build.VERSION.RELEASE
                , cause
        );
    }
}
