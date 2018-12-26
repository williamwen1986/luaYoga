package com.common.luakit.yoganode;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.LuaHelper;
import com.common.luakit.constant.PropertyType;
import com.common.luakit.utils.LogUtil;
import com.facebook.yoga.YogaNode;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaButton extends android.support.v7.widget.AppCompatButton implements IYoga {

    private static final String TAG = "YogaButton";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root = -1;

    private YogaNode yogaNode;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaButton(Context context) {
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
        params.width = (int) yogaNode.getWidth().value;
        params.height = (int) yogaNode.getHeight().value;
        setLayoutParams(params);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
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

    @Override
    public boolean removeFromParent() {
        return false;
    }

    @Override
    public void reloadYoga() {

    }

    public void nativeSetText(String text) {
        setText(text);
    }

    /*public void nativeSetTextColor() {
        setTextColor();
    }*/

    public void nativeSetTextNumberOfLines(float numberOfLines) {
        setMaxLines((int)numberOfLines);
    }
}
