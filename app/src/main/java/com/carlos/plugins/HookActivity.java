package com.carlos.plugins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carlos.plugins.util.HookStartActivityUtil;


public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        HookStartActivityUtil.init(this,ProxyActivity.class);
    }

    public void onClick(View v){
        Intent intent = new Intent(this, HookTestActivity.class);
        startActivity(intent);
    }
}
