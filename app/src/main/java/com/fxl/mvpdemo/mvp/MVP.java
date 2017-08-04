package com.fxl.mvpdemo.mvp;

/**
 * Created by Administrator on 2017/2/15.
 * MVP根
 */

public interface MVP {
    /**
     * MVP view 层 基类
     */
    interface BaseView <T extends BasePresenter>{
        int DIALOG_TYPE_LOADING = 0;
//    void showError(String msg);

        //    void useNightMode(boolean isNight);
        void showToast(String messsage);

        /**
         * 根据类型显示对话框 0，默认为加载中
         * 为什么要加个Base，showDialog被占用了
         * @param type
         */
        void showBaseDialog(int type);

        /**
         * 根据类型取消对话框 0，默认为加载中
         * 为什么要加个Base，因为dismissDialog被占用了
         * @param type
         */
        void dismissBaseDialog(int type);

        /**
         * /**
         * @param title
         * @param type 0 1 2
         * @link EmptyView.TYPE_LOADING TYPE_NO_DATA TYPE_ERROR
         */
        void setEmptyView(String title ,int type);

        void clearEmptyView();

    }

    /**
     * MVP  Model层基类
     */
    interface BaseModel {
        Object getApi();
    }

    /**
     * MVP Presenter基类，
     * @param <T> presenter需要持有View层
     */
    interface BasePresenter<T extends BaseView> {
        void attachView(T view);

        void detachView();
    }

}
