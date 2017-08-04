package com.fxl.mvpdemo.model.bean;

import java.util.List;

/**
 * Created by tcz on 2016/10/27.
 */
public class CommentDetail {


    /**
     * AccountId : 1000
     * AccountName : 寻乐宝小助手
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg
     * Gender : 0
     */

    private AccountBean Account;
    /**
     * Id : d4b1d6fc-3fb2-4cfe-83d1-b7bc0331c52d
     * Content : 玩个奶子啊
     * Pictures : []
     * Timer : 1天前
     * PraiseCount : 9999
     * ReplyCount : 10002
     * HasPraise : false
     */

    private CommentBean Comment;
    /**
     * AccountId : 1507
     * AccountName : 风车
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1507/201608021620416424.jpg
     * Content : 哈哈哈第一条
     * ReplyId : null
     * ReplyName :
     * CreateTime : 2016-10-27
     */

    private List<ReplyBean> Reply;
    /**
     * PraiseId : 1001
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1001/201607221054410410.jpg
     */

    private List<PraiseBean> Praise;

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

    public List<ReplyBean> getReply() {
        return Reply;
    }

    public void setReply(List<ReplyBean> Reply) {
        this.Reply = Reply;
    }

    public List<PraiseBean> getPraise() {
        return Praise;
    }

    public void setPraise(List<PraiseBean> Praise) {
        this.Praise = Praise;
    }

    public static class AccountBean {
        private int AccountId;
        private String AccountName;
        private String HeadPicUrl;
        private int Gender;

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
    }

    public static class CommentBean {
        private String Id;
        private String Content;
        private String Timer;
        private String PraiseCount;
        private String ReplyCount;
        private boolean HasPraise;
        private List<String> Pictures;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getTimer() {
            return Timer;
        }

        public void setTimer(String Timer) {
            this.Timer = Timer;
        }

        public String getPraiseCount() {
            return PraiseCount;
        }

        public void setPraiseCount(String PraiseCount) {
            this.PraiseCount = PraiseCount;
        }

        public String getReplyCount() {
            return ReplyCount;
        }

        public void setReplyCount(String ReplyCount) {
            this.ReplyCount = ReplyCount;
        }

        public boolean isHasPraise() {
            return HasPraise;
        }

        public void setHasPraise(boolean HasPraise) {
            this.HasPraise = HasPraise;
        }

        public List<String> getPictures() {
            return Pictures;
        }

        public void setPictures(List<String> Pictures) {
            this.Pictures = Pictures;
        }
    }

    public static class ReplyBean {
        private int AccountId;
        private String AccountName;
        private String HeadPicUrl;
        private String Content;
        private int ReplyId;
        private String ReplyName;
        private String CreateTime;

        public ReplyBean(int accountId, String accountName, String headPicUrl, String content) {
            AccountId = accountId;
            AccountName = accountName;
            HeadPicUrl = headPicUrl;
            Content = content;
        }

        public ReplyBean(int accountId, String accountName, String headPicUrl, String content, int replyId, String replyName) {
            AccountId = accountId;
            AccountName = accountName;
            HeadPicUrl = headPicUrl;
            Content = content;
            ReplyId = replyId;
            ReplyName = replyName;
        }

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

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getReplyId() {
            return ReplyId;
        }

        public void setReplyId(int ReplyId) {
            this.ReplyId = ReplyId;
        }

        public String getReplyName() {
            return ReplyName;
        }

        public void setReplyName(String ReplyName) {
            this.ReplyName = ReplyName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }

    public static class PraiseBean {
        private int PraiseId;
        private String HeadPicUrl;

        public PraiseBean(int praiseId, String headPicUrl) {
            PraiseId = praiseId;
            HeadPicUrl = headPicUrl;
        }

        public int getPraiseId() {
            return PraiseId;
        }

        public void setPraiseId(int PraiseId) {
            this.PraiseId = PraiseId;
        }

        public String getHeadPicUrl() {
            return HeadPicUrl;
        }

        public void setHeadPicUrl(String HeadPicUrl) {
            this.HeadPicUrl = HeadPicUrl;
        }
    }
}
