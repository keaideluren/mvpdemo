package com.fxl.mvpdemo.pages.amuselist;

import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.Amuse;
import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.model.network.RetrofitRefreshSubscriber;
import com.fxl.mvpdemo.model.network.RetrofitRequestUtil;
import com.fxl.mvpdemo.mvp.base.BaseRefreshFragmentPresenter;
import com.fxl.mvpdemo.util.JsonParam;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/6/16.
 */

public class AmuseListPresenter extends BaseRefreshFragmentPresenter<AmuseListContact.AmuseView> implements AmuseListContact.Presenter {
    private AmuseListContact.Model api;

    @Inject
    public AmuseListPresenter(RetrofitHelper retrofitHelper) {
        api = retrofitHelper.createApi(AmuseListContact.Model.class);
    }

    @Override
    public void stopNetWork() {

    }

    @Override
    public void requestData(int pageIndex, int type) {
        new RetrofitRequestUtil<List<Amuse>>(){
            @Override
            public Observable<BaseEntity<List<Amuse>>> getRequest(HashMap<String, Object> param, String cacheKey) {
                return api.getAmuseList(param,cacheKey);
            }
        }.request(JsonParam.newInstance()
                .add("AccountId", UserInfo.getInstance().getId())
                .add("PageIndex", pageIndex)
                .add("PageSize", Constant.PAGE_SIZE)
            , new RetrofitRefreshSubscriber<>(mView, type));
    }
}
