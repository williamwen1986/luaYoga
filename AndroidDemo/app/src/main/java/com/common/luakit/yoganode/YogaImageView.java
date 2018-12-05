package com.common.luakit.yoganode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.common.luakit.constant.PropertyType;
import com.demo.luayoga.yy.androiddemo.utils.LogUtil;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaNode;

import java.io.InputStream;

/**
 * Created by hjx on 2018/11/19
 */
public class YogaImageView extends SimpleCircleImageView implements IYoga {

    private static final String TAG = "YogaImageView";

    /**
     * The native pointer address returned from Jni calling.
     */
    private long self, parent, root;

    private YogaNode yogaNode;

    private YogaLayoutHelper yogaLayoutHelper;

    public YogaImageView(Context context) {
        super(context);
        yogaNode = new YogaNode();
        yogaLayoutHelper = YogaLayoutHelper.getInstance();
    }

    @Override
    public boolean setYogaProperty(int type, String propertyName, float value) {
        LogUtil.i(TAG, "setYogaProperty -> propertyName: " + propertyName + ", value: " + value);
        if (PropertyType.YOGA_IS_ENABLE.equals(propertyName)) {
            boolean enabled = value == 1.0f;
            setEnabled(enabled);
            setClickable(enabled);
            return true;
        } else {
            return yogaLayoutHelper.setYogaProperty(yogaNode, propertyName, value);
        }
    }

    @Override
    public float getYogaProperty(int type, String propertyName) {
        return yogaLayoutHelper.getYogaProperty(yogaNode, propertyName);
    }

    @Override
    public YogaNode getYogaNode() {
        return yogaNode;
    }

    @Override
    public void setNativePointer(long self, long parent, long root) {
        this.self = self;
        this.parent = parent;
        this.root = root;
        LogUtil.i(TAG, "The self = " + self + ", parent = " + parent + ", root = " + root);
    }

    @Override
    public long getSelfPointer() {
        return self;
    }

    @Override
    public long getParentPointer() {
        return parent;
    }

    @Override
    public long getRootPointer() {
        return root;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void inflate() {
        setPadding((int) yogaNode.getPadding(YogaEdge.LEFT).value, (int) yogaNode.getPadding(YogaEdge.TOP).value,
                (int) yogaNode.getPadding(YogaEdge.RIGHT).value, (int) yogaNode.getPadding(YogaEdge.BOTTOM).value);
        setX(yogaNode.getLayoutX());
        setY(yogaNode.getLayoutY());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = (int) yogaNode.getWidth().value;
        params.height = (int) yogaNode.getHeight().value;
        params.setMargins((int) yogaNode.getMargin(YogaEdge.LEFT).value, (int) yogaNode.getMargin(YogaEdge.TOP).value,
                (int) yogaNode.getMargin(YogaEdge.RIGHT).value, (int) yogaNode.getMargin(YogaEdge.BOTTOM).value);
        setLayoutParams(params);
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    public void nativeSetBackgroundColor(float r, float g, float b, float a) {
        setBackgroundColor(Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b)));
    }

    /**
     * Jni calling method.
     * Set the image source to the view.
     *
     * @param imagePath The path of Picture in sdcard.
     */
    public void nativeSetImagePath(String imagePath) {
        try {
            InputStream inputStream = getContext().getAssets().open(imagePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            int viewWidth = (int) yogaNode.getWidth().value;
            int viewHeight = (int)yogaNode.getHeight().value;
            options.inSampleSize = calculateInSampleSize(options, viewWidth, viewHeight);
            options.inJustDecodeBounds = false;
            inputStream.reset();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int viewWidth, int viewHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        LogUtil.i(TAG, "imgWidth = " + width + ", imgHeight = " + height +
                "; viewWidth = " + viewWidth + ", viewHeight = " + viewHeight);
        int inSampleSize = 1;
        if (height > viewHeight || width > viewWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > viewHeight
                    && (halfWidth / inSampleSize) > viewWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }
}
