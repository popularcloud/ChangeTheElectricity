package com.younge.changetheelectricity.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.younge.changetheelectricity.R;


/**
 * 自定义对话框
 * 
 */
public class PayTypeDialog extends Dialog implements View.OnClickListener{

	/** 上下文 */
	private Context context;
	CallBack callBack;

	RelativeLayout rl_qb;
	RelativeLayout rl_wechat;
	RelativeLayout rl_alipay;
	LinearLayout ll_pay_type;
	LinearLayout ll_pay_pwd;
	ImageView ic_close;

	private int payType = 1; //1钱包 2.支付宝 3.微信


	private boolean isEnableMoney = true;

	public PayTypeDialog(Context context, String payPrice, CallBack callBack) {
		super(context, R.style.BottomDialogStyle);
		// 拿到Dialog的Window, 修改Window的属性
		Window window = getWindow();
		window.getDecorView().setPadding(0, 0, 0, 0);
		// 获取Window的LayoutParams
		WindowManager.LayoutParams attributes = window.getAttributes();
		attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
		attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
		// 一定要重新设置, 才能生效
		window.setAttributes(attributes);
		this.callBack = callBack;
		init(context,payPrice);
	}

	/**
	 *
	 * @param context
	 * @param theme
     */
	public PayTypeDialog(Context context, int theme) {
		super(context, theme);
		init(context,null);
	}

	public PayTypeDialog(Context context) {
		super(context, R.style.dialog_style);
		init(context,null);
	}

	/**
	 * 初始话对话框
	 * 
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param context
	 * 
	 */
	protected void init(final Context context, String payPrice) {
		this.context = context;
		this.setCancelable(true);
		this.setCanceledOnTouchOutside(true);
		this.setContentView(R.layout.dialog_pay_type);

		rl_qb = findViewById(R.id.rl_qb);
		rl_wechat = findViewById(R.id.rl_wechat);
		rl_alipay = findViewById(R.id.rl_alipay);
		ll_pay_type = findViewById(R.id.ll_pay_type);
		ic_close = findViewById(R.id.ic_close);

		ic_close.setOnClickListener(this);
		widgetListener();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ic_close:
				dismiss();
				if(callBack != null){
					callBack.onColse();
				}
				break;
			case R.id.rl_qb:
				payType = 1;
				callBack.onSubmit(payType,null);
				break;
			case R.id.rl_wechat:
				//changeLayout(0);
				payType = 3;
				callBack.onSubmit(payType,null);

				break;
			case R.id.rl_alipay:
				//changeLayout(0);
				payType = 2;
				callBack.onSubmit(payType,null);
				break;

		}
	}

	public interface CallBack {
		void onSubmit(int payType, String password);
		void onColse();
	}



	protected void widgetListener() {
		rl_qb.setOnClickListener(this);
		rl_wechat.setOnClickListener(this);
		rl_alipay.setOnClickListener(this);
	}
}
