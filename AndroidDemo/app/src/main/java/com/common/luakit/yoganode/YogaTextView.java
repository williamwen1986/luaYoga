package com.common.luakit.yoganode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.LuaHelper;
import com.common.luakit.constant.PropertyType;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNode;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaTextView extends android.support.v7.widget.AppCompatTextView implements IYoga {

    private static final String TAG = "YogaTextView";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root;

    private YogaNode yogaNode;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaTextView(Context context) {
        super(context);
        yogaNode = new YogaNode();
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
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
            return yogaLayoutHelper.setYogaProperty(yogaNode, propertyName, value);
        }
    }

    @Override
    public float getYogaProperty(int type, String propertyName) {
        return yogaLayoutHelper.getYogaProperty(yogaNode, propertyName);
    }

    @Override
    public YogaNode getYogaNode() {
        return yogaNode;
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

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void inflate() {
        setX(yogaNode.getLayoutX());
        setY(yogaNode.getLayoutY());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if ((int) yogaNode.getWidth().value > 0) {
            params.width = (int) yogaNode.getWidth().value;
        }
        if ((int) yogaNode.getHeight().value > 0) {
            params.height = (int) yogaNode.getHeight().value;
        }
        setLayoutParams(params);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    public void nativeSetText(String text) {
        LogUtil.i(TAG, this + " set text : " + text);
        setTextColor(Color.BLACK);
        setText(text);
    }

    public void nativeSetTextColor(float r, float g, float b, float a) {
        setTextColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    public void nativeSetTextHighlightedColor(float r, float g, float b, float a) {
        setHighlightColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    public void nativeSetTextNumberOfLines(float numberOfLines) {
        if (numberOfLines > 0) {
            setMaxLines((int) numberOfLines);
        }
    }

    public void nativeSetTextFont(String fontName, float textSize, boolean isBold) {
        LogUtil.i(TAG, this + " fontName: " + fontName + " textSize=" + textSize + ", isBold=" + isBold);
        setTextSize(textSize);
        if (!TextUtils.isEmpty(fontName)) {
            try {
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName));
            } catch (Exception e) {
                if (isBold) {
                    setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        } else {
            if (isBold) {
                setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
        }
    }

    public void nativeSetTextAlignment(float textAlignment) {
        if (textAlignment == 0) { // Left
            setTextAlignment(TEXT_ALIGNMENT_INHERIT);
        } else if (textAlignment == 1) { // Center
            setTextAlignment(TEXT_ALIGNMENT_CENTER);
        } else if (textAlignment == 2) { // Right
            setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        }
    }

    @Override
    public void nativeAddTapGesture() {
        LogUtil.i(TAG, this + "AddTapGesture");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i(TAG, this + "onClick");
                LuaHelper.callback(LuaHelper.TYPE_CALLBACK_PRESS, self);
            }
        });
    }

    @Override
    public void nativeAddLongPressGesture() {
        LogUtil.i(TAG, this + "AddLongTapGesture");
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LogUtil.i(TAG, this + "onLongClick");
                LuaHelper.callback(LuaHelper.TYPE_CALLBACK_LONG_PRESS, self);
                return true;
            }
        });
    }
}
