package com.flua.luayoga.yoganode;

import com.facebook.yoga.YogaNode;

public interface IYoga {

    /**
     * Called in jni.
     * Set the attr and parameter to the view.
     * @param type The view type
     * @param propertyName The property to set
     * @param value the property value
     * @return
     */
    boolean setYogaProperty(int type, String propertyName, float value);

    /**
     * Called in jni.
     * get the attr and parameter to the view.
     * @param type The view type
     * @param propertyName The property to set
     * @return
     */
    float getYogaProperty(int type, String propertyName);

    /**
     * Called in jni.
     * Save the native pointer in java layer.
     * @param self The native pointer to the current view
     * @param parent The native pointer to the parent of the current view
     * @param root The native pointer to the root view
     */
    void setNativePointer(long self, long parent, long root);

    void nativeSetBackgroundColor(float r, float g, float b, float a);

    /**
     * Called in jni.
     * represent the click event.
     */
    void nativeAddTapGesture();

    /**
     * Called in jni.
     * represent the long click event.
     */
    void nativeAddLongPressGesture();

    boolean removeFromParent();

    void reloadYoga();

    YogaNode getYogaNode();

    long getSelfPointer();

    long getParentPointer();

    long getRootPointer();

    boolean isRoot();

    void inflate();

}
