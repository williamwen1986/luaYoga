package com.common.luakit.yoganode;

import android.view.View;

import com.facebook.yoga.YogaNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The class wrapper the View object would to be handled in Yoga.
 */
public class YogaNodeWrapper {

    private static final String TAG = "YogaNodeWrapper";

    private View parent;
    private YogaNode root;

    private List<View> viewList;
    private List<YogaNode> nodeList;

    public YogaNodeWrapper(View view) {
        this.parent = view;
        viewList = new ArrayList<>();
        nodeList = new ArrayList<>();
    }

    public void addChildNode(View childView, YogaNode childNode, int i) {
        nodeList.add(i, childNode);
        viewList.add(i, childView);
    }

    private View getParent() {
        return parent;
    }

    public YogaNode getChildNode(int i) {
        return nodeList.get(i);
    }

    public View getChildView(int i) {
        return viewList.get(i);
    }

}
