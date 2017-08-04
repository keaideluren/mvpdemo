package com.fxl.mvpdemo.myutils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/9/22.
 */
public class DistanceUtil {

    /**
     * 转换距离显示
     *
     * @param distance
     * @return B/KB/MB/GB
     */
    public static String formatDistance(Double distance) {//千米
        //java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String resultDistance = "";
        //LogUtils.e("distance"," orign:"+distance);
        Double mDistance = distance * 1000;
      //  LogUtils.e("distance"," orign1:"+mDistance);
        if (mDistance == 0.0) {
            resultDistance = "--km";
        } else if (mDistance < 100) {
            resultDistance = new BigDecimal(mDistance).setScale(0, BigDecimal.ROUND_HALF_UP).toString() + "m";
        } else if (mDistance > 100 && mDistance < 200) {
            resultDistance = "100m";
        } else if (mDistance > 100 && mDistance < 200) {
            resultDistance = "200m";
        } else if (mDistance > 200 && mDistance < 500) {
            resultDistance = "500m";
        } else if (mDistance > 500 && mDistance < 1000) {
            resultDistance = "1km";
        } else if (mDistance > 1000 && mDistance < 20000) {
            resultDistance =  new BigDecimal(mDistance/1000).setScale(1, BigDecimal.ROUND_HALF_UP).toString()+"km";
        } else if (mDistance > 20000) {
            resultDistance =  new BigDecimal(mDistance/1000).setScale(0, BigDecimal.ROUND_HALF_UP).toString()+"km";
        }else {
            resultDistance = "很远";
        }
       // LogUtils.e("distance","  result:"+resultDistance);
        return resultDistance;
    }
}