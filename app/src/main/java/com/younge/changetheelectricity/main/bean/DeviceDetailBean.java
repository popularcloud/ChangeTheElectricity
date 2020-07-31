package com.younge.changetheelectricity.main.bean;

import java.util.List;

public class DeviceDetailBean {
    /**
     * id : 1
     * type : 3
     * title : test
     * macno : 5202019020500002
     * box : 6
     * charge_box : 2
     * area : 广东省/东莞市/南城街道
     * address : 高盛科技大厦308
     * lng : 113.726037
     * lat : 22.98662
     * remark :
     * admin_id : 3
     * status : 2
     * clean : 0
     * activetime : 0
     * createtime : 1593690628
     * updatetime : 0
     * distance : 0
     * seller : {"id":3,"nickname":"商家1"}
     * appointment : {"appointment_minute":"10","appointment_count":"5","my_count":0,"my_order":null}
     * charge : {"my_order":null}
     * device_goods : [{"id":1,"type":3,"goods_type":0,"device_id":1,"device_box":1,"macno":"123456","macno2":"","status":1,"lock":1,"rfid":1,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":2,"type":3,"goods_type":0,"device_id":1,"device_box":2,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":3,"type":3,"goods_type":0,"device_id":1,"device_box":3,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":4,"type":3,"goods_type":0,"device_id":1,"device_box":4,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":5,"type":3,"goods_type":0,"device_id":1,"device_box":5,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":6,"type":3,"goods_type":0,"device_id":1,"device_box":6,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":7,"type":3,"goods_type":1,"device_id":1,"device_box":1,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0},{"id":8,"type":3,"goods_type":1,"device_id":1,"device_box":2,"macno":"","macno2":"","status":0,"lock":0,"rfid":0,"hall":0,"battery":0,"createtime":1593690628,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"","charge_minute":0}]
     * active_box : 1
     * active_charge_box : 0
     */

    private int id;
    private int type;
    private String title;
    private String macno;
    private int box;
    private int charge_box;
    private String area;
    private String address;
    private String lng;
    private String lat;
    private String remark;
    private int admin_id;
    private int status;
    private int clean;
    private int activetime;
    private int createtime;
    private int updatetime;
    private int distance;
    private SellerBean seller;
    private AppointmentBean appointment;
    private ChargeBean charge;
    private int active_box;
    private int active_charge_box;
    private List<DeviceGoodsBean> device_goods;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMacno() {
        return macno;
    }

    public void setMacno(String macno) {
        this.macno = macno;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public int getCharge_box() {
        return charge_box;
    }

    public void setCharge_box(int charge_box) {
        this.charge_box = charge_box;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClean() {
        return clean;
    }

    public void setClean(int clean) {
        this.clean = clean;
    }

    public int getActivetime() {
        return activetime;
    }

    public void setActivetime(int activetime) {
        this.activetime = activetime;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public AppointmentBean getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentBean appointment) {
        this.appointment = appointment;
    }

    public ChargeBean getCharge() {
        return charge;
    }

    public void setCharge(ChargeBean charge) {
        this.charge = charge;
    }

    public int getActive_box() {
        return active_box;
    }

    public void setActive_box(int active_box) {
        this.active_box = active_box;
    }

    public int getActive_charge_box() {
        return active_charge_box;
    }

    public void setActive_charge_box(int active_charge_box) {
        this.active_charge_box = active_charge_box;
    }

    public List<DeviceGoodsBean> getDevice_goods() {
        return device_goods;
    }

    public void setDevice_goods(List<DeviceGoodsBean> device_goods) {
        this.device_goods = device_goods;
    }

    public static class SellerBean {
        /**
         * id : 3
         * nickname : 商家1
         */

        private int id;
        private String nickname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class AppointmentBean {
        /**
         * appointment_minute : 10
         * appointment_count : 5
         * my_count : 0
         * my_order : null
         */

        private String appointment_minute;
        private String appointment_count;
        private int my_count;
        private Object my_order;

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

        public Object getMy_order() {
            return my_order;
        }

        public void setMy_order(Object my_order) {
            this.my_order = my_order;
        }
    }

    public static class ChargeBean {
        /**
         * my_order : null
         */

        private Object my_order;

        public Object getMy_order() {
            return my_order;
        }

        public void setMy_order(Object my_order) {
            this.my_order = my_order;
        }
    }

    public static class DeviceGoodsBean {
        /**
         * id : 1
         * type : 3
         * goods_type : 0
         * device_id : 1
         * device_box : 1
         * macno : 123456
         * macno2 :
         * status : 1
         * lock : 1
         * rfid : 1
         * hall : 0
         * battery : 0
         * createtime : 1593690628
         * updatetime : 0
         * lockid : 0
         * locktime : 0
         * cleanbegin : 0
         * cleanend : 0
         * desc :
         * charge_minute : 0
         */

        private int id;
        private int type;
        private int goods_type;
        private int device_id;
        private int device_box;
        private String macno;
        private String macno2;
        private int status;
        private int lock;
        private int rfid;
        private int hall;
        private int battery;
        private int createtime;
        private int updatetime;
        private int lockid;
        private int locktime;
        private int cleanbegin;
        private int cleanend;
        private String desc;
        private int charge_minute;

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

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
        }

        public int getDevice_box() {
            return device_box;
        }

        public void setDevice_box(int device_box) {
            this.device_box = device_box;
        }

        public String getMacno() {
            return macno;
        }

        public void setMacno(String macno) {
            this.macno = macno;
        }

        public String getMacno2() {
            return macno2;
        }

        public void setMacno2(String macno2) {
            this.macno2 = macno2;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getLock() {
            return lock;
        }

        public void setLock(int lock) {
            this.lock = lock;
        }

        public int getRfid() {
            return rfid;
        }

        public void setRfid(int rfid) {
            this.rfid = rfid;
        }

        public int getHall() {
            return hall;
        }

        public void setHall(int hall) {
            this.hall = hall;
        }

        public int getBattery() {
            return battery;
        }

        public void setBattery(int battery) {
            this.battery = battery;
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

        public int getLockid() {
            return lockid;
        }

        public void setLockid(int lockid) {
            this.lockid = lockid;
        }

        public int getLocktime() {
            return locktime;
        }

        public void setLocktime(int locktime) {
            this.locktime = locktime;
        }

        public int getCleanbegin() {
            return cleanbegin;
        }

        public void setCleanbegin(int cleanbegin) {
            this.cleanbegin = cleanbegin;
        }

        public int getCleanend() {
            return cleanend;
        }

        public void setCleanend(int cleanend) {
            this.cleanend = cleanend;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getCharge_minute() {
            return charge_minute;
        }

        public void setCharge_minute(int charge_minute) {
            this.charge_minute = charge_minute;
        }
    }
}
