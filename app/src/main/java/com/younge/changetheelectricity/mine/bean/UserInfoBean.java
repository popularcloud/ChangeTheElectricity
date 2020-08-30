package com.younge.changetheelectricity.mine.bean;

import java.util.List;

public class UserInfoBean {

    /**
     * userinfo : {"id":2,"username":"13412553966","nickname":"13412553966","email":"","mobile":"13412553966","avatar":"http://winpower.wljueli.com/assets/img/avatar.png","money":"0.00","givemoney":"0.00","score":0,"verification":0,"idcard":"","redmoney":"0.00","verification_desc":"","idcardfront":"","idcardback":"","idcardhand":"","token":"497447ab-5f43-4dee-9cf2-1e524f2f1cb5"}
     * thirdlist : []
     */

    private UserinfoBean userinfo;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * id : 2
         * username : 13412553966
         * nickname : 13412553966
         * email :
         * mobile : 13412553966
         * avatar : http://winpower.wljueli.com/assets/img/avatar.png
         * money : 0.00
         * givemoney : 0.00
         * score : 0
         * verification : 0
         * idcard :
         * redmoney : 0.00
         * verification_desc :
         * idcardfront :
         * idcardback :
         * idcardhand :
         * token : 497447ab-5f43-4dee-9cf2-1e524f2f1cb5
         */

        private int id;
        private String username;
        private String nickname;
        private String email;
        private String mobile;
        private String avatar;
        private String money;
        private String givemoney;
        private int score;
        private int verification;//0为未认证 1为认证通过 2为失败
        private String idcard;
        private String redmoney;
        private String verification_desc;
        private String idcardfront;
        private String idcardback;
        private String idcardhand;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getVerification() {
            return verification;
        }

        public void setVerification(int verification) {
            this.verification = verification;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRedmoney() {
            return redmoney;
        }

        public void setRedmoney(String redmoney) {
            this.redmoney = redmoney;
        }

        public String getVerification_desc() {
            return verification_desc;
        }

        public void setVerification_desc(String verification_desc) {
            this.verification_desc = verification_desc;
        }

        public String getIdcardfront() {
            return idcardfront;
        }

        public void setIdcardfront(String idcardfront) {
            this.idcardfront = idcardfront;
        }

        public String getIdcardback() {
            return idcardback;
        }

        public void setIdcardback(String idcardback) {
            this.idcardback = idcardback;
        }

        public String getIdcardhand() {
            return idcardhand;
        }

        public void setIdcardhand(String idcardhand) {
            this.idcardhand = idcardhand;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
