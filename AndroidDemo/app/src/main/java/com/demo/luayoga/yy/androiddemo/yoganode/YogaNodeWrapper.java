package com.demo.luayoga.yy.androiddemo.yoganode;

import android.view.View;

import com.facebook.yoga.YogaNode;

/**
 * The class wrapper the View object would to be handled in Yoga.
 */
public class YogaNodeWrapper extends YogaNode {

    private View nodeView;
    private int width;
    private int height;
    private boolean enabled;
    private boolean clickable;

    private YogaNodeWrapper() {
        
    }



}
