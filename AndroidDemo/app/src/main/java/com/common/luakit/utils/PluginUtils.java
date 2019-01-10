package com.common.luakit.utils;


import java.util.HashMap;
import java.util.Set;

public class PluginUtils {

    private static final String TAG = "PluginUtils";

    public static void goFlutter(String moduleName, String version, String type, String url) { // String needRoot
        LogUtil.i(TAG, "------>>> goFlutter : moduleName = " + moduleName + ", version = " + version + ", type = " + type + ", url = " + url);
    }

    public static String test(String s, Integer a, Boolean b, Double d, HashMap map) {
        LogUtil.i(TAG, String.format("test [s:%s][a:%s][b:%s][d:%s]", s, a.toString(), b.toString(), d.toString()));
        Set set = map.keySet();
        for (Object obj : set) {
            LogUtil.i(TAG, String.format("test hashmap [key:%s][value:%s]", obj.toString(), map.get(obj)));
        }
        return "click to jump yoga";
    }

    public static void goYoga(String moduleName) {
        LogUtil.i(TAG, "------>>> goYoga : moduleName = " + moduleName);

    }

}