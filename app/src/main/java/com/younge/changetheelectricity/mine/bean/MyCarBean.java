package com.younge.changetheelectricity.mine.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCarBean {

    /**
     * total : 1
     * totalpage : 1
     * list : [{"id":2,"user_id":2,"carvin":"000000","serial":"000000","carno":"000000","picfront":"http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg","picback":"http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg","picleft":"http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg","picright":"http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg","battery_id":0,"createtime":1595897504,"updatetime":1595897504,"default":1}]
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
         * id : 2
         * user_id : 2
         * carvin : 000000
         * serial : 000000
         * carno : 000000
         * picfront : http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg
         * picback : http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg
         * picleft : http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg
         * picright : http://winpower.wljueli.com/uploads/20200728/PictureSelector.temp.jpg
         * battery_id : 0
         * createtime : 1595897504
         * updatetime : 1595897504
         * default : 1
         */

        private int id;
        private int user_id;
        private String carvin;
        private String serial;
        private String carno;
        private String picfront;
        private String picback;
        private String picleft;
        private String picright;
        private int battery_id;
        private int createtime;
        private int updatetime;
        @SerializedName("default")
        private int defaultX;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getCarvin() {
            return carvin;
        }

        public void setCarvin(String carvin) {
            this.carvin = carvin;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
        }

        public String getPicfront() {
            return picfront;
        }

        public void setPicfront(String picfront) {
            this.picfront = picfront;
        }

        public String getPicback() {
            return picback;
        }

        public void setPicback(String picback) {
            this.picback = picback;
        }

        public String getPicleft() {
            return picleft;
        }

        public void setPicleft(String picleft) {
            this.picleft = picleft;
        }

        public String getPicright() {
            return picright;
        }

        public void setPicright(String picright) {
            this.picright = picright;
        }

        public int getBattery_id() {
            return battery_id;
        }

        public void setBattery_id(int battery_id) {
            this.battery_id = battery_id;
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

        public int getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(int defaultX) {
            this.defaultX = defaultX;
        }
    }
}
