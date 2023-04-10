package com.parking.mapper;
import com.parking.entity.UserCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCarMapper {
    @Insert("insert into user_car(phone,car_number) values(#{phone},#{car_number})")
    void addCar(@Param("phone") String phone, @Param("car_number") String car_number);
    //用户的车牌号
    @Select("select * from user_car where phone = #{phone}")
    List<UserCar> findUserCar(@Param("phone") String phone);
    //通过车牌号查询手机号
    @Select("select phone from user_car where car_number = #{car_number}")
    String getCar_Phone(@Param("car_number") String car_number);
}
