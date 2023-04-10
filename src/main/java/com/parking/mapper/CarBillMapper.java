package com.parking.mapper;

import com.parking.entity.CarBill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CarBillMapper {
    //汽车进场
    @Insert("insert into car_bill(car_number,enter_time,owner) values(#{car_number},#{date},#{phone})")
    void carEnter(@Param("car_number")String car_number,@Param("date") String date,@Param("phone")String phone);
    //车辆离场
    @Update("update car_bill set leave_time = #{leave_time},count_time=#{count_time} where car_number=#{car_number} and enter_time=#{enter_time}")
    void carLeave(@Param("leave_time")String leave_time,@Param("count_time")String count_time,@Param("car_number")String car_number,@Param("enter_time")String enter_time);
    //获取车牌的账单信息
    @Select("select * from car_bill where car_number=#{car_number}")
    List<CarBill> zhi_ding_numberInfo(@Param("car_number") String car_number);
    //获取用户下的车牌账单信息
    @Select("select * from car_bill where owner = #{phone}")
    List<CarBill> userAllCarInfo(@Param("phone") String phone);
    // 获取用户下的车牌账单信息
    @Select("select * from car_bill where owner = #{phone} limit #{current_page},#{page_size}")
    List<CarBill> getUserAllCarFenYe(@Param("phone")String phone,@Param("current_page") int current_page,@Param("page_size") int page_size);
    @Update("update car_bill set pay_money = #{pay_money} where car_number=#{car_number} and enter_time=#{enter_time}")
    void countPayMoney(@Param("car_number") String car_number,@Param("enter_time") String enter_time,@Param("pay_money")float pay_money);
    //找出该用户未完成付款的订单
    @Select("select * from car_bill where pay_flag='0' and owner = #{phone}")
    List<CarBill> noPayCarBill(@Param("phone")String phone);
    //找出该用户完成的订单
    @Select("select * from car_bill where pay_flag!='0' and owner= #{phone}")
    List<CarBill> PayCarBill(@Param("phone") String phone);
    //查找特定单号的记录'
    @Select("select * from car_bill where id=#{id}")
    CarBill getIdCarBill(@Param("id") int id);
    //找出用户超时记录
    @Select("select * from car_bill where id=#{id} and pay_flag=3")
    CarBill getTimeOutCarBill(@Param("id") int id);
    //指定车牌记录
    @Select("select * from car_bill where car_number=#{car_number}")
    List<CarBill> getCar_number_List(@Param("car_number")String car_number);
}
