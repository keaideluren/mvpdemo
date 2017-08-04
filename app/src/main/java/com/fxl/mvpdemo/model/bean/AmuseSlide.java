package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator 可爱的路人 on 2017/5/15.
 * 约耍的Banner
 */

public class AmuseSlide implements Serializable {
    private List<Slide> Siles;
    private List<LuckyOne.NoticeBean> Notice;

    public List<Slide> getSiles() {
        return Siles;
    }

    public void setSiles(List<Slide> siles) {
        Siles = siles;
    }

    public List<LuckyOne.NoticeBean> getNotice() {
        return Notice;
    }

    public void setNotice(List<LuckyOne.NoticeBean> notice) {
        Notice = notice;
    }
}
