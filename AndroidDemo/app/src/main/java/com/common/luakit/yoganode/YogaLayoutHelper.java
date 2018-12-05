package com.common.luakit.yoganode;

import com.common.luakit.utils.DimensUtils;
import com.common.luakit.YogaView;
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

    public native int getItemcount(int group, long hostView, long rootView);

    public native String getItemViewType(long hostView, long rootView, int position);

    public native void onCreateView(long hostView, long rootView, YogaView contentView);

    public native void onBindView(long hostView, long rootView, long contentView, int position);

    public native float getItemHeight(long hostView, int position);

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
        if (PropertyType.YOGA_FLEX_DIRECTION.equals(propertyName)) {
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
            yogaNode.setPosition(YogaEdge.LEFT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_TOP.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.TOP, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_RIGHT.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.RIGHT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BOTTOM.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.BOTTOM, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_START.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.START, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_END.equals(propertyName)) {
            yogaNode.setPosition(YogaEdge.END, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_LEFT.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.LEFT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_TOP.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.TOP, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_RIGHT.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.RIGHT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_BOTTOM.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.BOTTOM, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_START.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.START, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_END.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.END, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_HORIZONTAL.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.HORIZONTAL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.ALL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MARGIN_VERTICAL.equals(propertyName)) {
            yogaNode.setMargin(YogaEdge.VERTICAL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_LEFT.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.LEFT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_TOP.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.TOP, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_RIGHT.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.RIGHT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_BOTTOM.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.BOTTOM, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_START.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.START, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_END.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.END, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_HORIZONTAL.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.HORIZONTAL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING_VERTICAL.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.VERTICAL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_PADDING.equals(propertyName)) {
            yogaNode.setPadding(YogaEdge.ALL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_LEFT.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.LEFT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_TOP.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.TOP, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_RIGHT.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.RIGHT, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_BOTTOM.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.BOTTOM, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_START.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.START, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER_END.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.END, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_BORDER.equals(propertyName)) {
            yogaNode.setBorder(YogaEdge.ALL, DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_WIDTH.equals(propertyName)) {
            yogaNode.setWidth(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_HEIGHT.equals(propertyName)) {
            yogaNode.setHeight(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MIN_WIDTH.equals(propertyName)) {
            yogaNode.setMinWidth(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MIN_HEIGHT.equals(propertyName)) {
            yogaNode.setMinHeight(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MAX_WIDTH.equals(propertyName)) {
            yogaNode.setMaxWidth(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_MAX_HEIGHT.equals(propertyName)) {
            yogaNode.setMaxHeight(DimensUtils.dp2px(value));
        } else if (PropertyType.YOGA_ASPECT_RATIO.equals(propertyName)) {
            yogaNode.setAspectRatio(value);
        } else {
            return false;
        }
        return true;
    }

    public float getYogaProperty(YogaNode yogaNode, String propertyName) {
        LogUtil.i(TAG, "getYogaProperty -> propertyName: " + propertyName);
        if (PropertyType.YOGA_IS_ENABLE.equals(propertyName)) {
            // TODO : The property not found...
        } else if (PropertyType.YOGA_FLEX_DIRECTION.equals(propertyName)) {
            return yogaNode.getFlexDirection().intValue();
        } else if (PropertyType.YOGA_JUSTIFY_CONTENT.equals(propertyName)) {
            return yogaNode.getJustifyContent().intValue();
        } else if (PropertyType.YOGA_ALIGN_CONTENT.equals(propertyName)) {
            return yogaNode.getAlignContent().intValue();
        } else if (PropertyType.YOGA_ALIGN_ITEMS.equals(propertyName)) {
            return yogaNode.getAlignItems().intValue();
        } else if (PropertyType.YOGA_ALIGN_SELF.equals(propertyName)) {
            return yogaNode.getAlignSelf().intValue();
        } else if (PropertyType.YOGA_POSITION.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.ALL).value;
        } else if (PropertyType.YOGA_FLEX_WRAP.equals(propertyName)) {
            // TODO : The property not found...
        } else if (PropertyType.YOGA_OVERFLOW.equals(propertyName)) {
            return yogaNode.getOverflow().intValue();
        } else if (PropertyType.YOGA_DISPLAY.equals(propertyName)) {
            // TODO : The property not found...
        } else if (PropertyType.YOGA_FLEX_GROW.equals(propertyName)) {
            return yogaNode.getFlexGrow();
        } else if (PropertyType.YOGA_FLEX_SHRINK.equals(propertyName)) {
            return yogaNode.getFlexShrink();
        } else if (PropertyType.YOGA_FLEX_BASIS.equals(propertyName)) {
            return yogaNode.getFlexBasis().value;
        } else if (PropertyType.YOGA_LEFT.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.LEFT).value;
        } else if (PropertyType.YOGA_TOP.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.TOP).value;
        } else if (PropertyType.YOGA_RIGHT.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.RIGHT).value;
        } else if (PropertyType.YOGA_BOTTOM.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.BOTTOM).value;
        } else if (PropertyType.YOGA_START.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.START).value;
        } else if (PropertyType.YOGA_END.equals(propertyName)) {
            return yogaNode.getPosition(YogaEdge.END).value;
        } else if (PropertyType.YOGA_MARGIN_LEFT.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.LEFT).value;
        } else if (PropertyType.YOGA_MARGIN_TOP.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.TOP).value;
        } else if (PropertyType.YOGA_MARGIN_RIGHT.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.RIGHT).value;
        } else if (PropertyType.YOGA_MARGIN_BOTTOM.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.BOTTOM).value;
        } else if (PropertyType.YOGA_MARGIN_START.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.START).value;
        } else if (PropertyType.YOGA_MARGIN_END.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.END).value;
        } else if (PropertyType.YOGA_MARGIN_HORIZONTAL.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.HORIZONTAL).value;
        } else if (PropertyType.YOGA_MARGIN.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.ALL).value;
        } else if (PropertyType.YOGA_MARGIN_VERTICAL.equals(propertyName)) {
            return yogaNode.getMargin(YogaEdge.VERTICAL).value;
        } else if (PropertyType.YOGA_PADDING_LEFT.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.LEFT).value;
        } else if (PropertyType.YOGA_PADDING_TOP.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.TOP).value;
        } else if (PropertyType.YOGA_PADDING_RIGHT.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.RIGHT).value;
        } else if (PropertyType.YOGA_PADDING_BOTTOM.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.BOTTOM).value;
        } else if (PropertyType.YOGA_PADDING_START.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.START).value;
        } else if (PropertyType.YOGA_PADDING_END.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.END).value;
        } else if (PropertyType.YOGA_PADDING_HORIZONTAL.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.HORIZONTAL).value;
        } else if (PropertyType.YOGA_PADDING_VERTICAL.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.VERTICAL).value;
        } else if (PropertyType.YOGA_PADDING.equals(propertyName)) {
            return yogaNode.getPadding(YogaEdge.ALL).value;
        } else if (PropertyType.YOGA_BORDER_LEFT.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.LEFT);
        } else if (PropertyType.YOGA_BORDER_TOP.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.TOP);
        } else if (PropertyType.YOGA_BORDER_RIGHT.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.RIGHT);
        } else if (PropertyType.YOGA_BORDER_BOTTOM.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.BOTTOM);
        } else if (PropertyType.YOGA_BORDER_START.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.START);
        } else if (PropertyType.YOGA_BORDER_END.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.END);
        } else if (PropertyType.YOGA_BORDER.equals(propertyName)) {
            return yogaNode.getBorder(YogaEdge.ALL);
        } else if (PropertyType.YOGA_WIDTH.equals(propertyName)) {
            return yogaNode.getWidth().value;
        } else if (PropertyType.YOGA_HEIGHT.equals(propertyName)) {
            return yogaNode.getHeight().value;
        } else if (PropertyType.YOGA_MIN_WIDTH.equals(propertyName)) {
            return yogaNode.getMinWidth().value;
        } else if (PropertyType.YOGA_MIN_HEIGHT.equals(propertyName)) {
            return yogaNode.getMinHeight().value;
        } else if (PropertyType.YOGA_MAX_WIDTH.equals(propertyName)) {
            return yogaNode.getMaxWidth().value;
        } else if (PropertyType.YOGA_MAX_HEIGHT.equals(propertyName)) {
            return yogaNode.getMaxHeight().value;
        } else if (PropertyType.YOGA_ASPECT_RATIO.equals(propertyName)) {
            return yogaNode.getAspectRatio();
        }
        return .0f;
    }

    /**
     * recursive to inflate the layout with YogaNode.
     */
    public void inflate(IYoga root) {
        root.inflate();
    }

}
