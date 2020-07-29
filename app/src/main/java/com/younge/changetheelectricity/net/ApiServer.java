package com.younge.changetheelectricity.net;


import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import module.login.bean.LoadingImgBean;
import module.login.bean.LoginBean;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServer {

    //获取轮播图
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/cms/api/index/banner")
    Observable<BaseModel<List<LoadingImgBean>>> getLoadingBanner(@Field("HTTP_API") String httpApi);

    //验证码
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/sms/api/index/send")
    Observable<BaseModel<Object>> getPhoneCode(@Field("HTTP_API") String httpApi,@Field("mobile") String mobile,@Field("event") String event);

    //手机短信登录
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/user/login")
    Observable<BaseModel<LoginBean>> loginByCode(@Field("HTTP_API") String httpApi, @Field("platform") String platform,
                                                 @Field("mobile") String mobile, @Field("smscode") String smscode, @Field("pid") String pid);

    //手机一键登录
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/sms/api/index/send")
    Observable<BaseModel<Object>> loginFast(@Field("HTTP_API") String httpApi,@Field("platform") String platform,
                                               @Field("mobile") String mobile,@Field("pid") String pid);

    //获取附近网点
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/device/api/index/index")
    Observable<BaseModel<Object>> getShopLocations(@Field("HTTP_API") String httpApi,
                                            @Field("lng") String lng,@Field("lat") String lat);

    //获取个人资料
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/user/profile")
    Observable<BaseModel<UserInfoBean>> getPersonalInfo(@Field("HTTP_API") String httpApi,
                                                        @Field("token") String token);

    //获取我的车辆
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/car/car")
    Observable<BaseModel<MyCarBean>> getMyCar(@Field("HTTP_API") String httpApi,
                                              @Field("page") String page,
                                              @Field("token") String token);

    //车辆添加
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/car/car_edit")
    Observable<BaseModel<UserInfoBean>> addCar(@Field("HTTP_API") String httpApi,
                                                 @Field("carvin") String carvin,
                                                 @Field("serial") String serial,
                                               @Field("carno") String carno,
                                               @Field("picfront") String picfront,
                                               @Field("picback") String picback,
                                               @Field("picleft") String picleft,
                                               @Field("picright") String picright,
                                               @Field("token") String token);

    //上传图片
    @Multipart
    @POST("/api/index")
    @Headers("HTTP_API: api/common/upload")
    Observable<BaseModel<ReturnImgUrlBean>> upLoadPic(@Part() List<MultipartBody.Part> partList);

    //套餐列表
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/package/api/index/index")
    Observable<BaseModel<PackageBean>> getPackage(@Field("HTTP_API") String httpApi,
                                                  @Field("type") String type,
                                                  @Field("page") String page,
                                                  @Field("size") String size);

}
