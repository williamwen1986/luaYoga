package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.common.luakit.YogaView;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNode;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaListView extends RecyclerView implements IYoga {

    private static final String TAG = "YogaListView";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root;

    private YogaNode yogaNode;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaListView(@NonNull Context context) {
        super(context);
        yogaNode = new YogaNode();
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @Override
    public boolean setYogaProperty(YogaView view, int type, String propertyName, float value) {
        return yogaLayoutHelper.setYogaProperty(yogaNode, propertyName, value);
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
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.width = (int) yogaNode.getWidth();
        params.height = (int) yogaNode.getHeight();
        params.setMargins((int) yogaNode.getMargin(YogaEdge.LEFT), (int) yogaNode.getMargin(YogaEdge.TOP),
                (int) yogaNode.getMargin(YogaEdge.RIGHT), (int) yogaNode.getMargin(YogaEdge.BOTTOM));
        setLayoutParams(params);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {

    }

}
