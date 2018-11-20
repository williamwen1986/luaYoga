package com.common.luakit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.common.luakit.constant.PropertyType;
import com.common.luakit.constant.ViewType;
import com.common.luakit.yoganode.YogaButton;
import com.common.luakit.yoganode.YogaFrameLayout;
import com.common.luakit.yoganode.YogaImageView;
import com.common.luakit.yoganode.YogaInterface;
import com.common.luakit.yoganode.YogaListView;
import com.common.luakit.yoganode.YogaNodeWrapper;
import com.common.luakit.yoganode.YogaOther;
import com.common.luakit.yoganode.YogaScrollView;
import com.common.luakit.yoganode.YogaTextView;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.soloader.SoLoader;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaWrap;

public class YogaView extends FrameLayout implements YogaInterface {

    private static final String TAG = "YogaView";

    /**
     * The pointer address in Jni.
     */
    private long self, parent, root;

    private Context context;

    private native long loadLua(String moduleName);

    private boolean loadSuccess = true;

    private YogaNode rootNode;

    private YogaNodeWrapper yogaNodeWrapper;

    public YogaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        rootNode = new YogaNode();
        yogaNodeWrapper = new YogaNodeWrapper(this);
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
    public boolean setYogaProperty(YogaView view, int type, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (propertyName.equals(PropertyType.YOGA_IS_ENABLE)) {
            // TODO : The property not found...
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_DIRECTION)) {
            rootNode.setFlexDirection(YogaFlexDirection.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_JUSTIFY_CONTENT)) {
            rootNode.setJustifyContent(YogaJustify.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_CONTENT)) {
            rootNode.setAlignContent(YogaAlign.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_ITEMS)) {
            rootNode.setAlignItems(YogaAlign.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_SELF)) {
            rootNode.setAlignSelf(YogaAlign.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_POSITION)) {
            rootNode.setPosition(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_WRAP)) {
            rootNode.setWrap(YogaWrap.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_OVERFLOW)) {
            rootNode.setOverflow(YogaOverflow.fromInt((int) value));
        } else if (propertyName.equals(PropertyType.YOGA_DISPLAY)) {
            // TODO : The property not found...
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_GROW)) {
            rootNode.setFlexGrow(value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_SHRINK)) {
            rootNode.setFlexShrink(value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_BASIS)) {
            rootNode.setFlexBasis(value);
        } else if (propertyName.equals(PropertyType.YOGA_LEFT)) {
            rootNode.setPosition(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_TOP)) {
            rootNode.setPosition(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_RIGHT)) {
            rootNode.setPosition(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BOTTOM)) {
            rootNode.setPosition(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_START)) {
            rootNode.setPosition(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_END)) {
            rootNode.setPosition(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_LEFT)) {
            rootNode.setMargin(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_TOP)) {
            rootNode.setMargin(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_RIGHT)) {
            rootNode.setMargin(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_BOTTOM)) {
            rootNode.setMargin(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_START)) {
            rootNode.setMargin(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_END)) {
            rootNode.setMargin(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_HORIZONTAL)) {
            rootNode.setMargin(YogaEdge.HORIZONTAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN)) {
            rootNode.setMargin(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_VERTICAL)) {
            rootNode.setMargin(YogaEdge.VERTICAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_LEFT)) {
            rootNode.setPadding(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_TOP)) {
            rootNode.setPadding(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_RIGHT)) {
            rootNode.setPadding(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_BOTTOM)) {
            rootNode.setPadding(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_START)) {
            rootNode.setPadding(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_END)) {
            rootNode.setPadding(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_HORIZONTAL)) {
            rootNode.setPadding(YogaEdge.HORIZONTAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_VERTICAL)) {
            rootNode.setPadding(YogaEdge.VERTICAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING)) {
            rootNode.setPadding(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_LEFT)) {
            rootNode.setBorder(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_TOP)) {
            rootNode.setBorder(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_RIGHT)) {
            rootNode.setBorder(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_BOTTOM)) {
            rootNode.setBorder(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_START)) {
            rootNode.setBorder(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_END)) {
            rootNode.setBorder(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER)) {
            rootNode.setBorder(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_WIDTH)) {
            rootNode.setWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_HEIGHT)) {
            rootNode.setHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_MIN_WIDTH)) {
            rootNode.setMinWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_MIN_HEIGHT)) {
            rootNode.setMinHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_MAX_WIDTH)) {
            rootNode.setMaxWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_MAX_HEIGHT)) {
            rootNode.setMaxHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_ASPECT_RATIO)) {
            rootNode.setAspectRatio(value);
        }
        return true;
    }

    @Override
    public YogaNode getYogaNode() {
        return rootNode;
    }

    public float getYogaProperty(View view, int type, String propertyName) {
        return 0.0f;
    }

    public View addYogaView(View parent, int type) {
        YogaInterface added = null;
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
            // rootNode.addChildAt(added.getYogaNode(), rootNode.getChildCount());
            // yogaNodeWrapper.addChildNode((View)added, added.getYogaNode(), );
        }
        return (View) added;
    }

    @Override
    public void setPointer(long self, long parent, long root) {
        this.self = self;
        this.parent = parent;
        this.root = root;
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

}
