package module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import module.login.bean.LoginBean;
import module.login.presenter.LoginPresenter;
import module.login.view.LoginView;

public class LoginActivity extends MyBaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;
    @BindView(R.id.tv_code)
    TextView tv_code;

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


        SpannableString spanString = new SpannableString("《用户服务协议》");
        spanString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                intent.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=32");
                intent.putExtra("title","用户协议");
                startActivity(intent);
            }
        }, 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spanStringTwo = new SpannableString("《隐私政策法律法规》");
        spanStringTwo.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                intent.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=33");
                intent.putExtra("title","隐私协议");
                startActivity(intent);
            }
        }, 0, spanStringTwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cb_agree.setText("已同意");
        cb_agree.append(spanString);
        cb_agree.append("及");
        cb_agree.append(spanStringTwo);
        cb_agree.append("并同意动力风/智能充换电网络获取本机号码");
        cb_agree.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }


    @Override
    protected void initGetData() {
    }

    @Override
    protected void widgetListener() {

    }



    private int count = 60;
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (count == 0) {
                count = 60;
                tv_code.setEnabled(true);
                tv_code.setText("获取验证码");
                return;
            }
            tv_code.setText(count-- + "s");
            tv_code.setEnabled(false);
            handle.sendEmptyMessageDelayed(0, 1000);
        }
    };


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
                //startActivity(new Intent(this, MainActivity.class));
                mPresenter.loginByCode(phone,code,"");

                break;
        }
    }



    @Override
    public void onLoginSuccess(BaseModel<LoginBean> data) {
        ToastUtil.makeText(LoginActivity.this,"登录成功");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",data.getData().getToken());
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void ongetCodeSuccess(BaseModel<Object> data) {
        handle.sendEmptyMessageDelayed(0, 1000);
        ToastUtil.makeText(LoginActivity.this,"验证码已发送");
    }

    @Override
    public void onUplaod(BaseModel<Object> data) {

    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoginActivity.this,"获取失败");
    }
}
