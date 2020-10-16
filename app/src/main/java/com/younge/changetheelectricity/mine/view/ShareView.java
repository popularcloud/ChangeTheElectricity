package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.changetheelectricity.Bean.AlipayBean;
import com.younge.changetheelectricity.mine.bean.PayByWechatBean;
import com.younge.changetheelectricity.mine.bean.ShareSettingBean;

public interface ShareView extends BaseView {

    void onGetShareSuccess(BaseModel<ShareSettingBean> data);

    void onGetDataFail();
}
