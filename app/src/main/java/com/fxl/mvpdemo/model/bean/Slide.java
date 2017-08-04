package com.fxl.mvpdemo.model.bean;

/**
 * Created by tcz on 2016/7/26.
 */
public class Slide {


    /**
     * SlideImg : http://o7rvvizw0.bkt.clouddn.com/1.jpeg-s
     * SlideUrl : www.baidu.com
     * CanJump :是否可以跳转
     * NeedPay : 是否要加一段去报名
     * ActivityId :活动Id
     */

    private String SlideImg;
    private String SlideUrl;
    private boolean CanJump;
    private boolean NeedPay;
    private long ActivityId;

    public String getSlideImg() {
        return SlideImg;
    }

    public void setSlideImg(String SlideImg) {
        this.SlideImg = SlideImg;
    }

    public String getSlideUrl() {
        return SlideUrl;
    }

    public void setSlideUrl(String SlideUrl) {
        this.SlideUrl = SlideUrl;
    }

    public boolean isCanJump() {
        return CanJump;
    }

    public void setCanJump(boolean canJump) {
        CanJump = canJump;
    }

    public boolean isNeedPay() {
        return NeedPay;
    }

    public void setNeedPay(boolean needPay) {
        NeedPay = needPay;
    }

    public long getActivityId() {
        return ActivityId;
    }

    public void setActivityId(long activityId) {
        ActivityId = activityId;
    }
}
