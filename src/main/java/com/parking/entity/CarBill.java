package com.parking.entity;

public class CarBill {
    private int id;
    private String car_number;
    private String enter_time;
    private String leave_time;
    private int pay_flag;
    private String pay_phone;
    private String pay_money;
//    private String off_money;
    private String pay_time;
    private String over_time;
    private String owner;
    private String over_money;

    public String getOver_money() {
        return over_money;
    }

    public void setOver_money(String over_money) {
        this.over_money = over_money;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getOver_time() {
        return over_time;
    }

    public void setOver_time(String over_time) {
        this.over_time = over_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(String enter_time) {
        this.enter_time = enter_time;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public int getPay_flag() {
        return pay_flag;
    }

    public void setPay_flag(int pay_flag) {
        this.pay_flag = pay_flag;
    }

    public String getPay_phone() {
        return pay_phone;
    }

    public void setPay_phone(String pay_phone) {
        this.pay_phone = pay_phone;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

//    public String getOff_money() {
//        return off_money;
//    }
//
//    public void setOff_money(String off_money) {
//        this.off_money = off_money;
//    }

}
