package com.common.luakit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class YogaView extends FrameLayout {

    private static final String TAG = "YogaView";

    private native void loadLua(String moduleName);

    private boolean loadSuccess = true;

    public YogaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadSo();
    }

    private void loadSo() {
        try {
            System.loadLibrary("luaFramework");
        } catch (Throwable e) {
            LogUtil.i(TAG, "Load libluaFramework.so failed : " + e);
            loadSuccess = false;
        }
    }

    /**
     * Begin to parse and execute the lua script.
     *
     * @param moduleName The name of .lua script without suffix
     */
    public void render(String moduleName) {
        if (loadSuccess) {
            loadLua(moduleName);
        }
    }

}
