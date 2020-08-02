package com.younge.changetheelectricity.mine.bean;

import java.util.List;

public class DepositHistoryBean {


    /**
     * total : 1
     * totalpage : 1
     * list : [{"id":1,"type":2,"typeid":2,"user_type":9,"user_id":1,"paytype":"","money":"-30.00","givemoney":"0.00","memo":"","ismoney":1,"createtime":1594954776,"updatetime":0,"status":1,"couponmoney":"0.00","redmoney":"0.00"}]
     */

    private int total;
    private int totalpage;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * type : 2
         * typeid : 2
         * user_type : 9
         * user_id : 1
         * paytype :
         * money : -30.00
         * givemoney : 0.00
         * memo :
         * ismoney : 1
         * createtime : 1594954776
         * updatetime : 0
         * status : 1
         * couponmoney : 0.00
         * redmoney : 0.00
         */

        private int id;
        private int type;
        private int typeid;
        private int user_type;
        private int user_id;
        private String paytype;
        private String money;
        private String givemoney;
        private String memo;
        private int ismoney;
        private int createtime;
        private int updatetime;
        private int status;
        private String couponmoney;
        private String redmoney;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getGivemoney() {
            return givemoney;
        }

        public void setGivemoney(String givemoney) {
            this.givemoney = givemoney;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getIsmoney() {
            return ismoney;
        }

        public void setIsmoney(int ismoney) {
            this.ismoney = ismoney;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCouponmoney() {
            return couponmoney;
        }

        public void setCouponmoney(String couponmoney) {
            this.couponmoney = couponmoney;
        }

        public String getRedmoney() {
            return redmoney;
        }

        public void setRedmoney(String redmoney) {
            this.redmoney = redmoney;
        }
    }
}
