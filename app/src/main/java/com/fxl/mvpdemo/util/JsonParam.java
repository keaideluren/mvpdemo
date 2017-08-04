package com.fxl.mvpdemo.util;

import com.google.gson.Gson;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.myutils.AppUtils;
import com.fxl.mvpdemo.myutils.SignUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tencent.tls.tools.MD5;


/**
 * Created by tcz on 2016/5/21.
 * 键值对生成Json字符串
 */
public class JsonParam {

    private HashMap<String, Object> mKeyValueMap;
    private String cacheKey;

    private JsonParam(HashMap<String, Object> mKeyValueMap) {
        this.mKeyValueMap = mKeyValueMap;
    }

    public static JsonParam newInstance() {
        return new JsonParam(new HashMap<>());
    }

    /**
     * AccessToken string  访问令牌
     * Timestamp  string   时间戳
     * Nonce string 随机数
     * DeviceModel string 用户手机品牌
     * DeviceIMEI string 用户手机唯一标识码
     * DeviceSys string 用户手机系统版本
     * SoftwareVersion string 软件版本
     *
     * @return
     */
    public String J2S() {
        JSONObject jo = null;
        try {
            jo = new JSONObject();
            for (Map.Entry<String, Object> next : mKeyValueMap.entrySet()) {
                jo.put(next.getKey(), next.getValue());
            }
            String timeStep = "" + System.currentTimeMillis() / 1000;
            String random = SignUtils.getRandomString(6).toLowerCase();
            jo.put("AccessToken", SignUtils.getSign(timeStep, random))
                    .put("Timestamp", timeStep)
                    .put("Nonce", random)
                    .put("DeviceModel", AppUtils.deviceName)
                    .put("DeviceIMEI", AppUtils.imei)
                    .put("DeviceSys", AppUtils.sdkVersion)
                    .put("SignId", UserInfo.getInstance().getId())
                    .put("SoftwareVersion", AppUtils.appVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    public HashMap<String, Object> getMap() {
        cacheKey = MD5.toMD5(new Gson().toJson(mKeyValueMap));
        String timeStep = "" + System.currentTimeMillis() / 1000;
        String random = SignUtils.getRandomString(6).toLowerCase();
        mKeyValueMap.put("AccessToken", SignUtils.getSign(timeStep, random));
        mKeyValueMap.put("Timestamp", timeStep);
        mKeyValueMap.put("Nonce", random);
        mKeyValueMap.put("DeviceModel", AppUtils.deviceName);
        mKeyValueMap.put("DeviceIMEI", AppUtils.imei);
        mKeyValueMap.put("DeviceSys", AppUtils.sdkVersion);
        mKeyValueMap.put("SignId", UserInfo.getInstance().getId());
        mKeyValueMap.put("SoftwareVersion", AppUtils.appVersion);
        return mKeyValueMap;
    }

    /**
     * @return 这方法要在getMap之后执行
     */
    public String getCacheKey() {
        return cacheKey;
    }

    public HashMap<String, Object> getMapLoginAndRegister() {
        return mKeyValueMap;
    }

    public JsonParam add(String name, Object value) {
        mKeyValueMap.put(name, value);
        return this;
    }
}
