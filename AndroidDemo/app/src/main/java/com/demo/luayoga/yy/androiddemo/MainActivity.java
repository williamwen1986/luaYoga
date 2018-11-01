package com.demo.luayoga.yy.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.yoga.YogaNode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YogaNode root = new YogaNode();
        root.setWidth(300);
        root.setHeight(500);
    }
}
