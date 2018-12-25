package com.common.luakit.yoganode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.common.luakit.YogaView;
import com.common.luakit.utils.LogUtil;

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
        YogaView content = new YogaView(context);
        yogaLayoutHelper.onCreateView(listViewSelf, listViewRoot, content);
        // viewGroup.addView(content, null);
        return new YogaViewHolder(content);
    }

    @Override
    public void onBindViewHolder(@NonNull final YogaViewHolder viewHolder, final int position) {
        LogUtil.i(TAG, "onBindViewHolder");
        YogaView content = ((YogaView) viewHolder.itemView);
        yogaLayoutHelper.onBindView(listViewSelf, listViewRoot, content.getSelfPointer(), position);
        content.calculateLayout();
        yogaLayoutHelper.inflate(content);
        content.setEnabled(true);
        content.setClickable(true);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i(TAG, "Click the plugin list item.");
                yogaLayoutHelper.onItemClick(listViewSelf, listViewRoot, viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        int itemCount = yogaLayoutHelper.getItemcount(0, listViewSelf, listViewRoot);
        LogUtil.i(TAG, "getItemCount : " + itemCount);
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.i(TAG, "getItemViewType -> " + "mytest");
        String identifier = yogaLayoutHelper.getItemViewType(listViewSelf, listViewRoot, position);
        LogUtil.i(TAG, "getItemViewType -> " + identifier);
        int type;
        switch (identifier) {
            case "test":
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
