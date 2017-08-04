package com.fxl.mvpdemo.mvp.base;

import com.fxl.mvpdemo.mvp.MVP;

/**
 * Created by Administrator on 2017/2/24.
 * 带上拉下拉的Activity的基类
 * E上拉下拉的数据,一般是用{@link com.fxl.mvpdemo.model.bean.BaseEntity#Data}作为泛型
 * 调用的位置基本是在{@link com.fxl.mvpdemo.model.network.RetrofitRefreshSubscriber#onSuccess(Object, String, int)}
 * {@link com.fxl.mvpdemo.model.network.RetrofitRefreshSubscriber#onError(Throwable)}
 */
public interface IBaseRefreshView<E> extends MVP.BaseView {
    int REFRESH_TYPE_LOAD = 0;
    int REFRESH_TYPE_REFRESH = 1;
    int REFRESH_TYPE_MORE = 2;
    int REFRESH_STATE_SUCCESS = 0;
    int REFRESH_STATE_END = 1;
    int REFRESH_STATE_ERROR = 2;

    /**
     * 停止下拉头继续动画
     * @param type  是下拉还是上拉0下拉，1上拉，分别要形成互斥
     *              {@link #REFRESH_TYPE_LOAD}
     *              {@link #REFRESH_TYPE_REFRESH}
     *              {@link #REFRESH_TYPE_MORE}
     * @param state 状态0，上拉成功，1上拉无更多数据，2上拉失败
     *              {@link #onLoadMore(Object, String, int) return 返回值作为参数来控制Footer}
     *              {@link #REFRESH_STATE_ERROR}
     *              {@link #REFRESH_STATE_END}
     *              {@link #REFRESH_STATE_SUCCESS}
     */
    void stopRefresh(int state, int type);

    /**
     * @param baseData 数据
     * @param message 请求结果
     * @param code 请求结果码
     * @return 数据是否正确，控件Foot显示状态，加载中，无数据，加载错误
     */
    int onLoadMore(E baseData, String message, int code);

    /**
     * @param baseData 数据
     * @param message 请求结果
     * @param code 请求结果码
     * @return 数据是否正确，不正确时，空页面显示内容为message
     */
    boolean onLoad(E baseData, String message, int code);

    /**
     * @param baseData 数据
     * @param message 请求结果
     * @param code 请求结果码
     * @return 数据是否正确，不正确时，空页面显示内容为message,但是Adapter里的内容需要手动清除才有效
     * ，包括Head不包括Footer
     */
    boolean onRefresh(E baseData, String message, int code);
}
