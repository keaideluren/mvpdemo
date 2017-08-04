package com.fxl.mvpdemo.pages.scene;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.BusinessDiscover;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.HOME;

/**
 * Created by Administrator 可爱的路人 on 2017/3/24.
 * 首页主页
 */

public interface SceneContact {

    interface SceneView extends IBaseRefreshView<BusinessDiscover> {

    }

    interface Presenter extends MVP.BasePresenter<SceneView>{

    }

    interface Model{
        /**
            获取首页数据
         SearchKey string 搜索关键字
         Latitude float 纬度
         Longitude float 经度
         PageIndex int 索引
         PageSize int 页容量

         */
        @POST(HOME)
        Observable<BaseEntity<BusinessDiscover>> getBusinessList(@Body HashMap<String, Object> param, @Header("Cache-Type") String isCache);
    }
}
