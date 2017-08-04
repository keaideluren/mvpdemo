package com.fxl.mvpdemo.pages.amuselist;

import com.fxl.mvpdemo.model.bean.Amuse;
import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.ENTERAINMENT_LIST;

/**
 * Created by Administrator on 2017/6/15.
 *
 */

public interface AmuseListContact {


    interface AmuseView extends IBaseRefreshView<Amuse> {

    }

    interface Presenter extends MVP.BasePresenter<AmuseView>{

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
        @POST(ENTERAINMENT_LIST)
        Observable<BaseEntity<List<Amuse>>> getAmuseList(@Body HashMap<String, Object> param, @Header("Cache-Type") String isCache);
    }
}
