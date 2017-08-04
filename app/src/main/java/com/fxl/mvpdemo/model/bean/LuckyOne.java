package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 * 一元夺婊
 */

public class LuckyOne implements Serializable {

    private List<NoticeBean> noticeList;
    private List<Slide> slidesList;
    private List<SeizeListBean> seizeList;

    public LuckyOne() {
    }

    public List<NoticeBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeBean> noticeList) {
        this.noticeList = noticeList;
    }

    public List<Slide> getSlidesList() {
        return slidesList;
    }

    public void setSlidesList(List<Slide> slidesList) {
        this.slidesList = slidesList;
    }

    public List<SeizeListBean> getSeizeList() {
        return seizeList;
    }

    public void setSeizeList(List<SeizeListBean> seizeList) {
        this.seizeList = seizeList;
    }

    public static class SeizeListBean {
        /**
         * Id : 1
         * Name : iphone7 128GB
         * CoverUrl : http://image.xunlebao.cn/I7_1.jpg
         * TotalAmount : 100
         * RemainAmount : 100
         * Price decimal 单价
         ConfName string 分类名（例如：十元专区 ）
         */

        private int Id;
        private String Name;
        private String CoverUrl;
        private int TotalAmount;
        private int RemainAmount;
        private BigDecimal Price;
        private String ConfName;
        private int ConfId;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCoverUrl() {
            return CoverUrl;
        }

        public void setCoverUrl(String CoverUrl) {
            this.CoverUrl = CoverUrl;
        }

        public int getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(int TotalAmount) {
            this.TotalAmount = TotalAmount;
        }

        public int getRemainAmount() {
            return RemainAmount;
        }

        public void setRemainAmount(int RemainAmount) {
            this.RemainAmount = RemainAmount;
        }

        public int getConfId() {
            return ConfId;
        }

        public void setConfId(int confId) {
            ConfId = confId;
        }

        public BigDecimal getPrice() {
            return Price==null?new BigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP)
                    :Price.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setPrice(BigDecimal price) {
            Price = price;
        }

        public String getConfName() {
            return ConfName;
        }

        public void setConfName(String confName) {
            ConfName = confName;
        }

        @Override
        public String toString() {
            return "SeizeListBean{" +
                    "Id=" + Id +
                    ", Name='" + Name + '\'' +
                    ", CoverUrl='" + CoverUrl + '\'' +
                    ", TotalAmount=" + TotalAmount +
                    ", RemainAmount=" + RemainAmount +
                    ", Price=" + Price +
                    ", ConfName='" + ConfName + '\'' +
                    '}';
        }
    }

    /**
     * Id int 宝贝Id
     LotteryGoods string 奖品名
     AccountName int 用户Id
     */
    public static class NoticeBean implements Serializable {
        private int Id;
        private String LotteryGoods;
        private String AccountName;
        private String Timer;
        private int EntertainmentId;
        private int Gender;
        private boolean IsFans;

        public NoticeBean() {
        }

        public int getGender() {
            return Gender;
        }

        public void setGender(int gender) {
            Gender = gender;
        }

        public boolean isFans() {
            return IsFans;
        }

        public void setFans(boolean fans) {
            IsFans = fans;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getLotteryGoods() {
            return LotteryGoods;
        }

        public void setLotteryGoods(String lotteryGoods) {
            LotteryGoods = lotteryGoods;
        }

        public String getAccountName() {
            return AccountName;
        }

        public void setAccountName(String accountName) {
            AccountName = accountName;
        }

        public String getTimer() {
            return Timer;
        }

        public void setTimer(String timer) {
            Timer = timer;
        }

        public int getEntertainmentId() {
            return EntertainmentId;
        }

        public void setEntertainmentId(int entertainmentId) {
            EntertainmentId = entertainmentId;
        }

        @Override
        public String toString() {
            return "NoticeBean{" +
                    "Id=" + Id +
                    ", LotteryGoods='" + LotteryGoods + '\'' +
                    ", AccountName=" + AccountName +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LuckyOne{" +
                "noticeList=" + noticeList +
                ", slidesList=" + slidesList +
                ", seizeList=" + seizeList +
                '}';
    }
}
