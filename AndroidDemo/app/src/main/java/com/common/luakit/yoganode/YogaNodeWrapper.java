package com.common.luakit.yoganode;

import android.view.View;

import com.facebook.yoga.YogaNode;

/**
 * The class wrapper the View object would to be handled in Yoga.
 */
public class YogaNodeWrapper extends YogaNode {

    private View nodeView;

    private static final String TAG = "YogaNodeWrapper";

    /*static {
        try {
            System.loadLibrary("luaFramework");
        } catch (Throwable e) {
            LogUtil.i(TAG, "Load libluaFramework.so failed : " + e);
        }
    }*/

    private YogaNodeWrapper(View nodeView) {
        this.nodeView = nodeView;
    }



}
