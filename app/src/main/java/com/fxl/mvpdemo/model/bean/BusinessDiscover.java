package com.fxl.mvpdemo.model.bean;

import com.example.mythreeview.autoscrollviewpager.Slide;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10.
 */

public class BusinessDiscover {


    private List<Slide> SlidesList;
    private List<TipsListBean> TipsList;
    private List<Business> BusinessList;

    public List<Slide> getSlidesList() {
        return SlidesList;
    }

    public void setSlidesList(List<Slide> SlidesList) {
        this.SlidesList = SlidesList;
    }

    public List<TipsListBean> getTipsList() {
        return TipsList;
    }

    public void setTipsList(List<TipsListBean> TipsList) {
        this.TipsList = TipsList;
    }

    public List<Business> getBusinessList() {
        return BusinessList;
    }

    public void setBusinessList(List<Business> BusinessList) {
        this.BusinessList = BusinessList;
    }

    public static class TipsListBean {
        /**
         * ConfId : 1
         * ConfName : 酒吧
         */

        private int ConfId;
        private String ConfName;

        public int getConfId() {
            return ConfId;
        }

        public void setConfId(int ConfId) {
            this.ConfId = ConfId;
        }

        public String getConfName() {
            return ConfName;
        }

        public void setConfName(String ConfName) {
            this.ConfName = ConfName;
        }
    }
}
