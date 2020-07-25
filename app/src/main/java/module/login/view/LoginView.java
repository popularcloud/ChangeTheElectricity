package module.login.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;

import java.util.List;

import module.login.bean.LoadingImgBean;

public interface LoginView extends BaseView {

    void onLoginSuccess(BaseModel<Object> data);

    void ongetCodeSuccess(BaseModel<Object> data);

    void onGetDataFail();
}
