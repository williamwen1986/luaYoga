package com.demo.luayoga.yy.androiddemo;

import android.app.Application;

import com.common.luakit.LuaHelper;
import com.facebook.soloader.SoLoader;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LuaHelper.startLuaKit(this);
        SoLoader.init(this, false);
    }
}
