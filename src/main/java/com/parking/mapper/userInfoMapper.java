package com.parking.mapper;

import com.parking.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userInfoMapper {
    //注册
    @Insert("insert into user_info(user_name,phone,password) VALUES(#{user_name},#{phone},#{password})")
    void registerUser(@Param("user_name")String user_name,@Param("phone")String phone,@Param("password")String password);
    //寻找所有的用户信息
    @Select("SELECT * from user_info")
    List<UserInfo> getAllUserInfo();
    //寻找相应电话号码的用户信息
    @Select("SELECT * from user_info where phone = #{phone}")
    UserInfo getUserInfo(@Param("phone") String phone);
    //修改积分
    @Update("update user_info set point =#{point} where phone=#{phone}")
    void updatePoint(@Param("phone") String phone,@Param("point") String point);
    //获取用户积分
    @Select("select point from user_info where phone = #{phone}")
    String getUserPoint(@Param("phone") String phone);
}
