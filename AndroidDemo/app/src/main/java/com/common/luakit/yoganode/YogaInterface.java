package com.common.luakit.yoganode;

import com.common.luakit.YogaView;
import com.facebook.yoga.YogaNode;

public interface YogaInterface {

    boolean setYogaProperty(YogaView view, int type, String propertyName, float value);

    YogaNode getYogaNode();

    void setPointer(long self, long parent, long root);

    long getSelfPointer();

    long getParentPointer();

    long getRootPointer();

}
