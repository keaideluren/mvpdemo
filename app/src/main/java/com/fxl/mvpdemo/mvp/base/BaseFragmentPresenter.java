package com.fxl.mvpdemo.mvp.base;

import com.fxl.mvpdemo.mvp.MVP;

/**
 * Created by Administrator 可爱的路人 on 2017/4/1.
 * fragment的基础P
 */

public abstract class BaseFragmentPresenter<T extends MVP.BaseView> extends RxPresenter<T> implements ILazyLoad {

}
