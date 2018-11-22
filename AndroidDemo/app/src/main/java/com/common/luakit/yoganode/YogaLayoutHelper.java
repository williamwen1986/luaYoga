package com.common.luakit.yoganode;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.constant.PropertyType;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaWrap;

public class YogaLayoutHelper {

    private static final String TAG = "YogaLayoutHelper";

    private static YogaLayoutHelper yogaLayoutHelper;

    public static YogaLayoutHelper getInstance() {
        if(yogaLayoutHelper == null) {
            synchronized (YogaLayoutHelper.class) {
                if (yogaLayoutHelper == null) {
                    yogaLayoutHelper = new YogaLayoutHelper();
                }
            }
        }
        return yogaLayoutHelper;
    }

    public boolean setYogaProperty(YogaNode yogaNode, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (PropertyType.YOGA_IS_ENABLE.equals(propertyName)) {
            // TODO : The property not found...
        } else if (PropertyType.YOGA_FLEX_DIRECTION.equals(propertyName)) {
            yogaNode.setFlexDirection(YogaFlexDirection.fromInt((int)value));
        } else if (PropertyType.YOGA_JUSTIFY_CONTENT.equals(propertyName)) {
            yogaNode.setJustifyContent(YogaJustify.fromInt((int)value));
        } else if (PropertyType.YOGA_ALIGN_CONTENT.equals(propertyName)) {
            yogaNode.setAlignContent(YogaAlign.fromInt((int)value));
        } else if (PropertyType.YOGA_ALIGN_ITEMS.equals(propertyName)) {
            yogaNode.setAlignItems(YogaAlign.fromInt((int)value));
        } else if (PropertyType.YOGA_ALIGN_SELF.equals(propertyName)) {
            yogaNode.setAlignSelf(YogaAlign.fromInt((int)value));
        } else if (PropertyType.YOGA_POSITION.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.ALL, value);
        } else if (PropertyType.YOGA_FLEX_WRAP.equals(propertyName)) {
            yogaNode.setWrap(YogaWrap.fromInt((int)value));
        } else if (PropertyType.YOGA_OVERFLOW.equals(propertyName)) {
            yogaNode.setOverflow(YogaOverflow.fromInt((int)value));
        } else if (PropertyType.YOGA_DISPLAY.equals(propertyName)) {
            // TODO : The property not found...
        } else if (PropertyType.YOGA_FLEX_GROW.equals(propertyName)) {
            yogaNode.setFlexGrow(value);
        } else if (PropertyType.YOGA_FLEX_SHRINK.equals(propertyName)) {
            yogaNode.setFlexShrink(value);
        } else if (PropertyType.YOGA_FLEX_BASIS.equals(propertyName)) {
            yogaNode.setFlexBasis(value);
        } else if (PropertyType.YOGA_LEFT.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.LEFT, value);
        } else if (PropertyType.YOGA_TOP.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.TOP, value);
        } else if (PropertyType.YOGA_RIGHT.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.RIGHT, value);
        } else if (PropertyType.YOGA_BOTTOM.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.BOTTOM, value);
        } else if (PropertyType.YOGA_START.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.START, value);
        } else if (PropertyType.YOGA_END.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.END, value);
        } else if (PropertyType.YOGA_MARGIN_LEFT.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.LEFT, value);
        } else if (PropertyType.YOGA_MARGIN_TOP.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.TOP, value);
        } else if (PropertyType.YOGA_MARGIN_RIGHT.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.RIGHT, value);
        } else if (PropertyType.YOGA_MARGIN_BOTTOM.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.BOTTOM, value);
        } else if (PropertyType.YOGA_MARGIN_START.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.START, value);
        } else if (PropertyType.YOGA_MARGIN_END.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.END, value);
        } else if (PropertyType.YOGA_MARGIN_HORIZONTAL.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.HORIZONTAL, value);
        } else if (PropertyType.YOGA_MARGIN.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.ALL, value);
        } else if (PropertyType.YOGA_MARGIN_VERTICAL.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.VERTICAL, value);
        } else if (PropertyType.YOGA_PADDING_LEFT.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.LEFT, value);
        } else if (PropertyType.YOGA_PADDING_TOP.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.TOP, value);
        } else if (PropertyType.YOGA_PADDING_RIGHT.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.RIGHT, value);
        } else if (PropertyType.YOGA_PADDING_BOTTOM.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.BOTTOM, value);
        } else if (PropertyType.YOGA_PADDING_START.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.START, value);
        } else if (PropertyType.YOGA_PADDING_END.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.END, value);
        } else if (PropertyType.YOGA_PADDING_HORIZONTAL.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.HORIZONTAL, value);
        } else if (PropertyType.YOGA_PADDING_VERTICAL.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.VERTICAL, value);
        } else if (PropertyType.YOGA_PADDING.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.ALL, value);
        } else if (PropertyType.YOGA_BORDER_LEFT.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.LEFT, value);
        } else if (PropertyType.YOGA_BORDER_TOP.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.TOP, value);
        } else if (PropertyType.YOGA_BORDER_RIGHT.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.RIGHT, value);
        } else if (PropertyType.YOGA_BORDER_BOTTOM.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.BOTTOM, value);
        } else if (PropertyType.YOGA_BORDER_START.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.START, value);
        } else if (PropertyType.YOGA_BORDER_END.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.END, value);
        } else if (PropertyType.YOGA_BORDER.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.ALL, value);
        } else if (PropertyType.YOGA_WIDTH.equals(propertyName)) {
            yogaNode.setWidth(value);
        } else if (PropertyType.YOGA_HEIGHT.equals(propertyName)) {
            yogaNode.setHeight(value);
        } else if (PropertyType.YOGA_MIN_WIDTH.equals(propertyName)) {
            yogaNode.setMinWidth(value);
        } else if (PropertyType.YOGA_MIN_HEIGHT.equals(propertyName)) {
            yogaNode.setMinHeight(value);
        } else if (PropertyType.YOGA_MAX_WIDTH.equals(propertyName)) {
            yogaNode.setMaxWidth(value);
        } else if (PropertyType.YOGA_MAX_HEIGHT.equals(propertyName)) {
            yogaNode.setMaxHeight(value);
        } else if (PropertyType.YOGA_ASPECT_RATIO.equals(propertyName)) {
            yogaNode.setAspectRatio(value);
        }
        return true;
    }

    /**
     * recursive to inflate the layout with YogaNode.
     */
    public void inflate(IYoga root) {
        root.inflate();
    }

}
