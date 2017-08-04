package com.fxl.mvpdemo.pages.amusedetail;

import com.fxl.mvpdemo.model.bean.AmuseDetail;
import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.util.JsonParam;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/23.
 * 这个类自我感觉可以不需要，但是还是要弄出来，普适的情况，免得说BaseModel没用
 */

public class AmuseDetailModel implements MVP.BaseModel {
    RetrofitHelper retrofitHelper;

    @Inject
    public AmuseDetailModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public AmuseDetailContact.Model getApi() {
        return retrofitHelper.createApi(AmuseDetailContact.Model.class);
    }

    public Observable<BaseEntity<AmuseDetail>> getAmuseDetail(int amuseId) {

        JsonParam add = JsonParam.newInstance().add("AccountId", UserInfo.getInstance().getId())
                .add("Id", amuseId);
        return getApi()
                .getAmuseDetail(add.getMap(), add.getCacheKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
