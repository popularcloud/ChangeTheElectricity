package com.younge.changetheelectricity.mine.bean;

import java.io.Serializable;
import java.util.List;

public class PackageBean {

    /**
     * total : 1
     * totalpage : 1
     * list : [{"id":3,"type":2,"title":"充电次卡","text":{"use":"1","hour":"1","day":"15","money":"15"},"image":"","remark":"","createtime":1594901534,"updatetime":1594901534,"bind_ids":"0","admin_id":0,"weigh":0,"status":"normal"}]
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

    public static class ListBean implements Serializable {
        /**
         * id : 3
         * type : 2
         * title : 充电次卡
         * text : {"use":"1","hour":"1","day":"15","money":"15"}
         * image :
         * remark :
         * createtime : 1594901534
         * updatetime : 1594901534
         * bind_ids : 0
         * admin_id : 0
         * weigh : 0
         * status : normal
         */

        private int id;
        private int type;
        private String title;
        private TextBean text;
        private String image;
        private String remark;
        private int createtime;
        private int updatetime;
        private String bind_ids;
        private int admin_id;
        private int weigh;
        private String status;
        private boolean isChecked;

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

        public TextBean getText() {
            return text;
        }

        public void setText(TextBean text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getBind_ids() {
            return bind_ids;
        }

        public void setBind_ids(String bind_ids) {
            this.bind_ids = bind_ids;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public static class TextBean {
            /**
             * use : 1
             * hour : 1
             * day : 15
             * money : 15
             */

            private String use;
            private String hour;
            private String day;
            private String money;

            public String getUse() {
                return use;
            }

            public void setUse(String use) {
                this.use = use;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
