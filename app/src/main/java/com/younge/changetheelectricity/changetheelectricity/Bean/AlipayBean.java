package com.younge.changetheelectricity.changetheelectricity.Bean;

import com.google.gson.annotations.SerializedName;

public class AlipayBean {
    /**
     * order : {"type":0,"user_id":2,"amount":"100","giveamount":0,"payamount":"100","couponid":0,"couponamount":0,"redamount":0,"paytype":"alipay","method":"app","status":0,"memo":"充值套餐","package":"{\"id\":1,\"type\":0,\"title\":\"\\u5145\\u503c\\u5957\\u9910\",\"text\":{\"use\":\"\",\"hour\":\"\",\"day\":\"\",\"money\":\"100\",\"percent\":{\"count\":\"\",\"money\":\"\"}},\"image\":\"\\/uploads\\/20200719\\/1.png\",\"remark\":\"\",\"createtime\":1594901501,\"updatetime\":1596376069,\"bind_ids\":\"0\",\"admin_id\":0,\"weigh\":0,\"status\":\"normal\"}","orderid":"20200820235851347154","createtime":1597939131,"id":"137"}
     * payparams : app_id=2021001178695594&method=alipay.trade.app.pay&format=JSON&charset=UTF-8&sign_type=RSA2&version=1.0&return_url=http%3A%2F%2Fwinpower.wljueli.com%2Fvv%2Fepay%2Fapi%2Fapi%2Freturnx%2Ftype%2Frecharge%2Fpaytype%2Falipay%2Fmethod%2Fapp&notify_url=http%3A%2F%2Fwinpower.wljueli.com%2Fvv%2Fepay%2Fapi%2Fapi%2Fnotifyx%2Ftype%2Frecharge%2Fpaytype%2Falipay%2Fmethod%2Fapp&timestamp=2020-08-20+23%3A58%3A51&sign=dBbIttILvvvCMXSgneeGQpCIsYYQxRFNGo9S5NvmnSpYLtNt720czv5y%2Bfvl1AAwsh%2Bcy2SntaEx5G0S%2FFnoyiROiA6dpzD0K%2FxD8z6tWkeCOxy4RdkFGPlOvgt1HmtvEuD8tn5SFXo4kekmxF5RWbMI4pRaFshNac0wYiiprcAC7oH6o%2Fe%2B%2Ft9axyaqq5wekAt%2FcQpyikj0f9U6jrhSNGRGcR18v76fi9ANbfc1zo0bZ%2Ft7DAUfmze0b5d2%2BGKxsEk6pLxMseDlCXlaJMx6zHa4Aco2bMzWPVdRKF78HfYdoykFv31KIBYuFyP6NNpPkN1iyYT8nnaxMo5mv7v5uQ%3D%3D&biz_content=%7B%22out_trade_no%22%3A%2220200820235851347154%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%5Cu5145%5Cu503c%5Cu5957%5Cu9910%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D
     */

    private OrderBean order;
    private String payparams;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public String getPayparams() {
        return payparams;
    }

    public void setPayparams(String payparams) {
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
         * paytype : alipay
         * method : app
         * status : 0
         * memo : 充值套餐
         * package : {"id":1,"type":0,"title":"\u5145\u503c\u5957\u9910","text":{"use":"","hour":"","day":"","money":"100","percent":{"count":"","money":""}},"image":"\/uploads\/20200719\/1.png","remark":"","createtime":1594901501,"updatetime":1596376069,"bind_ids":"0","admin_id":0,"weigh":0,"status":"normal"}
         * orderid : 20200820235851347154
         * createtime : 1597939131
         * id : 137
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
}
