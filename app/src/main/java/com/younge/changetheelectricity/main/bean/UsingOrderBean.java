package com.younge.changetheelectricity.main.bean;

public class UsingOrderBean {


    /**
     * order_id : 2
     * info : {"id":2,"goods_type":0,"order_type":1,"begintime":0,"expiretime":0,"status":0,"start_box":1,"stop_box":2,"stop_macno":"123456"}
     * appointment : {"appointment_minute":"10","appointment_count":"5","my_count":0}
     */

    private int order_id;
    private InfoBean info;
    private AppointmentBean appointment;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public AppointmentBean getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentBean appointment) {
        this.appointment = appointment;
    }

    public static class InfoBean {
        /**
         * id : 2
         * goods_type : 0
         * order_type : 1
         * begintime : 0
         * expiretime : 0
         * status : 0
         * start_box : 1
         * stop_box : 2
         * stop_macno : 123456
         */

        private int id;
        private int goods_type;
        private int order_type;
        private int begintime;
        private int expiretime;
        private int status;
        private int start_box;
        private int stop_box;
        private String stop_macno;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getBegintime() {
            return begintime;
        }

        public void setBegintime(int begintime) {
            this.begintime = begintime;
        }

        public int getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStart_box() {
            return start_box;
        }

        public void setStart_box(int start_box) {
            this.start_box = start_box;
        }

        public int getStop_box() {
            return stop_box;
        }

        public void setStop_box(int stop_box) {
            this.stop_box = stop_box;
        }

        public String getStop_macno() {
            return stop_macno;
        }

        public void setStop_macno(String stop_macno) {
            this.stop_macno = stop_macno;
        }
    }

    public static class AppointmentBean {
        /**
         * appointment_minute : 10
         * appointment_count : 5
         * my_count : 0
         */

        private String appointment_minute;
        private String appointment_count;
        private int my_count;

        public String getAppointment_minute() {
            return appointment_minute;
        }

        public void setAppointment_minute(String appointment_minute) {
            this.appointment_minute = appointment_minute;
        }

        public String getAppointment_count() {
            return appointment_count;
        }

        public void setAppointment_count(String appointment_count) {
            this.appointment_count = appointment_count;
        }

        public int getMy_count() {
            return my_count;
        }

        public void setMy_count(int my_count) {
            this.my_count = my_count;
        }
    }
}
