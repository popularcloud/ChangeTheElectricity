package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface RealNameAuthenticationCenterView extends BaseView {

    void onSubmitSuccess(BaseModel<Object> data);

    void onUploadPicSuccess(BaseModel<ReturnImgUrlBean> data);

    void onGetDataFail();
}
