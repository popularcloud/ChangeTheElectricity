package module.login.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;

import module.login.bean.LoginBean;

public interface LoginView extends BaseView {

    void onLoginSuccess(BaseModel<LoginBean> data);

    void ongetCodeSuccess(BaseModel<Object> data);

    void onGetUserInfoSuccess(BaseModel<UserInfoBean> data);

    void onUplaod(BaseModel<Object> data);

    void onGetDataFail();
}
