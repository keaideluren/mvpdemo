package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/23.
 */

public class LinkInfo implements Serializable {
    /**
     * LinkId : 0
     * Cover :
     * Title :
     */

    private int LinkId;
    private String Cover;
    private String Title;
    private boolean NeedPay;

    public int getLinkId() {
        return LinkId;
    }

    public void setLinkId(int LinkId) {
        this.LinkId = LinkId;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public boolean isNeedPay() {
        return NeedPay;
    }

    public void setNeedPay(boolean needPay) {
        NeedPay = needPay;
    }
}
