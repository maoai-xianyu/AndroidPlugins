package com.carlos.plugins;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carlos.plugins.plugin.PluginManager;
import com.carlos.plugins.util.HookStartActivityUtil;
import com.carlos.plugins.util.LogU;

import java.io.File;


public class HookActivity extends AppCompatActivity {


    private String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "plugins_demo.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        HookStartActivityUtil.init(this,ProxyActivity.class);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInstall:
                LogU.d("apkPath "+new File(apkPath).exists());
                PluginManager.install(this, apkPath);
                LogU.d("加载类");
                break;
            case R.id.btStart:
                // 启动插件，下载好了，在内存卡里
                Intent install = new Intent();
                install.setClassName(getPackageName(), "com.tianxin.plugindemo.MainActivity");
                install.putExtra("user_name","大坤");
                startActivity(install);
                LogU.d("启动");
                break;
            case R.id.btn_0:
                Intent intent = new Intent(this, HookTestActivity.class);
                startActivity(intent);
                break;
        }

    }
}
