package com.common.luakit;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

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
    private long self, parent, root = -1;

    private Context context;

    private native long loadLua(String moduleName);

    private boolean loadSuccess = true;

    private boolean isRoot;

    private YogaNode rootNode;

    private YogaNodeWrapper yogaNodeWrapper;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        rootNode = new YogaNode();
        yogaNodeWrapper = new YogaNodeWrapper(this, rootNode);
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
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
    public long render(String moduleName) {
        if (loadSuccess) {
            return loadLua(moduleName);
        }
        return 0;
    }

    @Override
    public boolean setYogaProperty(int type, String propertyName, float value) {
        return yogaLayoutHelper.setYogaProperty(rootNode, propertyName, value);
    }

    @Override
    public YogaNode getYogaNode() {
        return rootNode;
    }

    public float getYogaProperty(View view, int type, String propertyName) {
        return 0.0f;
    }

    /**
     * Called by jni.
     * @param type the type of added view
     * @return the object of added view
     */
    public View addYogaView(int type) {
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
                // TODO: Which widget in Android?
                break;
            case ViewType.VIEW_TYPE_LIST:
                added = new YogaListView(context);
                break;
            case ViewType.VIEW_TYPE_COLLECTIONVIEW:
                // TODO: Which widget in Android?
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
        this.self = self;
        this.parent = parent;
        this.root = root;
        LogUtil.i(TAG, "The self = " + self + ", parent = " + parent + ", root = " + root);
    }

    @Override
    public long getSelfPointer() {
        return self;
    }

    @Override
    public long getParentPointer() {
        return parent;
    }

    @Override
    public long getRootPointer() {
        return root;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
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

}
