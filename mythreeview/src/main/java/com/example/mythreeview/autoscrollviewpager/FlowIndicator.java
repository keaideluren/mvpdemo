package com.example.mythreeview.autoscrollviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.example.mythreeview.R;


public class FlowIndicator extends View {

    int mCount;//指示器中实心圆数量
    int mRadius;//实心圆半径
    int mSpace;//实心圆之前的距离
    int mFocus;//当前实心圆的颜色
    int mNormalColor;//非焦点实心圆的颜色
    int mFocusColor;//焦点实心圆的颜色

    public FlowIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlowIndicator);
        mNormalColor = array.getColor(R.styleable.FlowIndicator_normal_color,
            Color.parseColor("#f5f5f5"));
        mFocusColor = array.getColor(R.styleable.FlowIndicator_focus_color,
            Color.parseColor("#ff4401"));
        mSpace = array.getDimensionPixelOffset(R.styleable.FlowIndicator_space, 15);
        mRadius = array.getDimensionPixelOffset(R.styleable.FlowIndicator_r, 15);
        mCount = array.getInt(R.styleable.FlowIndicator_count, 1);
        mFocus = array.getInt(R.styleable.FlowIndicator_focus, 0);
        array.recycle();
    }

    /**
     * 返回指示器实心圆数量
     *
     * @return
     */
    public int getCount() {
        return this.mCount;
    }

    /**
     * 设置指示器实心圆数量
     */
    public void setCount(int count) {
        this.mCount = count;
        postInvalidate();
//        invalidate();
    }

    /**
     * 设置当前焦点的下标
     *
     * @param focus
     */
    public void setFocus(int focus) {
        this.mFocus = focus;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int spaceSize = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = spaceSize;
        } else {
            result = (int) (getLeftPaddingOffset() + getRightFadingEdgeStrength())
                + 2 * mRadius * mCount + (mCount - 1) * mSpace;
//            result = Math.min(result, spaceSize);
        }
        return result;
    }

    private int measureWidth() {
        return 2 * mRadius * mCount + (mCount - 1) * mSpace;
    }

    private int measureHeight() {
        return getTopPaddingOffset() + getBottomPaddingOffset() + 2 * mRadius;
    }


    /**
     * 测量高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int spaceSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = spaceSize;
//            result=285;
        } else {
            result = getTopPaddingOffset() + getBottomPaddingOffset() + 2 * mRadius;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //int leftSpace=(getWidth()-mRadius*2*mCount+(mCount-1)*mSpace)/2;
        for (int i = 0; i < mCount; i++) {
            //int x=getLeftPaddingOffset()+leftSpace+i*(2*mRadius+mSpace)+mRadius-mSpace;
            int xOffset = (getMeasuredWidth() - measureWidth()) / 2;
            int x = getLeftPaddingOffset() + i * (2 * mRadius + mSpace) + mRadius + xOffset;
            int color = mFocus == i ? mFocusColor : mNormalColor;
            paint.setColor(color);
            canvas.drawCircle(x, getHeight() / 2, mRadius, paint);
        }
    }

}