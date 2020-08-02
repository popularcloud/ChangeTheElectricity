package com.younge.changetheelectricity.mine.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 支付信息
 * 
 * @Description TODO
 * @author 何栋
 * @date 2016年4月24日
 * @Copyright: lwc
 */
public class MyWxBean implements Serializable {

	/** 变量描述 */
	private static final long serialVersionUID = 1L;

	/**
	 * appid : wx83385dfe4f25bd55
	 * partnerid : 1567484741
	 * prepayid : wx17082317260085dbfba4d11b1049357800
	 * noncestr : 5ee96275455c0
	 * timestamp : 1592353397
	 * package : Sign=WXPay
	 * paySign : 37F4DE73A054CF04044E9C8F6130552B
	 */

	private String appid;
	private String partnerid;
	private String prepayid;
	private String noncestr;
	private String timestamp;
	@SerializedName("package")
	private String packageX;
	private String paySign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPackageX() {
		return packageX;
	}

	public void setPackageX(String packageX) {
		this.packageX = packageX;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
