package com.example.mythreeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Administrator 可爱的路人 on 2017/4/19.
 * 左上和右上是圆角的ImageView
 */

public class TopAngleImageView extends CircleImageView {
    public TopAngleImageView(Context context) {
        super(context);
    }

    public TopAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopAngleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRadius > 0) {
            Path path = new Path();
            path.moveTo(mRadius, 0);
            path.lineTo(getWidth() - mRadius, 0);
            path.arcTo(new RectF(getWidth() - 2 * mRadius, 0, getWidth(), 2 * mRadius), 270, 90);
            path.lineTo(getWidth(), getHeight());
            path.lineTo(0, getHeight());
            path.lineTo(0, mRadius);
            path.arcTo(new RectF(0, 0, mRadius * 2, mRadius * 2), 180, 90);
            path.close();
            canvas.drawPath(path, mBitmapPaint);
        }
    }
}
