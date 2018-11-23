package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.common.luakit.YogaView;
import com.common.luakit.yoganode.YogaLayoutHelper;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private YogaView yogaView;

    private YogaLayoutHelper yogaLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initMember();

        LogUtil.i(TAG, "Begin to render the yoga layout !!!!!!");
        long root = yogaView.render("testYoga");
        LogUtil.i(TAG, "The root address is : " + root);
        yogaView.getYogaNode().calculateLayout();
        if (root != -1) {
            yogaLayoutHelper.inflate(yogaView);
        }

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        yogaView = findViewById(R.id.demo_root_container);
    }

    private void initMember() {
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

}
