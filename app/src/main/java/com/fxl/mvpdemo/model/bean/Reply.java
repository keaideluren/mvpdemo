package com.fxl.mvpdemo.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by tcz on 2016/10/27.
 */
public class Reply implements Serializable, Parcelable {


    /**
     * Id
     * AccountId : 1507
     * AccountName : 风车
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1507/201608021620416424.jpg
     * Content : 哈哈哈第一条
     * ReplyId : null
     * ReplyName :
     * CreateTime : 2016-10-27
     * <p>
     * CommentId int 用户Id
     * CommentName string 用户昵称
     * ReplyId int 被回复人Id
     * ReplyName string 被回复人昵称
     * Content string 内容
     * CreateTime datetime 时间
     */

    private int AccountId;
    private String AccountName;
    private String HeadPicUrl;
    private String Content;
    private int ReplyId;
    private String ReplyName;
    private String CreateTime;
    private String CommentName;
    private int CommentId;
    private String Id;

    public Reply() {
    }

    public Reply(int accountId, String accountName, String content) {
        AccountId = accountId;
        AccountName = accountName;
        Content = content;
    }

    public Reply(int accountId, String accountName, String content, int replyId, String replyName) {
        AccountId = accountId;
        AccountName = accountName;
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

    public String getCommentName() {
        return CommentName;
    }

    public void setCommentName(String commentName) {
        CommentName = commentName;
    }

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "AccountId=" + AccountId +
                ", AccountName='" + AccountName + '\'' +
                ", HeadPicUrl='" + HeadPicUrl + '\'' +
                ", Content='" + Content + '\'' +
                ", ReplyId=" + ReplyId +
                ", ReplyName='" + ReplyName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", CommentName='" + CommentName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(AccountId);
        out.writeInt(CommentId);
        out.writeInt(ReplyId);
        out.writeString(AccountName);
        out.writeString(CommentName);
        out.writeString(ReplyName);
        out.writeString(Content);
        out.writeString(HeadPicUrl);
        out.writeString(CreateTime);
    }


    public static Creator<Reply> CREATOR = new Creator<Reply>() {
        public Reply createFromParcel(Parcel in) {
            Reply r = new Reply();
            r.setAccountId(in.readInt());
            r.setCommentId(in.readInt());
            r.setReplyId(in.readInt());
            r.setAccountName(in.readString());
            r.setCommentName(in.readString());
            r.setReplyName(in.readString());
            r.setContent(in.readString());
            r.setHeadPicUrl(in.readString());
            r.setCreateTime(in.readString());
            return r;
        }

        public Reply[] newArray(int size) {
            return new Reply[size];
        }
    };

}
