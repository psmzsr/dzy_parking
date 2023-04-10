package com.parking.entity;

public class BillList {
    private int id;
    private String create_time;
    private String money;
    private String phone;
    private String use_flag;

    public String getUse_flag() {
        return use_flag;
    }

    public void setUse_flag(String use_flag) {
        this.use_flag = use_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
