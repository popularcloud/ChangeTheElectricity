package com.younge.changetheelectricity.main.bean;

import java.util.List;

public class DeviceDetailBean {

    /**
     * id : 1
     * type : 3
     * title : 测试换电柜
     * macno : 2020081900000001
     * box : 4
     * charge_box : 4
     * area : 广东省/东莞市/南城街道
     * address : 高盛科技大厦308
     * lng : 113.726037
     * lat : 22.98662
     * remark : 烟感报警
     * admin_id : 7
     * status : 1
     * clean : 0
     * activetime : 0
     * createtime : 1593690628
     * updatetime : 1599567838
     * temp : 0
     * voltage : 0
     * current : 0
     * power : 0
     * kwh : 0.0
     * sysnum0 : 0
     * sysnum1 : 0
     * distance : 0
     * seller : {"id":7,"nickname":"李商家"}
     * appointment : {"appointment_minute":"10","appointment_count":"5","my_count":0,"my_order":null}
     * charge : {"my_order":null}
     * battery : {"my_order":null}
     * device_goods : [{"id":1,"type":3,"goods_type":0,"device_id":1,"device_box":1,"macno":"","macno2":"","status":0,"lock":1,"rfid":0,"hall":0,"battery":null,"createtime":1593690628,"updatetime":1599564703,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"灭火装置未启动","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":2,"type":3,"goods_type":0,"device_id":1,"device_box":2,"macno":"BT104802010TN00200706001","macno2":"","status":1,"lock":1,"rfid":1,"hall":0,"battery":{"id":10,"serial":"BT104802010TN00200706001","no":"BT104802010TN00200706001","sn":"BT104802010TN00200706001","createtime":1597933651,"updatetime":1599565521,"user_id":0,"battery":30,"temp":0,"voltage":"4915.00","volume":"609.00","lng":"","lat":"","admin_id":7,"remark":"灭火装置未启动","status":1,"volume_default":"0.00"},"createtime":1593690628,"updatetime":1599565496,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"灭火装置未启动","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"4915.00","volume":"609.00","volume_default":"0.00","charge_minute":0},{"id":9,"type":3,"goods_type":0,"device_id":1,"device_box":3,"macno":"","macno2":"","status":0,"lock":1,"rfid":0,"hall":0,"battery":null,"createtime":1596637948,"updatetime":1599565313,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"灭火装置未启动","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":10,"type":3,"goods_type":0,"device_id":1,"device_box":4,"macno":"","macno2":"","status":0,"lock":1,"rfid":0,"hall":0,"battery":null,"createtime":1596637948,"updatetime":1599563991,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"灭火装置未启动","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":15,"type":3,"goods_type":1,"device_id":1,"device_box":1,"macno":"","macno2":"","status":1,"lock":0,"rfid":0,"hall":0,"battery":null,"createtime":1598452675,"updatetime":1598968005,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"通信异常","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":16,"type":3,"goods_type":1,"device_id":1,"device_box":2,"macno":"","macno2":"","status":1,"lock":0,"rfid":0,"hall":0,"battery":null,"createtime":1598452675,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"通信异常","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":17,"type":3,"goods_type":1,"device_id":1,"device_box":3,"macno":"","macno2":"","status":1,"lock":0,"rfid":0,"hall":0,"battery":null,"createtime":1598452675,"updatetime":1598965388,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"通信异常","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0},{"id":18,"type":3,"goods_type":1,"device_id":1,"device_box":4,"macno":"","macno2":"","status":1,"lock":0,"rfid":0,"hall":0,"battery":null,"createtime":1598452675,"updatetime":0,"lockid":0,"locktime":0,"cleanbegin":0,"cleanend":0,"desc":"通信异常","sysnum":0,"mode":0,"use":0,"temp":0,"voltage":"0.00","volume":"0.00","volume_default":"0.00","charge_minute":0}]
     * active_box : 1
     * active_charge_box : 4
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
    private int temp;
    private int voltage;
    private int current;
    private int power;
    private String kwh;
    private int sysnum0;
    private int sysnum1;
    private int distance;
    private SellerBean seller;
    private AppointmentBean appointment;
    private ChargeBean charge;
    private BatteryBean battery;
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

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getKwh() {
        return kwh;
    }

    public void setKwh(String kwh) {
        this.kwh = kwh;
    }

    public int getSysnum0() {
        return sysnum0;
    }

    public void setSysnum0(int sysnum0) {
        this.sysnum0 = sysnum0;
    }

    public int getSysnum1() {
        return sysnum1;
    }

    public void setSysnum1(int sysnum1) {
        this.sysnum1 = sysnum1;
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

    public BatteryBean getBattery() {
        return battery;
    }

    public void setBattery(BatteryBean battery) {
        this.battery = battery;
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
         * id : 7
         * nickname : 李商家
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
        private Integer appointment_count;
        private Integer my_count;
        private MyOrder my_order;

        public String getAppointment_minute() {
            return appointment_minute;
        }

        public void setAppointment_minute(String appointment_minute) {
            this.appointment_minute = appointment_minute;
        }

        public Integer getAppointment_count() {
            return appointment_count;
        }

        public void setAppointment_count(Integer appointment_count) {
            this.appointment_count = appointment_count;
        }

        public Integer getMy_count() {
            return my_count;
        }

        public void setMy_count(Integer my_count) {
            this.my_count = my_count;
        }

        public MyOrder getMy_order() {
            return my_order;
        }

        public void setMy_order(MyOrder my_order) {
            this.my_order = my_order;
        }
    }

    public static class MyOrder {

        /**
         * id : 1
         * expiretime : 1234567
         * start_box : 1
         * start_macno : 32323
         */

