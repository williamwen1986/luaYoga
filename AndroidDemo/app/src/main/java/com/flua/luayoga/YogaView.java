package com.flua.luayoga;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.common.luakit.LuaHelper;
import com.flua.luayoga.constant.PropertyType;
import com.flua.luayoga.constant.ViewType;
import com.flua.luayoga.utils.FileUtils;
import com.flua.luayoga.yoganode.IYoga;
import com.flua.luayoga.yoganode.YogaButton;
import com.flua.luayoga.yoganode.YogaFrameLayout;
import com.flua.luayoga.yoganode.YogaImageView;
import com.flua.luayoga.yoganode.YogaLayoutHelper;
import com.flua.luayoga.yoganode.YogaListView;
import com.flua.luayoga.yoganode.YogaNodeWrapper;
import com.flua.luayoga.yoganode.YogaOther;
import com.flua.luayoga.yoganode.YogaScrollView;
import com.flua.luayoga.yoganode.YogaTextView;
import com.flua.luayoga.utils.LogUtil;
import com.facebook.yoga.YogaNode;

import java.io.File;

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

//    private boolean hasInflate = false;

    private YogaTransition mYogaTransition;

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
            System.loadLibrary("luaYoga");
            sLoadSuccess = true;
        } catch (Throwable e) {
            LogUtil.i(TAG, "Load libluaYoga.so failed : " + e);
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
            LogUtil.i(TAG, "Enabled  = " + enabled);
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
            addView((View) added);
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
        LogUtil.i(TAG, "The address of the view is : " + this);
//        if (hasInflate) {
            for (int i = 0; i < rootNode.getChildCount(); i++) {
                yogaNodeWrapper.getChildView(i).inflate();
            }
//            return;
//        }
//        for (int i = 0; i < rootNode.getChildCount(); i++) {
//            yogaNodeWrapper.getChildView(i).inflate();
//            View child = (View) yogaNodeWrapper.getChildView(i);
//            addView(child, i);
//            hasInflate = true;
//        }
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    @Override
    public void nativeAddTapGesture() {

    }

    @Override
    public void nativeAddLongPressGesture() {

    }

    @Override
    public boolean removeFromParent() {
        LogUtil.i(TAG, "removeFromParent");
        ViewParent parent = getParent();

        if (parent != null && parent instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) parent;
            vp.removeView(this);
            yogaNodeWrapper.removeChild(this);
            rootNode.reset();
            dispose(self);
        }
        return true;
    }

    @Override
    public void reloadYoga() {
        calculateLayout();
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            yogaNodeWrapper.getChildView(i).inflate();
        }
    }

    public void releaseNativeMemory() {
        LogUtil.i(TAG, "releaseNativeMemory");
        dispose(self);
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        if (view instanceof IYoga) {
            LogUtil.i(TAG, "removeView yoga node");
            yogaNodeWrapper.removeChild((IYoga) view);
        }
    }

    /**
     * Called by jni
     * @param module
     * @param luaString
     * @param title
     */
    public void goYogaModule(String module, String luaString, String title) {
        LogUtil.i(TAG, "goYogaModule");

        String moduleToLoad = "";
        if (!TextUtils.isEmpty(module)) {
            moduleToLoad = module;
        } else if (!TextUtils.isEmpty(luaString)) { // save lua string to file
            String luaDir = LuaHelper.getLuaPath(getContext());
            String tempLuaFileName = "temp_" + System.currentTimeMillis() + ".lua";
            File tempLuaFile = new File(luaDir, tempLuaFileName);
            FileUtils.StringToFile(luaString, tempLuaFile);
            if (tempLuaFile.exists()) {
                moduleToLoad = tempLuaFileName;
            }
        }

        if (!moduleToLoad.isEmpty() && mYogaTransition != null) {
            mYogaTransition.toYoga(moduleToLoad, title);
        }
    }

    public void setYogaTransition(YogaTransition yogaTransition) {
        mYogaTransition = yogaTransition;
    }
}
