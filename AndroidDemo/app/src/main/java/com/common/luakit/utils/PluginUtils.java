package com.common.luakit.utils;


public class PluginUtils {

    private static final String TAG = "PluginUtils";

    public static void goFlutter(String moduleName, String version, String type, String url) { // String needRoot
        LogUtil.i(TAG, "------>>> goFlutter : moduleName = " + moduleName + ", version = " + version + ", type = " + type + ", url = " + url);
    }

    public static void test(Integer a, Boolean b, Character c, Long l, Float f, Double d, Short s, Byte by) {
        LogUtil.i(TAG, String.format("test [a:%s][b:%s][c:%s][l:%s][f:%s][d:%s][s:%s][by:%s][", a.toString(), b.toString(), c.toString(), l.toString(), f.toString(), d.toString(), s.toString(), by.toString()));
    }

}