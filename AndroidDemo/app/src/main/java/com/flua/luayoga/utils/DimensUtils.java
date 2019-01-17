package com.flua.luayoga.utils;

import android.content.Context;


/**
 */
public class DimensUtils {
    public static float DENSITY;

    public static void setDensity(Context context) {
        DENSITY = context.getResources().getDisplayMetrics().density;
    }

    public static float dp2px(float dpValue) {
        return (dpValue * DENSITY + 0.5f);
    }

    public static int dip2pixel(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        return (int) (pxValue / DENSITY + 0.5f);
    }

    public static int sp2pixel(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int pixel2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
