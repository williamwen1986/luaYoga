package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class YogaListViewAdapter extends RecyclerView.Adapter<YogaListViewAdapter.YogaViewHolder> {

    private static final String TAG = "YogaListViewAdapter";

    private YogaLayoutHelper yogaLayoutHelper;

    private Context context;

    /**
     * The jni pointer.
     */
    private long listViewSelf, listViewRoot;

    YogaListViewAdapter(Context context, long listViewSelf, long listViewRoot) {
        LogUtil.i(TAG, "Build the Adapter");
        this.context = context;
        this.listViewSelf = listViewSelf;
        this.listViewRoot = listViewRoot;
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @NonNull
    @Override
    public YogaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LogUtil.i(TAG, "onCreateViewHolder");
        // return new YogaViewHolder(yogaLayoutHelper.onCreateView(listViewSelf));
        return new YogaViewHolder(new TextView(context));
    }

    @Override
    public void onBindViewHolder(@NonNull YogaViewHolder viewHolder, int position) {
        // TODO : YogaListView.reload()? how to trigger.
        LogUtil.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        // TODO : refer to the function numberOfRowsInSection() in luaYoga/LuaTableView.mm
        int itemCount = yogaLayoutHelper.getItemcount(0, listViewSelf, listViewRoot);
        LogUtil.i(TAG, "getItemCount : " + itemCount);
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.i(TAG, "getItemViewType");
        // TODO : refer to the function getIdentifier() in luaYoga/LuaTableView.mm
        int type;
        switch (yogaLayoutHelper.getItemViewType(listViewSelf, listViewRoot, position)) {
            case "identifier":
                type = 1;
                break;
            default:
                type = 0;
                break;
        }
        return type;
    }

    static class YogaViewHolder extends RecyclerView.ViewHolder {

        YogaViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
