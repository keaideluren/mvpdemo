package com.fxl.mvpdemo.mylibrary.diskcache;

import java.io.Serializable;

/**
 * Created by Administrator 可爱的路人 on 2017/5/17.
 * 网络缓存的头部，自己读来自己用
 */

public class LurenCacheHead implements Serializable {
    private String cacheKey;
    private long cacheDuration;
    //RetrofitHelper.
    private int cacheType;

    public LurenCacheHead(String cacheKey, long cacheDuration, int cacheType) {
        this.cacheKey = cacheKey;
        this.cacheDuration = cacheDuration;
        this.cacheType = cacheType;
    }

    public LurenCacheHead() {
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public long getCacheDuration() {
        return cacheDuration;
    }

    public void setCacheDuration(long cacheDuration) {
        this.cacheDuration = cacheDuration;
    }

    public int getCacheType() {
        return cacheType;
    }

    public void setCacheType(int cacheType) {
        this.cacheType = cacheType;
    }
}
