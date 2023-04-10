package com.parking.service;

import com.parking.entity.CarBill;
import com.parking.entity.UserInfo;
import com.parking.mapper.userInfoMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userInfoService {
    @Autowired
    private userInfoMapper userInfoMapper;
    //获取所有用户信息
    public List<UserInfo> getAllUserInfo(){
        return userInfoMapper.getAllUserInfo();
    }
    //注册
    public String addUserInfo(String user_name,String phone,String password){
        List<UserInfo> userInfos=userInfoMapper.getAllUserInfo();
        int t=0;
        for (int i=0;i<userInfos.size();i++){
            if (phone.equals(userInfos.get(i).getPhone())){
                break;
            }
            t++;
        }
        if (t==userInfos.size()){
            userInfoMapper.registerUser(user_name,phone,password);
            System.out.println("注册成功");
            return "注册成功";
        }
        return "手机号已被注册，请重新填写！";
    }

    //检查是否存在用户并且检查是否有正确
    public boolean checkLogin(String phone,String password){
        Boolean flag=false;
        List<UserInfo> userInfos= userInfoMapper.getAllUserInfo();
        for (int i=0;i<userInfos.size();i++){
            if (phone.equals(userInfos.get(i).getPhone())&&password.equals(userInfos.get(i).getPassword())){
                flag=true;
            }
        }
        return flag;
    }
    //从前端接受电话号码获取用户信息
    public UserInfo getUserInfo(String phone){
        UserInfo userInfo=userInfoMapper.getUserInfo(phone);
        System.out.println(userInfo);
        return userInfo;
    }


}
