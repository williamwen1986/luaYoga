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
            methods.add(PluginUtils.class.getMethod("test", Integer.class, Boolean.class, Character.class, Long.class, Float.class, Double.class, Short.class, Byte.class)); // test(Integer a, Boolean b, Character c, Long l, Float f, Double d, Short s, Byte by)
            LuaHelper.registerCallee(methods);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        SoLoader.init(this, false);
    }
}
