package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;

public interface DepositHistoryView extends BaseView {

    void onGetDepositHistorySuccess(BaseModel<DepositHistoryBean> data);

    void onGetDataFail();
}
