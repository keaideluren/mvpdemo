package com.fxl.mvpdemo.myutils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;

@SuppressWarnings("ALL")
public abstract class CustomToast {
    public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

    private static android.widget.Toast toast;
    private static Handler handler = new Handler();

    private static Runnable run = new Runnable() {
        public void run() {
            toast.cancel();
        }
    };

    private static void toast(Context ctx, CharSequence msg, int duration,int position) {
        handler.removeCallbacks(run);
        // handler的duration不能直接对应Toast的常量时长，在此针对Toast的常量相应定义时长
        switch (duration) {
            case LENGTH_SHORT:// CustomToast.LENGTH_SHORT值为0，对应的持续时间大概为1s
                duration = 1000;
                break;
            case LENGTH_LONG:// CustomToast.LENGTH_LONG值为1，对应的持续时间大概为3s
                duration = 3000;
                break;
            default:
                break;
        }
        if (null != toast) {
            toast.setText(msg);
        } else {
            toast = android.widget.Toast.makeText(ctx, msg, duration);
        }
        handler.postDelayed(run, duration);
        switch (position){
            case 1:
                toast.setGravity(Gravity.CENTER , 0, -50);
                break;
            default:
                break;

        }

        toast.show();
    }

    /**
     * 弹出Toast
     *
     * @param ctx
     *            弹出Toast的上下文
     * @param msg
     *            弹出Toast的内容
     * @param duration
     *            弹出Toast的持续时间
     */
    public static void show(Context ctx, CharSequence msg, int duration)
            throws NullPointerException {
        if (null == ctx) {
            throw new NullPointerException("The ctx is null!");
        }
        if (0 > duration) {
            duration = LENGTH_SHORT;
        }
        toast(ctx, msg, duration,0);
    }

    /**
    *
            * @param ctx
    *            弹出Toast的上下文
    * @param msg
    *            弹出Toast的内容
    * @param duration
    *            弹出Toast的持续时间
    */
    public static void show(Context ctx, CharSequence msg, int duration,int position)
            throws NullPointerException {
        if (null == ctx) {
            throw new NullPointerException("The ctx is null!");
        }
        if (0 > duration) {
            duration = LENGTH_SHORT;
        }
        toast(ctx, msg, duration,position);
    }

    /**
     * 弹出Toast
     *
     * @param ctx
     *            弹出Toast的上下文
     * @param resId
     *            弹出Toast的内容的资源ID
     * @param duration
     *            弹出Toast的持续时间
     */
    public static void show(Context ctx, int resId, int duration)
            throws NullPointerException {
        if (null == ctx) {
            throw new NullPointerException("The ctx is null!");
        }
        if (0 > duration) {
            duration = LENGTH_SHORT;
        }
        toast(ctx, ctx.getResources().getString(resId), duration,0);
    }

}