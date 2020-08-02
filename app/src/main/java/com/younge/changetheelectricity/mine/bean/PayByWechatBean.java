package com.younge.changetheelectricity.mine.bean;

import com.google.gson.annotations.SerializedName;

public class PayByWechatBean {
    /**
     * order : {"type":0,"user_id":2,"amount":"100","giveamount":0,"payamount":"100","couponid":0,"couponamount":0,"redamount":0,"paytype":"wechat","method":"app","status":0,"memo":"充值套餐","package":"{\"id\":1,\"type\":0,\"title\":\"\\u5145\\u503c\\u5957\\u9910\",\"text\":{\"use\":\"\",\"hour\":\"\",\"day\":\"\",\"money\":\"100\"},\"image\":\"\\/uploads\\/20200719\\/1.png\",\"remark\":\"\",\"createtime\":1594901501,\"updatetime\":1596283346,\"bind_ids\":\"0\",\"admin_id\":0,\"weigh\":0,\"status\":\"normal\"}","orderid":"20200802125338216517","createtime":1596344018,"id":"57"}
     * payparams : {"appid":"wx197fcf39e65d69a9","partnerid":"1601008796","prepayid":"wx0212533836710484730365d51668858400","timestamp":"1596344018","noncestr":"lALUkVArrPYdXf9k","package":"Sign=WXPay","sign":"F375A8EEAC27ADB78C58CF9F3F2B7461"}
     */

    private OrderBean order;
    private PayparamsBean payparams;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public PayparamsBean getPayparams() {
        return payparams;
    }

    public void setPayparams(PayparamsBean payparams) {
        this.payparams = payparams;
    }

    public static class OrderBean {
        /**
         * type : 0
         * user_id : 2
         * amount : 100
         * giveamount : 0
         * payamount : 100
         * couponid : 0
         * couponamount : 0
         * redamount : 0
         * paytype : wechat
         * method : app
         * status : 0
         * memo : 充值套餐
         * package : {"id":1,"type":0,"title":"\u5145\u503c\u5957\u9910","text":{"use":"","hour":"","day":"","money":"100"},"image":"\/uploads\/20200719\/1.png","remark":"","createtime":1594901501,"updatetime":1596283346,"bind_ids":"0","admin_id":0,"weigh":0,"status":"normal"}
         * orderid : 20200802125338216517
         * createtime : 1596344018
         * id : 57
         */

        private int type;
        private int user_id;
        private String amount;
        private int giveamount;
        private String payamount;
        private int couponid;
        private int couponamount;
        private int redamount;
        private String paytype;
        private String method;
        private int status;
        private String memo;
        @SerializedName("package")
        private String packageX;
        private String orderid;
        private int createtime;
        private String id;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getGiveamount() {
            return giveamount;
        }

        public void setGiveamount(int giveamount) {
            this.giveamount = giveamount;
        }

        public String getPayamount() {
            return payamount;
        }

        public void setPayamount(String payamount) {
            this.payamount = payamount;
        }

        public int getCouponid() {
            return couponid;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public int getCouponamount() {
            return couponamount;
        }

        public void setCouponamount(int couponamount) {
            this.couponamount = couponamount;
        }

        public int getRedamount() {
            return redamount;
        }

        public void setRedamount(int redamount) {
            this.redamount = redamount;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class PayparamsBean {
        /**
         * appid : wx197fcf39e65d69a9
         * partnerid : 1601008796
         * prepayid : wx0212533836710484730365d51668858400
         * timestamp : 1596344018
         * noncestr : lALUkVArrPYdXf9k
         * package : Sign=WXPay
         * sign : F375A8EEAC27ADB78C58CF9F3F2B7461
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String sign;

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

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
