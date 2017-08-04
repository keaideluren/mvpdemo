package com.fxl.mvpdemo.pages.activitylist;

import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.ActivityBean;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.model.network.RetrofitRefreshSubscriber;
import com.fxl.mvpdemo.mvp.base.BaseRefreshActivityPresenter;
import com.fxl.mvpdemo.util.JsonParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator 可爱的路人 on 2017/5/8.
 * 活动列表
 */

public class ActivityListPresenter extends BaseRefreshActivityPresenter<ActivityListContact.AcListView> implements ActivityListContact.Presenter {

    private final ActivityListContact.Model api;
    private RetrofitRefreshSubscriber<List<ActivityBean>> apiGetActivityListSubscriber;

    @Inject
    public ActivityListPresenter(RetrofitHelper retrofitHelper) {
        api = retrofitHelper.createApi(ActivityListContact.Model.class);
    }

    /**
     * @param index
     * @param type  获取活动列表
     *              PageIndex int 页索引
     *              PageSize int 页容量
     *              Latitude float 纬度
     *              Longitude float 经度
     */
    public void requestData(int index, int type) {
        stopNetWork();
        apiGetActivityListSubscriber = new RetrofitRefreshSubscriber<>(mView, type);
        api.getActivityList(JsonParam.newInstance()
            .add("PageIndex", index)
            .add("PageSize", Constant.PAGE_SIZE)
            .add("Latitude", 0)
            .add("Longitude", 0)
            .getMap())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(apiGetActivityListSubscriber);
    }

    @Override
    public void stopNetWork() {
        if (apiGetActivityListSubscriber != null) {
            apiGetActivityListSubscriber.unSubscriber();
        }
    }
}
