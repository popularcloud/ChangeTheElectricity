package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.bean.PageInfoBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.MinePresenter;
import com.younge.changetheelectricity.mine.view.MineView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import com.younge.changetheelectricity.login.activity.LoginActivity;
import com.younge.changetheelectricity.login.activity.WebViewActivity;

/**
 * 设置
 */
public class SettingActivity extends MyBaseActivity<MinePresenter> implements MineView {

	@BindView(R.id.tv_exit)
    TextView tv_exit;
	@BindView(R.id.txt_cache_size)
    TextView txt_cache_size;


	private UserInfoBean user;

	@Override
	protected MinePresenter createPresenter() {
		return new MinePresenter(this);
	}

	@Override
	protected int getContentViewId(Bundle savedInstanceState) {
		return R.layout.activity_setting;
	}

	@Override
	protected void init() {
		setTitle("设置");
	}

	@Override
	protected void initGetData() {
		txt_cache_size.setText(getAppCache());
	}

	@Override
	protected void widgetListener() {

	}

	@Override
	public void onGetDataSuccess(BaseModel<UserInfoBean> data) {

	}

	@Override
	public void onGetAboutUsSuccess(BaseModel<PageInfoBean> data) {

		if(data != null && data.getData() != null && data.getData().getPageInfo()!=null){
			Intent intent3 = new Intent(SettingActivity.this, WebViewActivity.class);
			intent3.putExtra("content",data.getData().getPageInfo().getContent());
			intent3.putExtra("title",data.getData().getPageInfo().getTitle());
			startActivity(intent3);
		}else{
			ToastUtil.makeText(this,"获取数据失败");
		}
	}

	@Override
	public void onGetDataFail() {

	}

	@OnClick({R.id.rl_fanhui_left,R.id.tv_exit,R.id.txt_agreement,R.id.txt_privacy,R.id.rl_clear_cache,R.id.txt_about_us})
	public void onBtnClick(View view){
		switch (view.getId()){
			case R.id.rl_fanhui_left:
				finish();
				break;
			case R.id.tv_exit:
				SharedPreferencesUtils.setParam(SettingActivity.this,"token","");
				startActivity(new Intent(this, LoginActivity.class));
				finish();
				break;
			case R.id.txt_agreement:
				Intent intent = new Intent(SettingActivity.this, WebViewActivity.class);
				intent.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=32");
				intent.putExtra("title","用户协议");
				startActivity(intent);
				break;
			case R.id.txt_privacy:
				Intent intent2 = new Intent(SettingActivity.this, WebViewActivity.class);
				intent2.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=33");
				intent2.putExtra("title","隐私协议");
				startActivity(intent2);
				break;
			case R.id.rl_clear_cache:
				showLoadingDialog("清理中...");
				clearAppCache();
				break;
			case R.id.txt_about_us:
				mPresenter.aboutUs((String) SharedPreferencesUtils.getParam(this,"token",""));
				break;
		}
	}

	/**
	 * 获取app的缓存大小
	 * 4. 缓存/storage/emulated/0/Android/data/com.sdxzt.xueliangapp_v3/cache
	 */
	File cacheDir;
	private String getAppCache(){
		long fileSize = 0;
		String cacheSize = "0KB";
		cacheDir = getExternalCacheDir();
		fileSize += getDirSize(cacheDir);

		cacheSize = formatFileSize(fileSize);
		//Log.d(TAG, "getAppCache: 总缓存大小: "+fileSizeStr);
		return cacheSize;
	}

	/**
	 * 获取文件大小(字节为单位)
	 * @param dir
	 * @return
	 */
	private long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();//文件的长度就是文件的大小
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 格式化文件长度
	 * @param fileSize
	 * @return
	 */
	private String formatFileSize(long fileSize){
		DecimalFormat df = new DecimalFormat("#0.00");//表示小数点前至少一位,0也会显示,后保留两位

		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "KB";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public void clearAppCache(){

		final Handler handler = new Handler(){
			@Override
			public void handleMessage(@NonNull Message msg) {
				super.handleMessage(msg);

				dissMissDialog();

				if (msg.what == 1) {
					txt_cache_size.setText(getAppCache());
					ToastUtil.makeText(SettingActivity.this,"缓存清除完毕");
				}else {
					ToastUtil.makeText(SettingActivity.this,"缓存清除失败");
				}
			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				try {
					clearCacheFolder(cacheDir,System.currentTimeMillis());
					msg.what = 1;
				}catch (Exception e){
					e.printStackTrace();
					msg.what = -1;
				}
				handler.sendMessage(msg);
			}
		}).start();
	}


	/**
	 * 清除缓存目录
	 * @param dir 目录
	 * @param curTime 当前系统时间
	 */
	private int clearCacheFolder(File dir,long curTime){
		int deletedFiles = 0;
		if (dir!= null && dir.isDirectory()) {
			try {
				for (File child:dir.listFiles()) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, curTime);
					}
					if (child.lastModified() < curTime) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}
}
