package com.demo.luayoga.yy.androiddemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.common.luakit.YogaContainerFragment;
import com.common.luakit.utils.DimensUtils;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "Main3Activity";

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
        setContentView(R.layout.activity_main3);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(R.id.demo_yoga_container);
        ids.add(R.id.demo_yoga_container2);

        ArrayList<String> modules = new ArrayList<>();
        modules.add("testYoga");
        modules.add("testYoga2");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.demo_root_container, YogaContainerFragment.newInstance(R.layout.yoga_container, ids, modules))
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
//        for (Fragment frag : fm.getFragments()) {
////            if (frag.isVisible()) {
//                FragmentManager childFm = frag.getFragmentManager();
//                if (childFm.getBackStackEntryCount() > 0) {
//                    childFm.popBackStack();
//                    return;
//                }
////            }
//        }

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            return;
        }

        super.onBackPressed();
    }
}
