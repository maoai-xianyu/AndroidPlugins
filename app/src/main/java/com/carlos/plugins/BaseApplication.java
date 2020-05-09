package com.carlos.plugins;

import android.app.Application;

import com.carlos.plugins.util.HookStartActivityUtil;

/**
 * @author zhangkun
 * @time 2020-05-09 13:56
 * @Description
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HookStartActivityUtil hookStartActivityUtil = new HookStartActivityUtil(this, ProxyActivity.class);
        try {
            hookStartActivityUtil.hookStartActivityAll();
            hookStartActivityUtil.hookLaunchActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //HookHelper.init(this);
    }
}
