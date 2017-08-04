package com.fxl.mvpdemo.mvp.component;

import android.app.Activity;

import com.fxl.mvpdemo.mvp.FragmentScope;
import com.fxl.mvpdemo.mvp.module.FragmentModule;
import com.fxl.mvpdemo.pages.amuselist.AmuseFragment;
import com.fxl.mvpdemo.pages.scene.SceneFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 *
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(SceneFragment sceneFragment);
    void inject(AmuseFragment amuseFragment);

}
