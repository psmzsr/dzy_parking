package com.parking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PayMapper {
    //用户支付
    @Update("update car_bill set pay_phone=#{phone},pay_time=#{pay_time},over_time=#{over_time},pay_flag=#{pay_flag},pay_money=#{pay_money} where id=#{id}")
    void pay(@Param("phone")String phone, @Param("pay_time")String pay_time, @Param("over_time")String over_time,
             @Param("pay_flag") int pay_flag, @Param("id") int id,@Param("pay_money") String pay_money);
    //用户支付了但是没有离场，超过三十分钟
    @Update("update car_bill set pay_flag='3',over_time =#{over_time},over_money=#{over_money} where id=#{id}")
    void timeOut(@Param("over_time")String over_time,@Param("over_money")String over_money,@Param("id")int id);
    //积分支付

}
