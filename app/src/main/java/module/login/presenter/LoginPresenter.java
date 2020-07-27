package module.login.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.net.ApiRetrofit;

import java.util.List;

import module.login.bean.LoginBean;
import module.login.view.LoginView;
import okhttp3.MultipartBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void getPhoneCode(String mobile){
        addDisposable(ApiRetrofit.getInstance().getApiService().getPhoneCode("vv/sms/api/index/send",mobile,"mobilebind"), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.ongetCodeSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

    public void loginByCode(String mobile,String code,String pid){
        addDisposable(ApiRetrofit.getInstance().getApiService().loginByCode("vv/usercenter/api/user/login","mobile",mobile,code,pid), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onLoginSuccess((BaseModel<LoginBean>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

    public void uploadPicToService(List<MultipartBody.Part> partList) {
        addDisposable(ApiRetrofit.getInstance().getApiService().upLoadPic(partList), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onUplaod((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    if("连接错误".equals(msg)){
                        baseView.onGetDataFail();
                    }else {
                        baseView.showError(msg);
                    }
                }
            }
        });
    }

}
