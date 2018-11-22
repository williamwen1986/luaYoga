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

    private View view;
    private YogaNode node;

    private List<IYoga> childViewList;

    public YogaNodeWrapper(View view, YogaNode yogaNode) {
        this.view = view;
        childViewList = new ArrayList<>();
        node = yogaNode;
    }

    public void addChild(IYoga yoga) {
        int index = node.getChildCount();
        node.addChildAt(yoga.getYogaNode(), index);
        childViewList.add(index,  yoga);
    }

    private View getView() {
        return view;
    }

    public YogaNode getChildNode(int i) {
        return node.getChildAt(i);
    }

    public IYoga getChildView(int i) {
        return childViewList.get(i);
    }

    public int getChildCount() {
        return Math.min(node.getChildCount(), childViewList.size());
    }

}
