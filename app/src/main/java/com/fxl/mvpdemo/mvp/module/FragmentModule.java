package com.fxl.mvpdemo.mvp.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.fxl.mvpdemo.mvp.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
