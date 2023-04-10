package com.parking.entity;

public class UserInfo {
    private String user_name;
    private String  phone;
    private String password;
    private String level;
    private String point;
    private String month_card;
//    private String del_flag;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getMonth_card() {
        return month_card;
    }

    public void setMonth_card(String month_card) {
        this.month_card = month_card;
    }

//    public String getDel_flag() {
//        return del_flag;
//    }
//
//    public void setDel_flag(String del_flag) {
//        this.del_flag = del_flag;
//    }
}
