package com.fxl.mvpdemo.mvp.component;

import com.fxl.mvpdemo.mvp.ActivityScope;
import com.fxl.mvpdemo.pages.guid.AutoLoginService;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/28.
 * Service的注射器，注入Application和RetrofitHelper，就是AppComponent的东东,不添加新依赖
 */

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ServiceComponent {

    void inject(AutoLoginService autoLoginService) ;
}