        private int id;
        private int expiretime;
        private int start_box;
        private String start_macno;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
        }

        public int getStart_box() {
            return start_box;
        }

        public void setStart_box(int start_box) {
            this.start_box = start_box;
        }

        public String getStart_macno() {
            return start_macno;
        }

        public void setStart_macno(String start_macno) {
            this.start_macno = start_macno;
        }
    }

    public static class ChargeBean {

        /**
         * id : 1
         * begintime : 124
         * start_box : 1
         */

        private int id;
        private String begintime;
        private int start_box;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public int getStart_box() {
            return start_box;
        }

        public void setStart_box(int start_box) {
            this.start_box = start_box;
        }
    }

    public static class BatteryBean {

        /**
         * id : 1
         * begintime : 124
         * start_box : 1
         * stop_box : 1
         */

        private int id;
        private String begintime;
        private int start_box;
        private int stop_box;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
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
    }

    public static class DeviceGoodsBean {
        /**
         * id : 1
         * type : 3
         * goods_type : 0
         * device_id : 1
         * device_box : 1
         * macno :
         * macno2 :
         * status : 0
         * lock : 1
         * rfid : 0
         * hall : 0
         * battery : null
         * createtime : 1593690628
         * updatetime : 1599564703
         * lockid : 0
         * locktime : 0
         * cleanbegin : 0
         * cleanend : 0
         * desc : 灭火装置未启动
         * sysnum : 0
         * mode : 0
         * use : 0
         * temp : 0
         * voltage : 0.00
         * volume : 0.00
         * volume_default : 0.00
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
        private Float battery;
        private int createtime;
        private int updatetime;
        private int lockid;
        private int locktime;
        private int cleanbegin;
        private int cleanend;
        private String desc;
        private int sysnum;
        private int mode;
        private int use;
        private int temp;
        private String voltage;
        private String volume;
        private String volume_default;
        private int charge_minute;
        private String battery_serial;

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

        public Float getBattery() {
            return battery;
        }

        public void setBattery(Float battery) {
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

        public int getSysnum() {
            return sysnum;
        }

        public void setSysnum(int sysnum) {
            this.sysnum = sysnum;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public int getUse() {
            return use;
        }

        public void setUse(int use) {
            this.use = use;
        }

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public String getVoltage() {
            return voltage;
        }

        public void setVoltage(String voltage) {
            this.voltage = voltage;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getVolume_default() {
            return volume_default;
        }

        public void setVolume_default(String volume_default) {
            this.volume_default = volume_default;
        }

        public int getCharge_minute() {
            return charge_minute;
        }

        public void setCharge_minute(int charge_minute) {
            this.charge_minute = charge_minute;
        }

        public String getBattery_serial() {
            return battery_serial;
        }

        public void setBattery_serial(String battery_serial) {
            this.battery_serial = battery_serial;
        }
    }
}
