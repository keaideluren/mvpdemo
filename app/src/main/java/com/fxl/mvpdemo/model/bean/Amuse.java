package com.fxl.mvpdemo.model.bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 * 娱乐圈，一条内容
 */

public class Amuse implements Serializable {

    /**
     * Id long 娱乐圈Id
     AccountId int 用户Id
     AccountName string 用户昵称
     HeadPicUrl string 用户头像
     Content string 内容
     Type int 类型(1:图片 2：视频 3：链接->使用LinkInfo 4：纯文字)
     Address string 地址
     Medias array 图片/视频地址数组
     CreateTime datetime 创建时间
     PraiseCount int 点赞次数
     ReplyCount int 回复次数
     HasPraise bool 是否点赞

     ReplyList：回复数据
     CommentId int 用户Id
     CommentName string 用户昵称
     ReplyId int 被回复人Id
     ReplyName string 被回复人昵称
     Content string 内容
     CreateTime datetime 时间

     LinkInfo：链接数据
     LinkId long 链接Id
     Cover string 封面图
     Title string 标题
     *
     * Id : 2
     * AccountId : 1000
     * AccountName : 寻乐宝小助手
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1000/201609081149446275.jpg
     * Content : 测试2号
     * Type : 4
     * Address : 成都环球中心
     * Medias : []
     * VodThu:String 封面图
     * CreateTime : 2016-12-14
     * PraiseCount : 1
     * CommentCount : 1
     * HasPraise : true
     * ReplyList : [{"CommentId":1000,"CommentName":"寻乐宝小助手","Content":"天王盖地虎，宝塔镇河妖，2333","ReplyId":null,"ReplyName":"","CreateTime":"2016-12-14"}]
     * LinkInfo : {"LinkId":0,"Cover":"","Title":""}
     *
     * FansInfo:粉丝数据
     FansCount int 粉丝数
     IsFans bool 是否粉丝（true:是）
     */

    private int Id;
    private int AccountId;
    private String AccountName;
    private String HeadPicUrl;
    private String Content;
    private int Type;
    private String Address;
    private String CreateTime;
    private int PraiseCount;
    private int CommentCount;
    private boolean HasPraise;
    private LinkInfo LinkInfo;
    private List<String> Medias;
    private List<Reply> ReplyList;
    private String VodThu;
    private FansInfo FansInfo;
    private int isFirst ;
    private Bitmap vodThumb;

    private double progressValue;//上传中的视频专用


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(int PraiseCount) {
        this.PraiseCount = PraiseCount;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int CommentCount) {
        this.CommentCount = CommentCount;
    }

    public boolean isHasPraise() {
        return HasPraise;
    }

    public void setHasPraise(boolean HasPraise) {
        this.HasPraise = HasPraise;
    }

    public LinkInfo getLinkInfo() {
        return LinkInfo;
    }

    public void setLinkInfo(LinkInfo LinkInfo) {
        this.LinkInfo = LinkInfo;
    }

    public List<String> getMedias() {
        return Medias;
    }

    public void setMedias(List<String> Medias) {
        this.Medias = Medias;
    }

    public List<Reply> getReplyList() {
        return ReplyList;
    }

    public void setReplyList(List<Reply> ReplyList) {
        this.ReplyList = ReplyList;
    }

    public String getVodThu() {
        return VodThu;
    }

    public void setVodThu(String vodThu) {
        VodThu = vodThu;
    }

    public FansInfo getFansInfo() {
        return FansInfo;
    }

    public void setFansInfo(FansInfo fansInfo) {
        FansInfo = fansInfo;
    }

    public double getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(double progressValue) {
        this.progressValue = progressValue;
    }

    public int isFirst() {
        return isFirst;
    }

    public void setFirst(int first) {
        isFirst = first;
    }

    public Bitmap getVodThumb() {
        return vodThumb;
    }

    public void setVodThumb(Bitmap vodThumb) {
        this.vodThumb = vodThumb;
    }
}
