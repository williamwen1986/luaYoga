package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.common.luakit.YogaView;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.soloader.SoLoader;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaPositionType;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private YogaView yogaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yogaView = findViewById(R.id.demo_root_container);

        SoLoader.init(MainActivity.this, false);

        LogUtil.i(TAG, "Begin to render the yoga layout !!!!!!");
        long root = yogaView.render("testYoga");
        LogUtil.i(TAG, "The root address is : " + root);

        // For test begin
        /*YogaNode root = new YogaNode();
        root.setWidth(500);
        root.setHeight(300);
        root.setAlignItems(YogaAlign.CENTER);
        root.setJustifyContent(YogaJustify.CENTER);
        root.setPadding(YogaEdge.ALL, 20);

        YogaNode text = new YogaNode();
        text.setWidth(200);
        text.setHeight(25);

        YogaNode image = new YogaNode();
        image.setWidth(50);
        image.setHeight(50);
        image.setPositionType(YogaPositionType.ABSOLUTE);
        image.setPosition(YogaEdge.END, 20);
        image.setPosition(YogaEdge.TOP, 20);

        root.addChildAt(text, 0);
        root.addChildAt(image, 1);

        root.calculateLayout();

        StringBuffer buffer = new StringBuffer();
        buffer.append("text,layout X:").append(text.getLayoutX())
                .append(" layout Y:").append(text.getLayoutY()).append("\n")
                .append("image,layout X:").append(image.getLayoutX())
                .append(" layout Y:").append(image.getLayoutY());
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();*/
        // For test end

    }
}
