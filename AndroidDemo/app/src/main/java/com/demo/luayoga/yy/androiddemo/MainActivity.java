package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.common.luakit.ILuaCallback;
import com.common.luakit.LuaHelper;
import com.common.luakit.YogaView;
import com.common.luakit.yoganode.YogaLayoutHelper;
import com.common.luakit.utils.DimensUtils;
import com.common.luakit.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private YogaView yogaView;

    private YogaLayoutHelper yogaLayoutHelper;

    private boolean hasLoadLua;

    private Button mButton;

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
        long root = yogaView.render("testYoga2");
        yogaView.setSelfPointer(root);
        LogUtil.i(TAG, "The root address is : " + root);
        yogaView.calculateLayout();
        if (root != -1) {
            yogaLayoutHelper.inflate(yogaView);
            hasLoadLua = true;
        }
    }

    @Override
    protected void onDestroy() {
        yogaLayoutHelper.releaseNativeMemory();
        super.onDestroy();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        yogaView = findViewById(R.id.demo_root_container);
        mButton = findViewById(R.id.button);
        final ILuaCallback callback = new ILuaCallback() {
            @Override
            public void onResult(Object o) {
                String result = (String)o;
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            }
        };
        //LuaHelper.callLuaFunction("testNotification", "observeUINotification");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LuaHelper.callLuaFunction("testNotification", "postNotification");
            }
        });
    }

    private void initMember() {
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

}
