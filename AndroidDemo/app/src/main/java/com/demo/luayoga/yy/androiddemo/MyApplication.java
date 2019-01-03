package com.demo.luayoga.yy.androiddemo;

import android.app.Application;

import com.common.luakit.LuaHelper;
import com.common.luakit.utils.PluginUtils;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LuaHelper.startLuaKit(this);
        List<Method> methods = new ArrayList<>();
        try {
            methods.add(PluginUtils.class.getMethod("goFlutter", String.class, String.class, String.class, String.class)); // goFlutter(String moduleName, String version, String type, String url)
            methods.add(PluginUtils.class.getMethod("test", int.class, boolean.class, char.class, long.class, float.class, double.class, short.class, byte.class, PluginUtils.class)); // test(int a, boolean b, char c, long l, float f, double d, short s, byte by, PluginUtils pluginUtils)
            LuaHelper.registerCallee(methods);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        SoLoader.init(this, false);
    }
}
