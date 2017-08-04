package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;

/**
 * Created by 张阿丙 on 2017/5/3.
 */

public class ActivityBean implements Serializable {

    /**
     * Id : 31
     * Cover : http://image.xunlebao.cn/20170406094739152.gif
     * BusinessId : 84
     * BusinessName : 一起哈皮.
     * Title : 倒计时一天 愚人节特别活动福利送不停
     */

    private int Id;
    private String Cover;
    private int BusinessId;
    private String BusinessName;
    private String Title;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }

    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int BusinessId) {
        this.BusinessId = BusinessId;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
}
