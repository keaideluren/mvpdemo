package com.fxl.mvpdemo.model;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.UserInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.AUTO_LOGIN;
import static com.fxl.mvpdemo.model.Constant.LOGIN;

/**
 * Created by Administrator on 2017/2/15.
 * retrofit接口
 */

public interface RetrofitApis {
    /**
     * 登录 使用账号密码
     * @return
     */
    @POST(LOGIN)
    Observable<BaseEntity<UserInfo>> loginByPassWord(@Body HashMap<String, Object> param);

    /**
     * 自动登录
     * @return
     */
    @POST(AUTO_LOGIN)
    Observable<BaseEntity<UserInfo>> loginAutoKey(@Body HashMap<String, Object> param);

}
