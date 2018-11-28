package com.common.luakit.yoganode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.demo.luayoga.yy.androiddemo.utils.LogUtil;

public class YogaListViewAdapter extends RecyclerView.Adapter {

    private static final String TAG = "YogaListViewAdapter";

    /**
     * The jni pointer.
     */
    private long listViewSelf, listViewParent, listViewRoot;

    YogaListViewAdapter(long listViewSelf, long listViewParent, long listViewRoot) {
        LogUtil.i(TAG, "Build the Adapter");
        this.listViewSelf = listViewSelf;
        this.listViewParent = listViewParent;
        this.listViewRoot = listViewRoot;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // TODO : refer to the function numberOfRowsInSection() in luaYoga/LuaTableView.mm
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        // TODO : refer to the function numberOfRowsInSection() in luaYoga/LuaTableView.mm
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO : refer to the function getIdentifier() in luaYoga/LuaTableView.mm
        return super.getItemViewType(position);
    }
}
