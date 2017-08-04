package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class FansInfo implements Serializable {

    /**
     * FansCount : 1
     * IsFan : true
     */

    private int FansCount;
    private boolean IsFan;

    public int getFansCount() {
        return FansCount;
    }

    public void setFansCount(int FansCount) {
        this.FansCount = FansCount;
    }

    public boolean isIsFan() {
        return IsFan;
    }

    public void setIsFan(boolean IsFan) {
        this.IsFan = IsFan;
    }
}
