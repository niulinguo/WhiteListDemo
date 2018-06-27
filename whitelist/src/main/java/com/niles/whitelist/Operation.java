package com.niles.whitelist;

/**
 * Created by Negro
 * Date 2018/6/26
 * Email niulinguo@163.com
 */
public interface Operation {

    /**
     * 打开自启动管理页面
     *
     * @throws NotSupportException 不支持的方法
     */
    void openAutoLaunch() throws NotSupportException;

    /**
     * 打开休眠管理页面
     *
     * @throws NotSupportException 不支持的方法
     */
    void openAppSleep() throws NotSupportException;

    /**
     * 获取手机信息
     */
    String getInfo();
}
