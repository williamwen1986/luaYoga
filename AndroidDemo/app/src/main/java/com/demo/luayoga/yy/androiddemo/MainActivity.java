package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.common.luakit.YogaView;
import com.common.luakit.utils.DimensUtils;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.common.luakit.yoganode.YogaLayoutHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private YogaView yogaView;

    private YogaLayoutHelper yogaLayoutHelper;

    private boolean hasLoadLua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DimensUtils.setDensity(this);
        initView();
        initMember();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus || hasLoadLua) {
            return;
        }
        LogUtil.i(TAG, "Begin to render the yoga layout !!!!!!");
        // long root = yogaView.render("MLDataCard");
        long root = yogaView.render("MLDataCard");
        yogaView.setSelfPointer(root);
        LogUtil.i(TAG, "The root address is : " + root);
        yogaView.calculateLayout();
        if (root != -1) {
            yogaLayoutHelper.inflate(yogaView);
            hasLoadLua = true;
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
