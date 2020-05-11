package com.carlos.plugins.plugin;

import android.content.Context;

/**
 * @author zhangkun
 * @time 2020-05-11 10:54
 * @Description
 */
public class PluginManager {

    public static void install(Context context, String apkPath) {
        try {
            // 解决类加载的问题
            FixDexManagerUtil fixDexManager = new FixDexManagerUtil(context);
            // 把apk的class加载到applicationClassloader中
            fixDexManager.fixDex(apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
