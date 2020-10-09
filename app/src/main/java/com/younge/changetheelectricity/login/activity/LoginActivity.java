package com.younge.changetheelectricity.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.RegexUtils;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import com.younge.changetheelectricity.login.bean.LoginBean;
import com.younge.changetheelectricity.login.presenter.LoginPresenter;
import com.younge.changetheelectricity.login.view.LoginView;

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

    private UMShareAPI umShareAPI;

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

        umShareAPI = UMShareAPI.get(this);
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


    @OnClick({R.id.tv_code,R.id.tv_login,R.id.rl_fanhui_left,R.id.ll_weChat})
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
                if(!RegexUtils.validateMobilePhone(phone)){
                    ToastUtil.makeText(LoginActivity.this,"请输入正确的手机号");
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
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.ll_weChat:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN,authListener);
                break;
        }
    }

    /**
     * 第三方登录
     * @param data
     * @param name
     */
  /*  private void thirdLogin(final Map<String,String> data, String name){
        final Map<String,String> params = new HashMap<>();
        String uid = data.get("uid");

        switch (name){
            case "QQ":
                params.put("qqid",uid);
                params.put("loginTypeName","QQ");
                break;
            case "WEIXIN":
                params.put("openid",uid);
                params.put("loginTypeName","微信");
                break;
            case "SINA":
                params.put("weiboid",uid);
                params.put("loginTypeName","新浪");
                break;
        }

    }*/



    @Override
    public void onLoginSuccess(BaseModel<LoginBean> data) {
        ToastUtil.makeText(LoginActivity.this,"登录成功");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",data.getData().getToken());
        //startActivity(new Intent(this, MainActivity.class));

        mPresenter.getPersonalInfo(data.getData().getToken());
    }

    @Override
    public void onWeChatLoginSuccess(BaseModel<LoginBean> data) {
        ToastUtil.makeText(LoginActivity.this,"登录成功");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",data.getData().getToken());
        //startActivity(new Intent(this, MainActivity.class));

        mPresenter.getPersonalInfo(data.getData().getToken());
    }

    @Override
    public void ongetCodeSuccess(BaseModel<Object> data) {
        handle.sendEmptyMessageDelayed(0, 1000);
        ToastUtil.makeText(LoginActivity.this,"验证码已发送");
    }

    @Override
    public void onGetUserInfoSuccess(BaseModel<UserInfoBean> data) {
        UserInfoBean.UserinfoBean userinfoBean = data.getData().getUserinfo();
        String userInfoDetail = JsonUtil.parserObjectToGson(userinfoBean);
        SharedPreferencesUtils.setParam(this,"userInfoDetail",userInfoDetail);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onUplaod(BaseModel<Object> data) {

    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoginActivity.this,"获取失败");
    }


    /**####################第三方登录的回调########################**/

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.d("third_login","开始获取登录信息---->"+platform.name());
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // ToastUtil.showToast(LoginActivity.this,platform.name() + "action"+action);
            Log.d("third_login","获取登录信息成功----》"+platform.name() + "action"+action);
            for(String keys : data.keySet()){
                Log.d("third_login","获取的信息："+keys + "===" + data.get(keys));
            }

            String unionid = data.get("unionid");
            String openid = data.get("openid");
            String avatar = data.get("profile_image_url");
            String gender = data.get("gender");
            String nickname = data.get("name");

            mPresenter.wechatLogin(unionid,openid,avatar,gender,nickname,"");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtil.makeText(LoginActivity.this,"失败：" + t.getMessage());
            Log.d("third_login","获取登录信息失败----》"+platform.name() + t.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.makeText(LoginActivity.this,"取消了：");
            Log.d("third_login","取消了"+platform.name() + action);
        }
    };
}
