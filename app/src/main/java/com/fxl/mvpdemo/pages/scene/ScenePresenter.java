package com.fxl.mvpdemo.pages.scene;

import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.BusinessDiscover;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.model.network.RetrofitRefreshSubscriber;
import com.fxl.mvpdemo.mvp.base.BaseRefreshFragmentPresenter;
import com.fxl.mvpdemo.util.JsonParam;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator 可爱的路人 on 2017/3/24.
 * 主页，首页
 */

public class ScenePresenter extends BaseRefreshFragmentPresenter<SceneContact.SceneView> implements SceneContact.Presenter {
    private SceneContact.Model api;
    private RetrofitRefreshSubscriber<BusinessDiscover> apiGetBusinessListSubscriber;

    @Inject
    public ScenePresenter(RetrofitHelper retrofitHelper) {
        api = retrofitHelper.createApi(SceneContact.Model.class);
    }

    /**
     * 请求数据
     * /**
     * SearchKey string 搜索关键字
     * Latitude float 纬度
     * Longitude float 经度
     * PageIndex int 索引
     * PageSize int 页容量
     *
     * @param i    页数
     * @param type 加载类型，0下拉，1上拉
     */
    public void requestData(int i, int type) {
        if (apiGetBusinessListSubscriber != null) {
            apiGetBusinessListSubscriber.unSubscriber();
        }

        apiGetBusinessListSubscriber = new RetrofitRefreshSubscriber<BusinessDiscover>(mView, type);
        JsonParam param = JsonParam.newInstance()
                .add("SearchKey", "")
                .add("Latitude", 0)
                .add("Longitude", 0)
                .add("PageIndex", i)
                .add("PageSize", Constant.PAGE_SIZE);
        api.getBusinessList(param.getMap(), param.getCacheKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiGetBusinessListSubscriber);
    }

    @Override
    public void stopNetWork() {
        if (apiGetBusinessListSubscriber != null) {
            apiGetBusinessListSubscriber.unSubscriber();
        }
    }
}
