package com.fxl.mvpdemo.pages.amusedetail;

import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.AmuseDetail;
import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.mvp.MVP;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/6/23.
 */

public interface AmuseDetailContact {
    interface AmuseDetailView extends MVP.BaseView {
        void setView(AmuseDetail amuseDetail);
    }

    interface Presenter extends MVP.BasePresenter<AmuseDetailView> {
        void requestAmuseDetail(int amuseId);
    }

    interface Model {
        @POST(Constant.AMUSE_DETAIL)
        Observable<BaseEntity<AmuseDetail>> getAmuseDetail(@Body HashMap<String, Object> param, @Header("CacheKey") String cacheKey);
    }

}
