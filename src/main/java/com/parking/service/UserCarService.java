package com.parking.service;

import com.parking.entity.UserCar;
import com.parking.mapper.UserCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCarService {
    @Autowired
    private UserCarMapper userCarMapper;
    public void addCar(String phone,String car_number){
        userCarMapper.addCar(phone,car_number);
    }
    public List<UserCar> findUserCar(String phone){
        List<UserCar> userCars=userCarMapper.findUserCar(phone);
        return userCars ;
    }
    //通过车牌查询用户手机号
    public String getCar_Phone(String car_number){
        String phone =userCarMapper.getCar_Phone(car_number);
        return phone;
    }
}
