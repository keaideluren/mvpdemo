package com.fxl.mvpdemo.model;

/**
 * 常量
 */
public interface Constant {

    boolean IS_DEBUG = true;//控制使用正式版的Im服务器，Qiniu服务器，是否输出日志，以及推送服务器
    int MAX_CACHE_SIZE = 1024 * 1024 * 100;//最大缓存空间
    int MAX_NET_CACHE_TIME = 60 * 60 * 24 * 28;//无网络时最大缓存时间
    int PAGE_SIZE = 20;//默认分页大小
    //正式用，腾讯Im
    int ACCOUNT_TYPE = 6011;
    //sdk appid 由腾讯分配
    int SDK_APPID = IS_DEBUG ? 1400012234 : 1400012234;

    /**
     * 应用内广播IntentFilter
     */
    String INTENT_WECHAT_SUCCESS = "com.fxl.mvpdemo.wechatSuccess";

    /**
     * 要使用的服务器Ip
     */
    //不带api/v1/的一般是WebView地址
    String BBASE_URL = IS_DEBUG ? "http://120.27.132.205:8080/" : "http://120.27.132.205:8080/";
    //文件服务器地址
    String QINIUHOST = IS_DEBUG ? "http://o7rvvizw0.bkt.clouddn.com/" : "http://o7rvvizw0.bkt.clouddn.com/";
    //带api/v1/的，接口地址
    String BASE_URL = BBASE_URL+"api/v1/";

    /**
     * 个人数据
     */
    String USER = "Account/";
    //登录
    String LOGIN = USER + "Login";
    //自动登录
    String AUTO_LOGIN = USER + "AutoLogin";

    /**
     * 商家数据
     */
    String BUSINESS = "Business/";
    //获得首页数据 Business + "BusinessIndexV2"
    String HOME = BUSINESS + "BusinessIndexV2";

    /**
     * 约耍圈
     */
    String ENTERAINMENT = BASE_URL + "Entertainment/";
    ///Entertainment/EntertainmentsList 娱乐圈列表
    String ENTERAINMENT_LIST = ENTERAINMENT + "EntertainmentsList";
    //约耍圈详情Entertainment/EnterainmentDetail
    String AMUSE_DETAIL = ENTERAINMENT + "EnterainmentDetail";

    /**
     * 活动
     */
    String ACTIVITYA = "ActivityA/";
    //活动列表
    String TAKE_ACITIVITYS = ACTIVITYA + "TakeActivities";

    /**
     * 网络请求参数
     */
    //登录手机号
    String PHONE = "Phone";
    //登录密码
    String ACCOUNT_PWD = "AccountPwd";
    //自动登录Key
    String AUTO_KEY = "AutoKey";
    //登录设备类型
    String DEVICE_TYPE = "DevicesType";
    /**
     * sharePreferences的常量
     */
    interface ShareConstant{
        //文件名
        String FILE_IS_FIRST = "isFirst";
        //引导页是否是第一次进入
        String IS_GUIDE_FIRST = "isGuidFirst";
        // 最后登录的用户Id
        String LAST_USER_ID = "lastUserId";
    }
}

