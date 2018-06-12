package com.example.zzn.nuomi.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZZN on 2017/8/25.
 */

public class MyResult implements Serializable {
    private List<Results> results;

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public class Results {
        private String id;
        private String name;
        private String img;
        private String food;
        private String price;
        private String time;
        private String state;
        private String serverVersion;
        private String updateurl;
        private String appname;
        private String userid;
        private String phone;
        private String username;
        private String head;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppname() {
            return appname;
        }

        public void setUpdateurl(String updateurl) {
            this.updateurl = updateurl;
        }

        public String getUpdateurl() {
            return updateurl;
        }

        public String getServerVersion() {
            return serverVersion;
        }

        public void setServerVersion(String serverVersion) {
            this.serverVersion = serverVersion;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public  String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public  String getImg() {
            return img;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
