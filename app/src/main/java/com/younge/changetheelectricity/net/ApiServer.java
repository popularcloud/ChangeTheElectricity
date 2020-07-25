package com.younge.changetheelectricity.net;


import com.younge.changetheelectricity.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;
import module.login.bean.LoadingImgBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    //获取轮播图
    @POST("/api/index")
    @FormUrlEncoded
    Observable<BaseModel<List<LoadingImgBean>>> getLoadingBanner(@Field("HTTP_API") String httpApi
    );

    //验证码
    @POST("/api/index")
    @FormUrlEncoded
    Observable<BaseModel<Object>> getPhoneCode(@Field("HTTP_API") String httpApi,@Field("mobile") String mobile,@Field("event") String event);

    //手机短信登录
    @POST("/api/index")
    @FormUrlEncoded
    Observable<BaseModel<Object>> loginByCode(@Field("HTTP_API") String httpApi,@Field("platform") String platform,
                                               @Field("mobile") String mobile,@Field("smscode") String smscode,@Field("pid") String pid);

    //手机一键登录
    @POST("/api/index")
    @FormUrlEncoded
    Observable<BaseModel<Object>> loginFast(@Field("HTTP_API") String httpApi,@Field("platform") String platform,
                                               @Field("mobile") String mobile,@Field("pid") String pid);

    //获取附近网点
    @POST("/api/index")
    @FormUrlEncoded
    Observable<BaseModel<Object>> getShopLocations(@Field("HTTP_API") String httpApi,
                                            @Field("lng") String lng,@Field("lat") String lat);

}
