package com.fxl.mvpdemo.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.mylibrary.Glide.GlideRoundTransform;


/**
 * Created by Administrator on 2R.drawable.bg_loading_holder17/2/7.
 * 图片加载工具类，统一使用这里的方法来加载图片，好换图片加载框架，
 */

public class ImageLoaderUtil {
    /**
     * 加载方头像，
     */
    public static void setAvatar(Context context, String headUrl, ImageView ivAvatar) {
        Glide.with(context)
                .load(headUrl)
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .centerCrop()
                .into(ivAvatar);//加载到哪个控件
    }

    /**
     * 加载圆头像，
     */
    public static void setCircleAvatar(Context context, String headUrl, ImageView ivAvatar) {
        Glide.with(context)
                .load(headUrl)
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .transform(new GlideRoundTransform(context))
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .centerCrop()
                .into(ivAvatar);//加载到哪个控件
    }

    /**
     * 加载网络图片
     */
    public static void setPic(Context context, String picUrl, ImageView imageView) {
        Glide.with(context)
                .load(picUrl)
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .transform(new GlideRoundTransform(context))
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .into(imageView);//加载到哪个控件
    }

    /**
     * 加载资源图片
     */
    public static void setPic(Context context,@DrawableRes int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .error(R.drawable.bg_loading_holder)
                .placeholder(R.drawable.bg_loading_holder)
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)
                .into(imageView);
    }

    /**
     * 加载gif网络图片
     */
    public static void setGifPic(Context context, String picUrl, ImageView imageView) {
        Glide.with(context)
                .load(picUrl)
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .transform(new GlideRoundTransform(context))
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .into(imageView);//加载到哪个控件
    }

    /**
     * 加载gif本地图片
     */
    public static void setGifAssetPic(Context context, ImageView imageView, String gifAssetName) {
//        InputSource.AssetSource assetSource = new InputSource.AssetSource(context.getAssets(), gifAssetName);e.printStackTrace();
//        Glide.with(context)
//                .load(context.getResources().getAssets().open(gifAssetName))
//                .error(R.drawable.bg_loading_holder)//加载失败图片
//                .placeholder(R.drawable.bg_loading_holder)//加载中图片
//                .transform(new GlideRoundTransform(context))
//                .animate(R.anim.alpha_in)//加载完成后载入动画
//                .into(imageView);//加载到哪个控件
    }

    /**
     * 加载gif本地图片
     */
    public static void setGifResourcePic(Context context, @DrawableRes int gifResourceId, ImageView imageView) {
        Glide.with(context)
                .load(gifResourceId)
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .transform(new GlideRoundTransform(context))
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .into(imageView);//加载到哪个控件
    }

    /**
     * 加载方头像，
     */
    public static void setAvatar(Fragment fragment, String headUrl, ImageView ivAvatar) {
        Glide.with(fragment)
                .load(headUrl)
//                .asGif()//设置Gif图片
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .centerCrop()
                .into(ivAvatar);//加载到哪个控件
    }

    /**
     * 加载圆头像，
     */
    public static void setCircleAvatar(Fragment fragment, String headUrl, ImageView ivAvatar) {
        Glide.with(fragment)
                .load(headUrl)
//                .asGif()//设置Gif图片
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .transform(new GlideRoundTransform(fragment.getContext()))
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .centerCrop()
                .into(ivAvatar);//加载到哪个控件
    }

    /**
     * 加载网络图片
     */
    public static void setPic(Fragment fragment, String picUrl, ImageView imageView) {
        Glide.with(fragment)
                .load(picUrl)
//                .asGif()//设置Gif图片
                .error(R.drawable.bg_loading_holder)//加载失败图片
                .placeholder(R.drawable.bg_loading_holder)//加载中图片
                .animate(com.fxl.mvpdemo.myutils.R.anim.alpha_in)//加载完成后载入动画
                .into(imageView);//加载到哪个控件
    }

    /**
     * 清除存储卡上的缓存
     *
     * @param context
     */
    public static void clearDiskCache(final Context context) {
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Glide.get(context).clearDiskCache();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .subscribe();
    }
}
