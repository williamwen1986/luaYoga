package com.common.luakit.yoganode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class YogaListViewAdapter extends RecyclerView.Adapter<YogaListViewAdapter.YogaViewHolder> {

    private static final String TAG = "YogaListViewAdapter";

    private YogaLayoutHelper yogaLayoutHelper;

    /**
     * The jni pointer.
     */
    private long listViewSelf;

    YogaListViewAdapter(long listViewSelf) {
        LogUtil.i(TAG, "Build the Adapter");
        this.listViewSelf = listViewSelf;

        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @NonNull
    @Override
    public YogaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LogUtil.i(TAG, "onCreateViewHolder");
        return new YogaViewHolder(yogaLayoutHelper.onCreateView(listViewSelf));
    }

    @Override
    public void onBindViewHolder(@NonNull YogaViewHolder viewHolder, int position) {
        // TODO : YogaListView.reload()? how to trigger.
        LogUtil.i(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        LogUtil.i(TAG, "getItemCount");
        // TODO : refer to the function numberOfRowsInSection() in luaYoga/LuaTableView.mm
        return yogaLayoutHelper.getItemcount(listViewSelf);
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.i(TAG, "getItemViewType");
        // TODO : refer to the function getIdentifier() in luaYoga/LuaTableView.mm
        return yogaLayoutHelper.getItemViewType(listViewSelf, position);
    }

    static class YogaViewHolder extends RecyclerView.ViewHolder {

        YogaViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
