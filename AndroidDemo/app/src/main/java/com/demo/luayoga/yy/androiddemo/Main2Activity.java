package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.common.luakit.LuaHelper;
import com.flua.luayoga.YogaFragment;
import com.flua.luayoga.utils.DimensUtils;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DimensUtils.setDensity(this);
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        setContentView(R.layout.activity_main2);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.demo_root_container, YogaFragment.newInstance("testYoga", getTitle().toString()))
                .commitAllowingStateLoss();
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LuaHelper.callLuaFunction("testNotification", "postNotification");
            }
        });
    }

}
