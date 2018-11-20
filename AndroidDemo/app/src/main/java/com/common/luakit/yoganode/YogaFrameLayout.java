package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.common.luakit.YogaView;
import com.common.luakit.constant.PropertyType;
import com.common.luakit.constant.ViewType;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaWrap;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaFrameLayout extends FrameLayout implements YogaInterface {

    private static final String TAG = "YogaFrameLayout";

    /**
     * The pointer address in Jni.
     */
    private long self, parent, root;

    private Context context;

    private YogaNode yogaNode;

    public YogaFrameLayout(@NonNull Context context) {
        super(context);
        yogaNode = new YogaNode();
    }

    @Override
    public boolean setYogaProperty(YogaView view, int type, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (propertyName.equals(PropertyType.YOGA_IS_ENABLE)) {
            // TODO : The property not found...
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_DIRECTION)) {
            yogaNode.setFlexDirection(YogaFlexDirection.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_JUSTIFY_CONTENT)) {
            yogaNode.setJustifyContent(YogaJustify.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_CONTENT)) {
            yogaNode.setAlignContent(YogaAlign.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_ITEMS)) {
            yogaNode.setAlignItems(YogaAlign.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_ALIGN_SELF)) {
            yogaNode.setAlignSelf(YogaAlign.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_POSITION)) {
            yogaNode.setPosition(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_WRAP)) {
            yogaNode.setWrap(YogaWrap.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_OVERFLOW)) {
            yogaNode.setOverflow(YogaOverflow.fromInt((int)value));
        } else if (propertyName.equals(PropertyType.YOGA_DISPLAY)) {
            // TODO : The property not found...
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_GROW)) {
            yogaNode.setFlexGrow(value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_SHRINK)) {
            yogaNode.setFlexShrink(value);
        } else if (propertyName.equals(PropertyType.YOGA_FLEX_BASIS)) {
            yogaNode.setFlexBasis(value);
        } else if (propertyName.equals(PropertyType.YOGA_LEFT)) {
            yogaNode.setPosition(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_TOP)) {
            yogaNode.setPosition(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_RIGHT)) {
            yogaNode.setPosition(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BOTTOM)) {
            yogaNode.setPosition(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_START)) {
            yogaNode.setPosition(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_END)) {
            yogaNode.setPosition(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_LEFT)) {
            yogaNode.setMargin(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_TOP)) {
            yogaNode.setMargin(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_RIGHT)) {
            yogaNode.setMargin(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_BOTTOM)) {
            yogaNode.setMargin(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_START)) {
            yogaNode.setMargin(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_END)) {
            yogaNode.setMargin(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_HORIZONTAL)) {
            yogaNode.setMargin(YogaEdge.HORIZONTAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN)) {
            yogaNode.setMargin(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_MARGIN_VERTICAL)) {
            yogaNode.setMargin(YogaEdge.VERTICAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_LEFT)) {
            yogaNode.setPadding(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_TOP)) {
            yogaNode.setPadding(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_RIGHT)) {
            yogaNode.setPadding(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_BOTTOM)) {
            yogaNode.setPadding(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_START)) {
            yogaNode.setPadding(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_END)) {
            yogaNode.setPadding(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_HORIZONTAL)) {
            yogaNode.setPadding(YogaEdge.HORIZONTAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING_VERTICAL)) {
            yogaNode.setPadding(YogaEdge.VERTICAL, value);
        } else if (propertyName.equals(PropertyType.YOGA_PADDING)) {
            yogaNode.setPadding(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_LEFT)) {
            yogaNode.setBorder(YogaEdge.LEFT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_TOP)) {
            yogaNode.setBorder(YogaEdge.TOP, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_RIGHT)) {
            yogaNode.setBorder(YogaEdge.RIGHT, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_BOTTOM)) {
            yogaNode.setBorder(YogaEdge.BOTTOM, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_START)) {
            yogaNode.setBorder(YogaEdge.START, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER_END)) {
            yogaNode.setBorder(YogaEdge.END, value);
        } else if (propertyName.equals(PropertyType.YOGA_BORDER)) {
            yogaNode.setBorder(YogaEdge.ALL, value);
        } else if (propertyName.equals(PropertyType.YOGA_WIDTH)) {
            yogaNode.setWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_HEIGHT)) {
            yogaNode.setHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_MIN_WIDTH)) {
            yogaNode.setMinWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_MIN_HEIGHT)) {
            yogaNode.setMinHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_MAX_WIDTH)) {
            yogaNode.setMaxWidth(value);
        } else if (propertyName.equals(PropertyType.YOGA_MAX_HEIGHT)) {
            yogaNode.setMaxHeight(value);
        } else if (propertyName.equals(PropertyType.YOGA_ASPECT_RATIO)) {
            yogaNode.setAspectRatio(value);
        }
        return true;
    }

    @Override
    public YogaNode getYogaNode() {
        return yogaNode;
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
