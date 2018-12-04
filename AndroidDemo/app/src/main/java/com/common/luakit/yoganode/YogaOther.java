package com.common.luakit.yoganode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNode;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaOther extends View implements IYoga {

    private static final String TAG = "YogaOther";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root;

    private YogaNode yogaNode;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaOther(Context context) {
        super(context);
        yogaNode = new YogaNode();
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @Override
    public boolean setYogaProperty(int type, String propertyName, float value) {
        return yogaLayoutHelper.setYogaProperty(yogaNode, propertyName, value);
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
        setPadding((int) yogaNode.getPadding(YogaEdge.LEFT).value, (int) yogaNode.getPadding(YogaEdge.TOP).value,
                (int) yogaNode.getPadding(YogaEdge.RIGHT).value, (int) yogaNode.getPadding(YogaEdge.BOTTOM).value);
        setX(yogaNode.getLayoutX());
        setY(yogaNode.getLayoutY());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = (int) yogaNode.getWidth().value;
        params.height = (int) yogaNode.getHeight().value;
        params.setMargins((int) yogaNode.getMargin(YogaEdge.LEFT).value, (int) yogaNode.getMargin(YogaEdge.TOP).value,
                (int) yogaNode.getMargin(YogaEdge.RIGHT).value, (int) yogaNode.getMargin(YogaEdge.BOTTOM).value);
        setLayoutParams(params);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {

    }

}
