package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.constant.ViewType;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNode;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaFrameLayout extends FrameLayout implements IYoga {

    private static final String TAG = "YogaFrameLayout";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root;

    private Context context;

    private YogaNode yogaNode;

    private YogaNodeWrapper yogaNodeWrapper;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaFrameLayout(@NonNull Context context) {
        super(context);
        yogaNode = new YogaNode();
        yogaNodeWrapper = new YogaNodeWrapper(this, yogaNode);
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

    public View addYogaView(View parent, int type) {
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

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void inflate() {
        setPadding((int) yogaNode.getPadding(YogaEdge.LEFT), (int) yogaNode.getPadding(YogaEdge.TOP),
                (int) yogaNode.getPadding(YogaEdge.RIGHT), (int) yogaNode.getPadding(YogaEdge.BOTTOM));
        setX(yogaNode.getPosition(YogaEdge.LEFT));
        setY(yogaNode.getPosition(YogaEdge.TOP));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = (int) yogaNode.getWidth();
        params.height = (int) yogaNode.getHeight();
        params.setMargins((int) yogaNode.getMargin(YogaEdge.LEFT), (int) yogaNode.getMargin(YogaEdge.TOP),
                (int) yogaNode.getMargin(YogaEdge.RIGHT), (int) yogaNode.getMargin(YogaEdge.BOTTOM));
        setLayoutParams(params);
        for (int i = 0; i < yogaNode.getChildCount(); i++) {
            yogaNodeWrapper.getChildView(i).inflate();
            addView((View) yogaNodeWrapper.getChildView(i), i);
        }
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {

    }
}
