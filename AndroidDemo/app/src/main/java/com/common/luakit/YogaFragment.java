package com.common.luakit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.common.luakit.utils.DimensUtils;
import com.common.luakit.utils.LogUtil;
import com.common.luakit.yoganode.YogaLayoutHelper;

/**
 * Created by xianjiachao on 2019/1/9.
 */
public class YogaFragment extends Fragment implements YogaTransition {

    private static final String TAG = "YogaFragment";
    private static final String YOGA_MODULE = "yoga_module";

    private YogaView yogaView;
    private YogaLayoutHelper yogaLayoutHelper;

    public static YogaFragment newInstance(String yogaModule) {
        Bundle args = new Bundle();
        args.putString(YOGA_MODULE, yogaModule);
        YogaFragment fragment = new YogaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DimensUtils.setDensity(getContext());
        initMember();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        yogaView = new YogaView(getContext());

        YogaTransition yt;
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof YogaTransition) {
            yt = (YogaTransition) parentFragment;
        } else {
            yt = this;
        }

        yogaView.setYogaTransition(yt);
        yogaView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // start load after the first measure ensure parent width height
        yogaView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                String moduleName = getArguments().getString(YOGA_MODULE);

                final ILuaCallback callback = new ILuaCallback() {
                    @Override
                    public void onResult(Object o) {
                        String result = (String)o;
                        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                    }
                };
                //LuaHelper.callLuaFunction("testNotification", "observeUINotification");

                LogUtil.i(TAG, "Begin to render the yoga layout !!!!!! module name : " + moduleName);
                // long root = yogaView.render("MLDataCard");
                long root = yogaView.render(moduleName);
                yogaView.setSelfPointer(root);
                LogUtil.i(TAG, "The root address is : " + root);
                yogaView.calculateLayout();
                if (root != -1) {
                    yogaLayoutHelper.inflate(yogaView);
                }
                yogaView.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });

        return yogaView;
    }

    @Override
    public void onDestroyView() {
        yogaLayoutHelper.releaseNativeMemory();
        super.onDestroyView();
    }

    private void initMember() {
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @Override
    public void toYoga(String moduleName) {
        String tag = getTag() + "_" + moduleName;
        Fragment fragment = getParentFragment().getFragmentManager().findFragmentByTag(tag);

        if (fragment != null) { // already added
            getParentFragment().getFragmentManager()
//            getFragmentManager()
                    .beginTransaction()
                    .hide(getParentFragment())
                    .show(fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        } else {
            getParentFragment().getFragmentManager()
//            getFragmentManager()
                    .beginTransaction()
                    .hide(getParentFragment())
                    .add(((View) getParentFragment().getView().getParent()).getId(), YogaFragment.newInstance(moduleName), tag)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }
}
