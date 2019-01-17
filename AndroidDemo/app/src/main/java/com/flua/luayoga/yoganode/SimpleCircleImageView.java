package com.flua.luayoga.yoganode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

import com.flua.luayoga.utils.LogUtil;

import java.lang.ref.WeakReference;

/**
 * Created by hjx on 2018/12/05
 */
public class SimpleCircleImageView extends android.support.v7.widget.AppCompatImageView {

    private static final String TAG = "SimpleCircleImageView";

    private Paint paint;
    private Xfermode mode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap maskBitmap;
    private WeakReference<Bitmap> weakBitmap;

    private int type; // circle or round type, default is rectangle
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_ROUND = 2;

    private static final int BORDER_RADIUS_DEFAULT = 0;
    private int roundRadius = BORDER_RADIUS_DEFAULT;

    private int width = 0;
    private int height = 0;

    private float centerX, centerY, radius;

    public SimpleCircleImageView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right - left;
        height = bottom - top;

        centerX = width / 2;
        centerY = height / 2;
        radius = Math.min(centerX, centerY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (type == TYPE_DEFAULT) {
            super.onDraw(canvas);
            return;
        }
        Bitmap bitmap = weakBitmap == null ? null : weakBitmap.get();
        if (bitmap == null || bitmap.isRecycled()) {
            if (width == 0 || height == 0) {
                LogUtil.i(TAG, "The size occurs error, width = " + width + ", height = " + height);
                return;
            }
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas drawCanvas = new Canvas(bitmap);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable.draw(drawCanvas);
            }
            weakBitmap = new WeakReference<>(bitmap); // cache bitmap, avoiding malloc frequently

            if (maskBitmap == null || maskBitmap.isRecycled()) {
                maskBitmap = getBitmap();
            }
            paint.reset();
            paint.setFilterBitmap(false);
            paint.setXfermode(mode);
            drawCanvas.drawBitmap(maskBitmap, 0, 0, paint);

            canvas.drawBitmap(bitmap, 0, 0, null);
            paint.setXfermode(null);

        } else {
            paint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        }
    }

    @Override
    public void invalidate() {
        weakBitmap = null;
        if (maskBitmap != null) {
            maskBitmap.recycle();
            maskBitmap = null;
        }
        super.invalidate();
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(new RectF(0f, 0f, width, height), roundRadius, roundRadius, paint);
        } else {
            canvas.drawCircle(centerX, centerY, radius, paint);
        }
        return bitmap;
    }

    public void setType(int type) {
        if (type < 0 || type > 2) {
            throw new IllegalStateException("The type should be between 0 and 2");
        }
        this.type = type;
        invalidate();
    }

    public void setRoundRadius(int radius) {
        roundRadius = radius;
        invalidate();
    }

}
