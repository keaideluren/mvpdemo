package com.fxl.mvpdemo.model.bean;

import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.db.UserDao;
import com.fxl.mvpdemo.myutils.SPUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 用户数据
 */
public class UserInfo implements Serializable {
    private int Id;
    private String Phone;
    private String AccountName;    // 呢称
    private String sig;
    private String HeadPicUrl;      // 头像
    private String AutoKey;
    private int Gender;
    private String Birthday;
    private String Signature;      // 签名
    private String Address;
    private String Emotional;
    private String Occupation;
    private int RegisterProgress;
    private String WallImg;
    private int Role;
    private List<String> Photos;
    private boolean IsFriend;
    private boolean IsThirdLogin;
    private String Token;

    private static UserInfo ourInstance;

    private static final byte[] lock = new byte[0];

    public static UserInfo getInstance() {
        if (ourInstance == null) {
            synchronized (lock) {
                if (ourInstance == null) {
                    ourInstance = UserDao.getInstance().findUserByUserId(
                            (int) SPUtils.get(LeplusApplication.getInstance()
                                    , Constant.ShareConstant.FILE_IS_FIRST
                                    , Constant.ShareConstant.LAST_USER_ID, 0));
                    if (ourInstance == null) {
                        ourInstance = new UserInfo();
                    }
                }
            }
        }
        return ourInstance;
    }

    /**
     * 专为登录成功后设置用的，其它地方不要调用
     *
     * @param instance 实例
     */
    public static void setInstance(UserInfo instance) {
        ourInstance = instance;
    }


    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAutoKey() {
        return AutoKey;
    }

    public void setAutoKey(String autoKey) {
        AutoKey = autoKey;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getEmotional() {
        return Emotional;
    }

    public void setEmotional(String emotional) {
        Emotional = emotional;
    }

    public int getRegisterProgress() {
        return RegisterProgress;
    }

    public void setRegisterProgress(int registerProgress) {
        RegisterProgress = registerProgress;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String userSig) {
        this.sig = userSig;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String nickName) {
        this.AccountName = nickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String sign) {
        this.Signature = sign;
    }

    public String getHeadPicUrl() {
        return HeadPicUrl;
    }

    public void setHeadPicUrl(String avatar) {
        this.HeadPicUrl = avatar;
    }

    public boolean isThirdLogin() {
        return IsThirdLogin;
    }

    public void setThirdLogin(boolean thirdLogin) {
        IsThirdLogin = thirdLogin;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }

    public String getWallImg() {
        return WallImg;
    }

    public void setWallImg(String wallImg) {
        WallImg = wallImg;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public List<String> getPhotos() {
        return Photos;
    }

    public void setPhotos(List<String> photos) {
        this.Photos = photos;
    }

    public boolean isFriend() {
        return IsFriend;
    }

    public void setIsFriend(boolean isFriend) {
        IsFriend = isFriend;
    }

    @Override
    public String toString() {
        return "MobUserInfo{" +
                "id=" + Id +
                ", Phone='" + Phone + '\'' +
                ", AccountName='" + AccountName + '\'' +
                ", userSig='" + sig + '\'' +
                ", HeadPicUrl='" + HeadPicUrl + '\'' +
                ", AutoKey='" + AutoKey + '\'' +
                ", Gender=" + Gender +
                ", Birthday='" + Birthday + '\'' +
                ", Signature='" + Signature + '\'' +
                ", Address='" + Address + '\'' +
                ", Emotional='" + Emotional + '\'' +
                ", Occupation='" + Occupation + '\'' +
                ", RegisterProgress=" + RegisterProgress +
                ", WallImg='" + WallImg + '\'' +
                ", Role=" + Role +
                ", photos=" + Photos +
                ", IsFriend=" + IsFriend +
                '}';
    }
}