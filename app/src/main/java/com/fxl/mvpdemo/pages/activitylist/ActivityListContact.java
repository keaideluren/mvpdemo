package com.fxl.mvpdemo.pages.activitylist;

import com.fxl.mvpdemo.model.bean.ActivityBean;
import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.fxl.mvpdemo.model.Constant.TAKE_ACITIVITYS;

/**
 * Created by Administrator 可爱的路人 on 2017/5/8.
 * 活动列表
 */

public class ActivityListContact {
    interface AcListView extends IBaseRefreshView<List<ActivityBean>> {

    }

    interface Presenter extends MVP.BasePresenter<AcListView> {

    }

    interface Model {
        /**
         * 获取活动列表
         * PageIndex int 页索引
         * PageSize int 页容量
         * Latitude float 纬度
         * Longitude float 经度
         */
        @POST(TAKE_ACITIVITYS)
        Observable<BaseEntity<List<ActivityBean>>> getActivityList(@Body HashMap<String, Object> param);
    }
}
