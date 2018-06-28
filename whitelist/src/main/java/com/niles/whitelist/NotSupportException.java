package com.niles.whitelist;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Niles
 * Date 2018/6/27
 * Email niulinguo@163.com
 */
public class NotSupportException extends Exception {

    NotSupportException(@NonNull Class<? extends DefaultOperation> operationClass, @Nullable Throwable cause) {
        super(
                operationClass.getSimpleName() + "\n" +
                        Build.MANUFACTURER + "(" + Build.MODEL + ")\n" +
                        "Android " + Build.VERSION.RELEASE + "(" + Build.VERSION.SDK_INT + ")"
                , cause
        );
    }
}
