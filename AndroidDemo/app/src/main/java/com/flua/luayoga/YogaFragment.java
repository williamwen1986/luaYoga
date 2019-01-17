package com.flua.luayoga;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.common.luakit.ILuaCallback;
import com.flua.luayoga.utils.DimensUtils;
import com.flua.luayoga.utils.LogUtil;
import com.flua.luayoga.yoganode.YogaLayoutHelper;

/**
 * Created by xianjiachao on 2019/1/9.
 */
public class YogaFragment extends Fragment implements YogaTransition {

    private static final String TAG = "YogaFragment";
    private static final String YOGA_MODULE = "yoga_module";
    private static final String YOGA_MODULE_TITLE = "yoga_module_title";

    private YogaView yogaView;
    private YogaLayoutHelper yogaLayoutHelper;

    public static YogaFragment newInstance(String yogaModule, String title) {
        Bundle args = new Bundle();
        args.putString(YOGA_MODULE, yogaModule);
        args.putString(YOGA_MODULE_TITLE, title);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString(YOGA_MODULE_TITLE);
        if (!TextUtils.isEmpty(title)) {
            getActivity().setTitle(title);
        }
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

        // start load yoga after the first measure ensure parent width & height
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
    public void toYoga(String moduleName, String title) {
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
                    .add(((View) getParentFragment().getView().getParent()).getId(), YogaFragment.newInstance(moduleName, title), tag)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }
}
