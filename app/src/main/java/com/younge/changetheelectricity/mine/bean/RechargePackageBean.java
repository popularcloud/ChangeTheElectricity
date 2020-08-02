package com.younge.changetheelectricity.mine.bean;

public class RechargePackageBean implements Cloneable{

    /**
     * taocanId : 1
     * chongMoney : 50
     * giftMoney : 0
     * discount : -1
     * desc : 充值50
     */

    private String taocanId;
    private String chongMoney;
    private String giftMoney;
    private int discount;
    private String desc;
    private boolean isChecked = false;

    public String getTaocanId() {
        return taocanId;
    }

    public void setTaocanId(String taocanId) {
        this.taocanId = taocanId;
    }

    public String getChongMoney() {
        return chongMoney;
    }

    public void setChongMoney(String chongMoney) {
        this.chongMoney = chongMoney;
    }

    public String getGiftMoney() {
        return giftMoney;
    }

    public void setGiftMoney(String giftMoney) {
        this.giftMoney = giftMoney;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
