package com.fxl.mvpdemo.mylibrary.diskcache;

import android.content.Context;
import android.content.pm.PackageManager;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator 可爱的路人 on 2017/5/17.
 * 实现Post缓存的拦截器
 */

public class LurenCacheInterceptor implements Interceptor {
    public static final String HTTP_HEADER_CACHE_KEY_CUSTOM = "CacheKey-Luren";
    public static final String HTTP_HEADER_CACHE_DURATION_CUSTOM = "CacheDuration-Luren";
    public static final String HTTP_HEADER_CACHE_TYPE_CUSTOM = "CacheType-Luren";

    private DiskLruCache diskLruCache;

    public LurenCacheInterceptor(Context context) {
        //最大缓存20M

        try {
            diskLruCache = DiskLruCache.open(new File(context.getExternalCacheDir().getAbsolutePath() + "apiCache")
                    , getPackageVersion(context)
                    , 1
                    , 20 * 1024 * 1024);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    private int getPackageVersion(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        String cacheKey = request.header(HTTP_HEADER_CACHE_KEY_CUSTOM);
//        String cacheDuration = request.header(HTTP_HEADER_CACHE_DURATION_CUSTOM);
//        String cacheType = request.header(HTTP_HEADER_CACHE_TYPE_CUSTOM);
//        if (!TextUtils.isEmpty(cacheKey) && diskLruCache != null) {
//            int cacheDurationi = 0, cacheTypei = 0;
//            try {
//                cacheDurationi = Integer.valueOf(cacheDuration);
//                cacheTypei = Integer.valueOf(cacheType);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            LurenCacheHead lurenCacheHead = new LurenCacheHead(cacheKey, cacheDurationi, cacheTypei);
//
////                return new Response.Builder().cacheResponse().build();
//        }
        return chain.proceed(request);
    }
}
