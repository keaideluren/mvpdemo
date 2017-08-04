package com.fxl.mvpdemo.pages.login;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.mvp.MVP;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.LOGIN;

/**
 * Created by Administrator on 2017/2/24.
 * 登录页，接口
 */

public interface LoginCountact {
    int LOGIN_TYPE_PWD = 0;
    int LOGIN_TYPE_THIRD = 1;
    interface LoginView extends MVP.BaseView{
        /**
         * 跳转到主页
         */
        void navToHome();


    }
    interface Presenter extends MVP.BasePresenter<LoginView>{
        /**
         * 登录事件交给Presenter来做
         * @param phone 登录需要的手机号，如果 @param loginContactLoginType 为LOGIN_TYPE_PWD时，否则是第三方登录TOKEN
         * @param pwd 登录密码，如果 @param loginContactLoginType 为LOGIN_TYPE_PWD时，否则为空
         * @param loginContactLoginType 登录方式，0是手机密码登录，1是第三方登录
         */
        void doLogin(String phone, String pwd, int loginContactLoginType);

    }

    interface Model {
        /**
         * 手机密码登录
         * @param param 参数
         * @return 结果
         */
        @POST(LOGIN)
        Observable<BaseEntity<UserInfo>> loginByPassWord(@Body HashMap<String, Object> param);
    }

}
