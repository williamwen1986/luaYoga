package com.common.luakit;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.constant.PropertyType;
import com.common.luakit.constant.ViewType;
import com.common.luakit.yoganode.IYoga;
import com.common.luakit.yoganode.YogaButton;
import com.common.luakit.yoganode.YogaFrameLayout;
import com.common.luakit.yoganode.YogaImageView;
import com.common.luakit.yoganode.YogaLayoutHelper;
import com.common.luakit.yoganode.YogaListView;
import com.common.luakit.yoganode.YogaNodeWrapper;
import com.common.luakit.yoganode.YogaOther;
import com.common.luakit.yoganode.YogaScrollView;
import com.common.luakit.yoganode.YogaTextView;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaNode;

public class YogaView extends FrameLayout implements IYoga {

    private static final String TAG = "YogaView";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self;

    private Context context;

    private native long loadLua(String moduleName);

    private native void dispose(long self);

    private static boolean sLoadSuccess = false;

    private YogaNode rootNode;

    private YogaNodeWrapper yogaNodeWrapper;

    private YogaLayoutHelper yogaLayoutHelper;

    private int width, height;

    public YogaView(@NonNull Context context) {
        this(context, null);
    }

    public YogaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        rootNode = new YogaNode();
        yogaNodeWrapper = new YogaNodeWrapper(this, rootNode);
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
        loadSo();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right - left;
        height = bottom - top;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void loadSo() {
        if (sLoadSuccess) {
            return;
        }
        try {
            System.loadLibrary("luaFramework");
            sLoadSuccess = true;
        } catch (Throwable e) {
            LogUtil.i(TAG, "Load libluaFramework.so failed : " + e);
            sLoadSuccess = false;
        }
    }

    /**
     * Begin to parse and execute the lua script.
     *
     * @param moduleName The name of .lua script without suffix
     */
    public long render(String moduleName) {
        if (sLoadSuccess) {
            return loadLua(moduleName);
        }
        return 0;
    }

    @Override
    public boolean setYogaProperty(int type, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (PropertyType.YOGA_IS_ENABLE.equals(propertyName)) {
            boolean enabled = value == 1.0f;
            setEnabled(enabled);
            setClickable(enabled);
            return true;
        } else {
            return yogaLayoutHelper.setYogaProperty(rootNode, propertyName, value);
        }
    }

    @Override
    public float getYogaProperty(int type, String propertyName) {
        if (PropertyType.YOGA_WIDTH.equals(propertyName)) {
            return width;
        } else if (PropertyType.YOGA_HEIGHT.equals(propertyName)) {
            return height;
        }
        return yogaLayoutHelper.getYogaProperty(rootNode, propertyName);
    }

    @Override
    public YogaNode getYogaNode() {
        return rootNode;
    }

    public void calculateLayout() {
        int yogaWidth = (int)rootNode.getWidth().value;
        int yogaHeight = (int)rootNode.getHeight().value;
        LogUtil.i(TAG, "calculateLayout-> yogaWidth = " + yogaWidth + ", yogaHeight = " + yogaHeight);
        if (yogaWidth > 0 && yogaHeight > 0) {
            // Set the layout parameter to the Recycler item.
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.width = 1080;
            params.height = 540;
            setLayoutParams(params);
            rootNode.calculateLayout(1080, 540);
        } else {
            LogUtil.i(TAG, "getWidth() = " + getWidth() + ", getHeight() = " + getHeight());
            rootNode.calculateLayout(getWidth(), getHeight());
        }
    }

    /**
     * Called by jni.
     *
     * @param type the type of added view
     * @return the object of added view
     */
    public View addYogaView(int type) {
        LogUtil.i(TAG, "Add YogaView : " + type);
        IYoga added = null;
        switch (type) {
            case ViewType.VIEW_TYPE_CONTAINER:
                added = new YogaFrameLayout(context);
                break;
            case ViewType.VIEW_TYPE_IMAGE:
                added = new YogaImageView(context);
                break;
            case ViewType.VIEW_TYPE_TEXT:
                added = new YogaTextView(context);
                break;
            case ViewType.VIEW_TYPE_BUTTON:
                added = new YogaButton(context);
                break;
            case ViewType.VIEW_TYPE_SVGA:
                // SurfaceView
                break;
            case ViewType.VIEW_TYPE_LIST:
                added = new YogaListView(context);
                break;
            case ViewType.VIEW_TYPE_COLLECTIONVIEW:
                // GridView
                break;
            case ViewType.VIEW_TYPE_SCROLLVIEW:
                added = new YogaScrollView(context);
                break;
            case ViewType.VIEW_TYPE_OTHER:
                added = new YogaOther(context);
                break;
        }
        if (added != null) {
            yogaNodeWrapper.addChild(added);
        }
        return (View) added;
    }

    @Override
    public void setNativePointer(long self, long parent, long root) {
        // TODO : Ignore
    }

    /**
     * java and jni both calling.
     * @param self the jni pointer address.
     */
    public void setSelfPointer(long self) {
        this.self = self;
    }

    @Override
    public long getSelfPointer() {
        return self;
    }

    @Override
    public long getParentPointer() {
        return 0;
    }

    @Override
    public long getRootPointer() {
        return 0;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void inflate() {
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            yogaNodeWrapper.getChildView(i).inflate();
            View child = (View) yogaNodeWrapper.getChildView(i);
            addView(child, i);
        }
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // important! Release the native memory.
        dispose(self);
    }
}
