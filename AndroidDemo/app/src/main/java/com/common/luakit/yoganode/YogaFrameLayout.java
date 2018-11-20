package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.common.luakit.YogaView;
import com.common.luakit.constant.ViewType;
import com.facebook.yoga.YogaNode;

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

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaFrameLayout(@NonNull Context context) {
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
