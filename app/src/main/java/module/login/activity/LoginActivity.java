package module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.mine.activity.MyWalletActivity;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import module.login.presenter.LoginPresenter;
import module.login.view.LoginView;

public class LoginActivity extends MyBaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();
    private String phone;
    private String code;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }


    @Override
    protected void initGetData() {
    }

    @Override
    protected void widgetListener() {

    }


    @OnClick({R.id.tv_code,R.id.tv_login})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_code:

                phone = et_phone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.makeText(LoginActivity.this,"请输入手机号");
                    return;
                }

                mPresenter.getPhoneCode(phone);

                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString().trim();
               // code = et_code.getText().toString().trim();
                code = "1111";
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.makeText(LoginActivity.this,"请输入手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.makeText(LoginActivity.this,"请输入验证码");
                    return;
                }

                if(!cb_agree.isChecked()){
                    ToastUtil.makeText(LoginActivity.this,"请同意以下服务协议");
                    return;
                }
                startActivity(new Intent(this, MainActivity.class));
               // mPresenter.loginByCode(phone,code,"");
                break;
        }
    }

    @Override
    public void onLoginSuccess(BaseModel<Object> data) {
        ToastUtil.makeText(LoginActivity.this,"登录成功");
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void ongetCodeSuccess(BaseModel<Object> data) {
        ToastUtil.makeText(LoginActivity.this,"验证码已发送");
    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoginActivity.this,"获取轮播图失败");
    }
}
