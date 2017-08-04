package com.fxl.mvpdemo.pages.guid;

import android.content.DialogInterface;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.mvp.MVP;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.AUTO_LOGIN;

/**
 * Created by Administrator on 2017/2/17.
 * 引导页，兼启动闪屏页
 */

public interface GuidContact {
    interface GudiView extends MVP.BaseView{
        /**
         * 跳转到登录页
         */
        void navToLogin();

        /**
         * 免登录跳转到首页
         */
        void nvaToHome();

        /**
         * 显示被踢下线对话框
         * @param onClickListener 用户点击重新登录还是或是退出的回调
         */
        void showOtherDeviceLoginDialog(DialogInterface.OnClickListener onClickListener);

        /**
         * 请求权限
         */
        void requestPermission();
    }

    interface Presenter extends MVP.BasePresenter<GudiView>{
        /**
         * @return 引导页或启动页的图片
         */
        List<Integer> getGuidImages();

        void onPermissionGanted();
    }

    interface Model {
        /**
         * 自动登录
         * @param param 参数
         * @return 结果
         */
        @POST(AUTO_LOGIN)
        Observable<BaseEntity<UserInfo>> loginAutoKey(@Body HashMap<String, Object> param);
    }
}
