package com.younge.changetheelectricity.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import com.younge.changetheelectricity.R;

public class NavigationUtil {

    private static NavigationUtil instance;

    public static NavigationUtil getInstance(){
        if(instance == null){
            instance = new NavigationUtil();
        }
        return instance;
    }

    /**
     * 打开高德地图（公交出行，起点位置使用地图当前位置）
     *
     * t = 0（驾车）= 1（公交）= 2（步行）= 3（骑行）= 4（火车）= 5（长途客车）
     *
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     */
    private void openGaoDeMap(String dlat, String dlon, String dname,Context context) {
        if (checkMapAppsIsExist(context, "com.autonavi.minimap")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("androidamap://route?sourceApplication=" + R.string.app_name
                    + "&sname=我的位置&dlat=" + dlat
                    + "&dlon=" + dlon
                    + "&dname=" + dname
                    + "&dev=0&m=0&t=0"));
            context.startActivity(intent);
        } else {
            //ToastUtil.makeText(context, "高德地图未安装");
            openTencent(dlat,dlon,dname,context);
        }
    }

    /** 打开百度地图（公交出行，起点位置使用地图当前位置）
     * mode = transit（公交）、driving（驾车）、walking（步行）和riding（骑行）. 默认:driving
     * 当 mode=transit 时 ： sy = 0：推荐路线 、 2：少换乘 、 3：少步行 、 4：不坐地铁 、 5：时间短 、 6：地铁优先
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     **/
    public void openBaiduMap(String dlat, String dlon, String dname,Context context) {
        if (checkMapAppsIsExist(context, "com.baidu.BaiduMap")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("baidumap://map/direction?origin=我的位置&destination=name:"
                    + dname
                    + "|latlng:" + dlat + "," + dlon
                    + "&mode=driving&sy=3&index=0&target=1"));
            context.startActivity(intent);
        } else {
           // ToastUtil.makeText(context, "百度地图未安装");

            openGaoDeMap(dlat,dlon,dname,context);
        }
    }

    /**
     * 打开腾讯地图（公交出行，起点位置使用地图当前位置）
     *
     * 公交：type=bus，policy有以下取值
     * 0：较快捷 、 1：少换乘 、 2：少步行 、 3：不坐地铁
     * 驾车：type=drive，policy有以下取值
     * 0：较快捷 、 1：无高速 、 2：距离短
     * policy的取值缺省为0
     *
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     */
    private void openTencent(String dlat, String dlon, String dname,Context context) {
        if (checkMapAppsIsExist(context, "com.tencent.map")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("qqmap://map/routeplan?type=bus&from=我的位置&fromcoord=0,0"
                    + "&to=" + dname
                    + "&tocoord=" + dlat + "," + dlon
                    + "&policy=1&referer=myapp"));
            context.startActivity(intent);
        } else {
            ToastUtil.makeText(context, "腾讯地图未安装");
        }
    }


    /**
     * 检测地图应用是否安装
     *
     * @param context
     * @param packagename
     * @return
     */
    private boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }
}
