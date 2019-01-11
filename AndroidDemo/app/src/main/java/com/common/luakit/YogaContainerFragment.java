package com.common.luakit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianjiachao on 2019/1/10.
 */
public class YogaContainerFragment extends Fragment implements YogaTransition {
    private static final String TAG = "YogaContainerFragment";

    public static final String KEY_LAYOUT_ID = "layout_id";
    public static final String KEY_YOGA_ID_LIST = "yoga_id_list";
    public static final String KEY_YOGA_MODULE_LIST = "yoga_module_list";

    public static YogaContainerFragment newInstance(int layoutId, ArrayList<Integer> yogaViewIdList, ArrayList<String> yogaViewModuleList) {
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_ID, layoutId);
        args.putIntegerArrayList(KEY_YOGA_ID_LIST, yogaViewIdList);
        args.putStringArrayList(KEY_YOGA_MODULE_LIST, yogaViewModuleList);
        YogaContainerFragment fragment = new YogaContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }

        int layoutId = arguments.getInt(KEY_LAYOUT_ID);
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }

        List<Integer> yogaViewIdList = arguments.getIntegerArrayList(KEY_YOGA_ID_LIST);
        List<String> yogaViewModuleList = arguments.getStringArrayList(KEY_YOGA_MODULE_LIST);
        if (!yogaViewIdList.isEmpty() && !yogaViewModuleList.isEmpty()) {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            for (int i = 0; i < yogaViewIdList.size(); i++) {
                Integer viewId = yogaViewIdList.get(i);
                String yogaModule = yogaViewModuleList.get(i);
                ft.add(viewId, YogaFragment.newInstance(yogaModule));
            }
            ft.commitAllowingStateLoss();
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void toYoga(String moduleName) {
        getFragmentManager()
                .beginTransaction()
                .replace(((View) getView().getParent()).getId(), YogaFragment.newInstance(moduleName))
                .commitAllowingStateLoss();
    }
}
