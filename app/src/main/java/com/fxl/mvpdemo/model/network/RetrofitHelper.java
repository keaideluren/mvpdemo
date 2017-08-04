package com.fxl.mvpdemo.model.network;

import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.mylibrary.diskcache.LurenCacheInterceptor;
import com.fxl.mvpdemo.myutils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codeest on 2016/8/3.
 * 网络加载层
 */
public class RetrofitHelper {
    public static final int CACHE_FIRST = 0;
    public static final int NET_FIRST = 0;
    public static final int CACHE_ONLY = 0;
    public static final int NET_ONLY = 0;
    private OkHttpClient okHttpClient = null;

    private void init() {
        initOkHttp();
    }

    public RetrofitHelper() {
        init();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (LogUtil.mDebuggable >= LogUtil.LEVEL_INFO) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message ->
                    LogUtil.i("httpRetrofit", message));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new LurenCacheInterceptor(LeplusApplication.getInstance()));
        //设置缓存
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    public <D> D createApi(Class<D> classOfD) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(classOfD);
    }



}
