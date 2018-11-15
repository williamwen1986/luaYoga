package com.common.luakit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class YogaView extends FrameLayout {

    private static final String TAG = "YogaView";

    private Context context;

    private native void loadLua(String moduleName);

    private boolean loadSuccess = true;

    public YogaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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

    public boolean setYogaProperty(View view, int type, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (propertyName.equals(PropertyType.YOGA_IS_ENABLE)) {

        } else if (propertyName.equals(PropertyType.YOGA_FLEX_DIRECTION)) {

        } else if (propertyName.equals(PropertyType.YOGA_JUSTIFY_CONTENT)) {

        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_CONTENT)) {

        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_ITEMS)) {

        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_SELF)) {

        } else if (propertyName.equals(PropertyType.YOGA_POSITION)) {

        } else if (propertyName.equals(PropertyType.YOGA_FLEX_WRAP)) {

        } else if (propertyName.equals(PropertyType.YOGA_OVERFLOW)) {

        } else if (propertyName.equals(PropertyType.YOGA_DISPLAY)) {

        } else if (propertyName.equals(PropertyType.YOGA_FLEX_GROW)) {

        } else if (propertyName.equals(PropertyType.YOGA_FLEX_SHRINK)) {

        } else if (propertyName.equals(PropertyType.YOGA_FLEX_BASIS)) {

        } else if (propertyName.equals(PropertyType.YOGA_LEFT)) {

        } else if (propertyName.equals(PropertyType.YOGA_TOP)) {

        } else if (propertyName.equals(PropertyType.YOGA_RIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_BOTTOM)) {

        } else if (propertyName.equals(PropertyType.YOGA_START)) {

        } else if (propertyName.equals(PropertyType.YOGA_END)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_LEFT)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_TOP)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_RIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_BOTTOM)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_START)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_END)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_HORIZONTAL)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN)) {

        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_VERTICAL)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_LEFT)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_TOP)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_RIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_BOTTOM)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_START)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_END)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_HORIZONTAL)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING_VERTICAL)) {

        } else if (propertyName.equals(PropertyType.YOGA_PADDING)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_LEFT)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_TOP)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_RIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_BOTTOM)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_START)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER_END)) {

        } else if (propertyName.equals(PropertyType.YOGA_BORDER)) {

        } else if (propertyName.equals(PropertyType.YOGA_WIDTH)) {

        } else if (propertyName.equals(PropertyType.YOGA_HEIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_MIN_WIDTH)) {

        } else if (propertyName.equals(PropertyType.YOGA_MIN_HEIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_MAX_WIDTH)) {

        } else if (propertyName.equals(PropertyType.YOGA_MAX_HEIGHT)) {

        } else if (propertyName.equals(PropertyType.YOGA_ASPECT_RATIO)) {

        }
        return true;
    }

    public float getYogaProperty(View view, int type, String propertyName) {
        return 0.0f;
    }

    public View addSubView(View parent, int type) {
        View added = null;
        switch (type) {
            case ViewType.VIEW_TYPE_CONTAINER:
                added = new FrameLayout(context);
                break;
            case ViewType.VIEW_TYPE_IMAGE:
                added = new ImageView(context);
                break;
            case ViewType.VIEW_TYPE_TEXT:
                added = new TextView(context);
                break;
            case ViewType.VIEW_TYPE_BUTTON:
                added = new Button(context);
                break;
            case ViewType.VIEW_TYPE_SVGA:
                // TODO:
                break;
            case ViewType.VIEW_TYPE_LIST:
                added = new ListView(context);
                break;
            case ViewType.VIEW_TYPE_COLLECTIONVIEW:
                // TODO:
                break;
            case ViewType.VIEW_TYPE_SCROLLVIEW:
                added = new ScrollView(context);
                break;
            case ViewType.VIEW_TYPE_OTHER:
                added = new View(context);
                break;
        }
        return added;
    }

}
