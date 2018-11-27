package com.common.luakit.yoganode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
        setPadding((int) yogaNode.getPadding(YogaEdge.LEFT), (int) yogaNode.getPadding(YogaEdge.TOP),
                (int) yogaNode.getPadding(YogaEdge.RIGHT), (int) yogaNode.getPadding(YogaEdge.BOTTOM));
        setX(yogaNode.getLayoutX());
        setY(yogaNode.getLayoutY());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = (int) yogaNode.getWidth();
        params.height = (int) yogaNode.getHeight();
        LogUtil.i(TAG, "params.width = " + params.width + ", params.height = " + params.height);
        /*params.setMargins((int) yogaNode.getMargin(YogaEdge.LEFT), (int) yogaNode.getMargin(YogaEdge.TOP),
                (int) yogaNode.getMargin(YogaEdge.RIGHT), (int) yogaNode.getMargin(YogaEdge.BOTTOM));*/
        setLayoutParams(params);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    public void nativeSetText(String text) {
        LogUtil.i(TAG, this + " set text : " + text );
        setTextColor(Color.BLACK);
        setText(text);
    }

    /*public void nativeSetTextColor() {
        setTextColor();
    }*/

    public void nativeSetTextNumberOfLines(float numberOfLines) {
        setMaxLines((int) numberOfLines);
    }

    public void nativeSetTextFont(float textSize, boolean isBold) {
        LogUtil.i(TAG, this + " textSize=" + textSize + ", isBold=" + isBold);
        setTextSize(20);
        if (isBold) {
            setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

}
