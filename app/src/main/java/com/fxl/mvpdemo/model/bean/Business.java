package com.fxl.mvpdemo.model.bean;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcz on 2016/7/26.
 */
public class Business implements Serializable {


    /**
     * BusinessId int 商户Id
     * BusinessLogo string logo
     * BusinessCover string 封面图
     * BusinessName string 商户名
     * BusinessDes string 商户说明
     * Notice string 商户公告
     * Tel string 商家电话
     * Address string 商家电话
     * CreateTime datetime 入驻时间
     * HasFollow bool 是否关注（true：是）
     * BusinessTime string 营业时间
     * FollowCount int 感兴趣人数
     * CommentCount int 评论次数
     * Type int 类型 （1：酒吧 ，2：ktv，3：慢摇吧，4：演艺吧，5：商务会所，6：清吧）
     * <p>
     * ActivityList 活动列表：
     * <p>
     * <p>
     * FollowList 感兴趣的人
     * AccountId int 用户id
     * HeadPicUrl string 头像
     * <p>
     * CommentList 留言列表
     * AccountId int 用户Id
     * AccountName string 用户名
     * HeadPicUrl string 头像
     * Content string 内容
     * CreateTime datetime 留言时间
     * picture array 数组
     * <p>
     * ReplyList：回复数据
     * AccountId int 用户Id
     * AccountName string 用户昵称
     * Content string 内容
     * CreateTime datetime 时间
     * SaleRate float 打折率
     */

    private int BusinessId;
    private String BusinessLogo;
    private String BusinessName;
    private String BusinessCover;
    private String BusinessKey;
    private String BusinessDes;
    private String Notice;
    private String Tel;
    private String Address;
    private String CreateTime;
    private String BusinessTime;
    private boolean HasFollow;
    private int FollowCount;
    private int CommentCount;
    private int Type;
    private List<String> WallImgs;
    private List<Campaign> ActivityList;
    private String SaleRate;
    private Double Distance;
    private int Fans;
    private boolean IsFans;
    private double Longitude;
    private double Latitude;
    private String ChatId;

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public Business() {
    }

    /**
     * AccountId : 1000
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1000/201607261713024166.jpg
     */

    private List<FollowListBean> FollowList;
    /**
     * AccountId : 1000
     * AccountName : 寻乐宝小助手
     * HeadPicUrl : http://o7rvvizw0.bkt.clouddn.com/1000/201607261713024166.jpg
     * Content : 60°酒吧是一家在成都开了很久的店了～就在我们公司附近，公司聚餐后大多数去的最多的地方，就在香槟广场这边2楼上～珍贵装修不算新颖也不是很新毕竟是老店咯，音响效果还将就，包间布局指路牌也比较清晰
     * picture : []
     * CreateTime : 2016-07-27T11:47:37
     */

    private List<Comment> CommentList;

    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int BusinessId) {
        this.BusinessId = BusinessId;
    }

    public String getBusinessLogo() {
        return BusinessLogo;
    }

    public void setBusinessLogo(String BusinessLogo) {
        this.BusinessLogo = BusinessLogo;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getBusinessCover() {
        return BusinessCover;
    }

    public String getBusinessKey() {
        return BusinessKey;
    }

    public void setBusinessKey(String businessKey) {
        BusinessKey = businessKey;
    }

    public void setBusinessCover(String businessCover) {
        BusinessCover = businessCover;
    }

    public String getBusinessDes() {
        return BusinessDes;
    }

    public void setBusinessDes(String BusinessDes) {
        this.BusinessDes = BusinessDes;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String Notice) {
        this.Notice = Notice;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
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

    public String getBusinessTime() {
        return BusinessTime;
    }

    public void setBusinessTime(String BusinessTime) {
        this.BusinessTime = BusinessTime;
    }

    public boolean isHasFollow() {
        return HasFollow;
    }

    public void setHasFollow(boolean HasFollow) {
        this.HasFollow = HasFollow;
    }

    public int getFollowCount() {
        return FollowCount;
    }

    public void setFollowCount(int FollowCount) {
        this.FollowCount = FollowCount;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public List<String> getWallImgs() {
        return WallImgs;
    }

    public void setWallImgs(List<String> WallImgs) {
        this.WallImgs = WallImgs;
    }

    public List<FollowListBean> getFollowList() {
        return FollowList;
    }

    public void setFollowList(List<FollowListBean> FollowList) {
        this.FollowList = FollowList;
    }

    public List<Comment> getCommentList() {
        return CommentList;
    }

    public void setCommentList(List<Comment> CommentList) {
        this.CommentList = CommentList;
    }

    public List<Campaign> getActivityList() {
        return ActivityList;
    }

    public void setActivityList(List<Campaign> activityList) {
        ActivityList = activityList;
    }

    public String getSaleRate() {
        return SaleRate;
    }

    public void setSaleRate(String saleRate) {
        SaleRate = saleRate;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    public int getFans() {
        return Fans;
    }

    public void setFans(int fans) {
        Fans = fans;
    }

    public boolean isFans() {
        return IsFans;
    }

    public void setFans(boolean fans) {
        IsFans = fans;
    }

    public String getChatId() {
        return ChatId;
    }

    public void setChatId(String chatId) {
        ChatId = chatId;
    }

    public static class FollowListBean implements Serializable {
        private int Id;
        private String HeadPicUrl;

        public FollowListBean(int id, String headPicUrl) {
            Id = id;
            HeadPicUrl = headPicUrl;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getHeadPicUrl() {
            return HeadPicUrl;
        }

        public void setHeadPicUrl(String HeadPicUrl) {
            this.HeadPicUrl = HeadPicUrl;
        }
    }

    /**
     * ActivityId long 活动Id (跳转：139.224.75.166:8090/activity/index/ActivityId)
     * ActivityCover string 活动封面图
     */
    public static class Campaign {
        private long ActivityId;
        private String ActivityCover;
        private boolean NeedPay;

        public long getActivityId() {
            return ActivityId;
        }

        public void setActivityId(long activityId) {
            ActivityId = activityId;
        }

        public String getActivityCover() {
            return ActivityCover;
        }

        public void setActivityCover(String activityCover) {
            ActivityCover = activityCover;
        }

        public boolean isNeedPay() {
            return NeedPay;
        }

        public void setNeedPay(boolean needPay) {
            NeedPay = needPay;
        }
    }
}
