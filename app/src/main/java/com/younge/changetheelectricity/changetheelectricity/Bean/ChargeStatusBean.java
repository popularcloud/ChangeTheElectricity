package com.younge.changetheelectricity.changetheelectricity.Bean;

public class ChargeStatusBean {

    /**
     * id : 7
     * type : 3
     * goods_type : 1
     * orderno : 20200806224033585759
     * begintime : 0
     * endtime : 0
     * stoptime : 0
     * confirmtime : 0
     * status : 0
     * usetime : 0
     * result : {"act":0,"status":3,"message":"插座通电失败"}
     */

    private int id;
    private int type;
    private int goods_type;
    private String orderno;
    private int begintime;
    private int endtime;
    private int stoptime;
    private int confirmtime;
    private int status;
    private int usetime;
    private int countdown;
    private ResultBean result;

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

    public int getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(int goods_type) {
        this.goods_type = goods_type;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public int getBegintime() {
        return begintime;
    }

    public void setBegintime(int begintime) {
        this.begintime = begintime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getStoptime() {
        return stoptime;
    }

    public void setStoptime(int stoptime) {
        this.stoptime = stoptime;
    }

    public int getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(int confirmtime) {
        this.confirmtime = confirmtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUsetime() {
        return usetime;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public void setUsetime(int usetime) {
        this.usetime = usetime;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * act : 0
         * status : 3
         * message : 插座通电失败
         */

        private int act;
        private int status;
        private String message;

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
