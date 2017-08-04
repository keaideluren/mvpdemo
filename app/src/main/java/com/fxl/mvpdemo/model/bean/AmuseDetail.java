package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 * 娱乐圈详情
 */

public class AmuseDetail implements Serializable {

    /**
     * Account : {"AccountId":1000,"AccountName":"寻乐宝小助手","HeadPicUrl":"http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg","Gender":0}
     * Comment : {"Id":1,"Content":"测试1号","Type":4,"Medias":[],"Address":"成都环球中心","Timer":"54分钟前","PraiseCount":1,"ReplyCount":1,"HasPraise":true}
     * Reply : [{"CommentId":1000,"AccountName":"寻乐宝小助手","HeadPicUrl":"http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg","Content":"自己评论自己，2333","ReplyId":null,"ReplyName":"","CreateTime":"2016-12-14"}]
     * Praise : [{"PraiseId":1000,"HeadPicUrl":"http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg"}]
     */

    private AccountBean Account;
    private CommentBean Comment;
    private List<Reply> Reply;
    private List<CommentDetail.PraiseBean> Praise;
    private LinkInfo LinkInfo;

    public AccountBean getAccount() {
        return Account;
    }

    public void setAccount(AccountBean Account) {
        this.Account = Account;
    }

    public CommentBean getComment() {
        return Comment;
    }

    public void setComment(CommentBean Comment) {
        this.Comment = Comment;
    }

    public List<Reply> getReply() {
        return Reply;
    }

    public void setReply(List<Reply> Reply) {
        this.Reply = Reply;
    }

    public List<CommentDetail.PraiseBean> getPraise() {
        return Praise;
    }

    public void setPraise(List<CommentDetail.PraiseBean> Praise) {
        this.Praise = Praise;
    }

    public static class AccountBean {
        /**
         * AccountId : 1000
         * AccountName : 寻乐宝小助手
         * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg
         * Gender : 0
         * IsFans boolean
         */

        private int AccountId;
        private String AccountName;
        private String HeadPicUrl;
        private int Gender;
        private boolean IsFans;

        public int getAccountId() {
            return AccountId;
        }

        public void setAccountId(int AccountId) {
            this.AccountId = AccountId;
        }

        public String getAccountName() {
            return AccountName;
        }

        public void setAccountName(String AccountName) {
            this.AccountName = AccountName;
        }

        public String getHeadPicUrl() {
            return HeadPicUrl;
        }

        public void setHeadPicUrl(String HeadPicUrl) {
            this.HeadPicUrl = HeadPicUrl;
        }

        public int getGender() {
            return Gender;
        }

        public void setGender(int Gender) {
            this.Gender = Gender;
        }

        public boolean isFans() {
            return IsFans;
        }

        public void setFans(boolean fans) {
            IsFans = fans;
        }
    }

    public LinkInfo getLinkInfo() {
        return LinkInfo;
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        LinkInfo = linkInfo;
    }

    public static class CommentBean implements Serializable {
        /**
         * Id : 1
         * Content : 测试1号
         * Type : 4
         * Medias : []
         * Address : 成都环球中心
         * Timer : 54分钟前
         * PraiseCount : 1
         * ReplyCount : 1
         * HasPraise : true
         * <p>
         * VodThu String 缩略图
         */

        private int Id;
        private String Content;
        private int Type;
        private String Address;
        private String Timer;
        private int PraiseCount;
        private int ReplyCount;
        private boolean HasPraise;
        private List<String> Medias;
        private String VodThu;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getTimer() {
            return Timer;
        }

        public void setTimer(String Timer) {
            this.Timer = Timer;
        }

        public int getPraiseCount() {
            return PraiseCount;
        }

        public void setPraiseCount(int PraiseCount) {
            this.PraiseCount = PraiseCount;
        }

        public int getReplyCount() {
            return ReplyCount;
        }

        public void setReplyCount(int ReplyCount) {
            this.ReplyCount = ReplyCount;
        }

        public boolean isHasPraise() {
            return HasPraise;
        }

        public void setHasPraise(boolean HasPraise) {
            this.HasPraise = HasPraise;
        }

        public List<String> getMedias() {
            return Medias;
        }

        public void setMedias(List<String> Medias) {
            this.Medias = Medias;
        }

        public String getVodThu() {
            return VodThu;
        }

        public void setVodThu(String vodThu) {
            VodThu = vodThu;
        }
    }

}
