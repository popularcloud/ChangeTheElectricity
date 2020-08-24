package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.AlipayBean;
import com.younge.changetheelectricity.mine.bean.MyWxBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;

import java.util.List;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface RechargeCenterView extends BaseView {

    void onGetDataSuccess(BaseModel<PackageBean> data);

    void onPayOrderByAliSuccess(BaseModel<AlipayBean> data);

    void onPayOrderByWeChatSuccess(BaseModel<PayByWechatBean> data);

    void onGetDataFail();
}
