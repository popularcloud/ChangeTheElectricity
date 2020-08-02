package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;

public interface PayView extends BaseView {

    void onGetPaySuccess(BaseModel<Object> data);

    void onGetDataFail();
}
