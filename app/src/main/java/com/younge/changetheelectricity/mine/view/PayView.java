package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.AlipayBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;

public interface PayView extends BaseView {

    void onPayOrderByWallet(BaseModel<Object> data);

    void onPayOrderByAliSuccess(BaseModel<AlipayBean> data);

    void onPayOrderByWeChatSuccess(BaseModel<PayByWechatBean> data);

    void onGetDataFail();
}
