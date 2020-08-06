package com.younge.changetheelectricity.net;


import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.main.bean.BatteryInfoBean;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.main.bean.ShopDetailBean;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.MyWxBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
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
    Observable<BaseModel<ShopDetailLocationBean>> getShopLocations(@Field("HTTP_API") String httpApi,
                                                                   @Field("lng") String lng, @Field("lat") String lat);


    //设备详情
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/device/api/index/show")
    Observable<BaseModel<DeviceDetailBean>> getDeviceDetail(@Field("HTTP_API") String httpApi,
                                                            @Field("lng") String lng,
                                                            @Field("lat") String lat,
                                                            @Field("macno") String macno,
                                                            @Field("token") String token);

    //店铺详情
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/agent/api/index/seller_show")
    Observable<BaseModel<ShopDetailBean>> getShopDetail(@Field("HTTP_API") String httpApi,
                                                        @Field("lng") String lng,
                                                        @Field("lat") String lat,
                                                        @Field("macno") String macno,
                                                        @Field("id") String admin_id);


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


    //实名认证
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/user/edit")
    Observable<BaseModel<Object>> realNameAuthentication(@Field("HTTP_API") String httpApi,
                                               @Field("realname") String realname,
                                               @Field("idcard") String idcard,
                                               @Field("idcardfront") String idcardfront,
                                               @Field("idcardback") String idcardback,
                                               @Field("idcardhand") String idcardhand,
                                               @Field("token") String token);


    //套餐列表
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/package/api/index/index")
    Observable<BaseModel<PackageBean>> getPackage(@Field("HTTP_API") String httpApi,
                                                  @Field("type") String type,
                                                  @Field("page") String page,
                                                  @Field("size") String size);

    //套餐详情
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/package/api/index/show")
    Observable<BaseModel<PackageBean>> getPackageDetail(@Field("HTTP_API") String httpApi,
                                                  @Field("id") String id);


    //充值下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/recharge/api/recharge/submit")
    Observable<BaseModel<PayByWechatBean>> rechargeOrderByWechat(@Field("HTTP_API") String httpApi,
                                                                 @Field("type") String type,
                                                                 @Field("package_id") String package_id,
                                                                 @Field("paytype") String paytype,
                                                                 @Field("method") String method,
                                                                 @Field("token") String token);

    //充值下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/recharge/api/recharge/submit")
    Observable<BaseModel<Object>> rechargeOrderByAli(@Field("HTTP_API") String httpApi,
                                                             @Field("type") String type,
                                                             @Field("package_id") String package_id,
                                                             @Field("paytype") String paytype,
                                                             @Field("method") String method,
                                                             @Field("token") String token);

    //套餐下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/cms/api/archives/order")
    Observable<BaseModel<PayByWechatBean>> PackageOrderByWechat(@Field("HTTP_API") String httpApi,
                                                     @Field("coupon_id") String coupon_id,
                                                     @Field("type") String type,
                                                     @Field("goodslist") String goodslist,
                                                     @Field("paytype") String paytype,
                                                     @Field("method") String method,
                                                     @Field("token") String token);

    //套餐下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/cms/api/archives/order")
    Observable<BaseModel<Object>> PackageOrderByAli(@Field("HTTP_API") String httpApi,
                                                       @Field("coupon_id") String coupon_id,
                                                       @Field("type") String type,
                                                       @Field("goodslist") String goodslist,
                                                       @Field("paytype") String paytype,
                                                       @Field("method") String method,
                                                       @Field("token") String token);

    //套餐下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/cms/api/archives/order")
    Observable<BaseModel<Object>> PackageOrderByWallet(@Field("HTTP_API") String httpApi,
                                                       @Field("coupon_id") String coupon_id,
                                                       @Field("type") String type,
                                                       @Field("goodslist") String goodslist,
                                                       @Field("paytype") String paytype,
                                                       @Field("method") String method,
                                                       @Field("token") String token);

    //我的套餐
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/package/api/index/my")
    Observable<BaseModel<PackageBean>> myPackageOrder(@Field("HTTP_API") String httpApi,
                                                    @Field("type") String type,
                                                    @Field("status") String status,
                                                    @Field("page") String page,
                                                    @Field("size") String size,
                                                    @Field("token") String token);


    //我的电池
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/car/battery")
    Observable<BaseModel<MyBatteryBean>> getMyBattery(@Field("HTTP_API") String httpApi,
                                                      @Field("page") String page,
                                                      @Field("size") String size,
                                                      @Field("token") String token);


    //根据sn获取电池信息
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/car/battery_search")
    Observable<BaseModel<BatteryInfoBean>> getBatteryInfo(@Field("HTTP_API") String httpApi,
                                                          @Field("sn") String sn,
                                                          @Field("token") String token);


    //电池添加/编辑
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/usercenter/api/car/battery_edit")
    Observable<BaseModel<BatteryInfoBean>> addBattery(@Field("HTTP_API") String httpApi,
                                                          @Field("sn") String sn,
                                                          @Field("token") String token);


    //充值明细
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/recharge/api/recharge/moneylog")
    Observable<BaseModel<DepositHistoryBean>> getDepositHistory(@Field("HTTP_API") String httpApi,
                                                                @Field("type") String type,
                                                                @Field("page") String page,
                                                                @Field("size") String size,
                                                                @Field("token") String token);

    //下单
    @POST("/api/index")
    @FormUrlEncoded
    @Headers("HTTP_API: vv/order/api/index/submit")
    Observable<BaseModel<DepositHistoryBean>> submitOrder(@Field("HTTP_API") String httpApi,
                                                                @Field("macno") String macno,
                                                                @Field("device_box") String device_box,
                                                                @Field("goods_type") String goods_type,
                                                                @Field("order_type") String order_type,
                                                                @Field("package_id") String package_id,
                                                                @Field("package_user_id") String package_user_id,
                                                                @Field("token") String token);

}
