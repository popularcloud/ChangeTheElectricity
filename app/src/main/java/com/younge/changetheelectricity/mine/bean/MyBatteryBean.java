package com.younge.changetheelectricity.mine.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyBatteryBean {


    /**
     * total : 1
     * totalpage : 1
     * list : [{"id":1,"user_id":1,"default":1,"serial":"","no":"123456","sn":"","battery":0,"createtime":1593779787,"updatetime":1593779787}]
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
         * user_id : 1
         * default : 1
         * serial :
         * no : 123456
         * sn :
         * battery : 0
         * createtime : 1593779787
         * updatetime : 1593779787
         */

        private int id;
        private int user_id;
        @SerializedName("default")
        private int defaultX;
        private String serial;
        private String no;
        private String sn;
        private int battery;
        private int createtime;
        private int updatetime;

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

        public int getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(int defaultX) {
            this.defaultX = defaultX;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
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
    }
}
