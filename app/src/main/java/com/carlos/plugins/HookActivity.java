package com.carlos.plugins;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carlos.plugins.util.HookStartActivityUtil;
import com.carlos.plugins.util.LogU;
import com.morgoo.droidplugin.pm.PluginManager;

import java.io.File;


public class HookActivity extends AppCompatActivity {


    private String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "plugins_demo.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        HookStartActivityUtil.init(this, ProxyActivity.class);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInstall:
                LogU.d("apkPath " + new File(apkPath).exists());
                /*PluginManager.install(this, apkPath);
                LogU.d("自定义 加载类");*/

                // 使用360
                LogU.d("360 加载类");
                try {
                    int result = PluginManager.getInstance().installPackage(apkPath, 0);
                    LogU.d("360  result "+result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btStart:
                // 启动插件，下载好了，在内存卡里
                /*LogU.d("自定义启动");
                Intent install = new Intent();
                install.setClassName(getPackageName(), "com.tianxin.plugindemo.MainActivity");
                install.putExtra("user_name", "大坤");
                startActivity(install);*/

                LogU.d("360 启动");
                // 一定要这样
                PackageManager pm =  getPackageManager();
                // 有了apk路径是可以获取apk的包名
                PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
                String packageName = info.packageName;
                Intent intent = pm.getLaunchIntentForPackage(packageName);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LogU.d(intent.toString());
                intent.putExtra("user_name","大坤");
                startActivity(intent);
                break;
            case R.id.btn_0:
                Intent intento = new Intent(this, HookTestActivity.class);
                startActivity(intento);
                break;
        }

    }
}
