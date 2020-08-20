package com.younge.changetheelectricity.changetheelectricity.Bean;

import java.util.List;

public class UserHistoryBean {

    /**
     * total : 5
     * totalpage : 1
     * list : [{"id":23,"admin_id":3,"createtime":1596983051,"title":"充电次卡","seller":{"id":3,"nickname":"商家1"}},{"id":4,"admin_id":3,"createtime":1596643822,"title":"充电次卡","seller":{"id":3,"nickname":"商家1"}},{"id":3,"admin_id":3,"createtime":1596643790,"title":"充电次卡","seller":{"id":3,"nickname":"商家1"}},{"id":2,"admin_id":3,"createtime":1596643757,"title":"充电次卡","seller":{"id":3,"nickname":"商家1"}},{"id":1,"admin_id":3,"createtime":1596643691,"title":"充电次卡","seller":{"id":3,"nickname":"商家1"}}]
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
         * id : 23
         * admin_id : 3
         * createtime : 1596983051
         * title : 充电次卡
         * seller : {"id":3,"nickname":"商家1"}
         */

        private int id;
        private int admin_id;
        private int createtime;
        private String title;
        private SellerBean seller;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public SellerBean getSeller() {
            return seller;
        }

        public void setSeller(SellerBean seller) {
            this.seller = seller;
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
    }
}
