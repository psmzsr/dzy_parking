package com.parking.controller_H5;

import com.parking.entity.CarBill;
import com.parking.entity.UserCar;
import com.parking.entity.UserInfo;
import com.parking.service.CarBillService;
import com.parking.service.UserCarService;
import com.parking.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserInfoController_H5 {
    @Autowired
    private userInfoService userInfoService;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private CarBillService carBillService;
    @RequestMapping("register_H5")
    public String register_H5(HttpServletRequest request){
        String user_name=request.getParameter("user_name");
        String password=request.getParameter("password");
        String phone=request.getParameter("phone");
        String data=userInfoService.addUserInfo(user_name,phone,password);
        return data;
    }
    @RequestMapping("check_login_H5")
    public ArrayList<Object> checkLoginH5(HttpServletRequest request){
        System.out.println("checkLoginH5方法已被调用");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        System.out.println(phone+password);
        String car_shuliang;
        UserInfo userInfo=userInfoService.getUserInfo(phone);
        List<UserCar> userCars=userCarService.findUserCar(phone);
        ArrayList<Object> data=new ArrayList<>();
        if (userInfoService.checkLogin(phone,password)){
            if(userInfo.getLevel().equals("0")){
                car_shuliang=String.valueOf(3-userCars.size());
            }else{
                car_shuliang=String.valueOf(5-userCars.size());
            }
            data.add(phone);
            data.add(password);
            data.add(car_shuliang);
            data.add(carBillService.userAllCarInfo(phone));//car_list
            data.add(userInfoService.getUserInfo(phone));//user_Info
            data.add(userCarService.findUserCar(phone));//user_car
            System.out.println(data);
            return data;
        }
        return data;
    }
    //刷新数据
    @RequestMapping("refresh")
    public ArrayList<Object> refresh(HttpServletRequest request) throws ParseException {
        System.out.println("checkLoginH5方法已被调用");
        String phone=request.getParameter("phone");
//        String password=request.getParameter("password");
//        System.out.println(phone+password);
        String car_shuliang;
        UserInfo userInfo=userInfoService.getUserInfo(phone);
        List<UserCar> userCars=userCarService.findUserCar(phone);
        ArrayList<Object> data=new ArrayList<>();
            if(userInfo.getLevel().equals("0")){
                car_shuliang=String.valueOf(3-userCars.size());
            }else{
                car_shuliang=String.valueOf(5-userCars.size());
            }
        carBillService.carNowShouldPayMoney(phone);
        carBillService.CheckTimeOut(phone);
            data.add(car_shuliang);//1
            data.add(carBillService.userAllCarInfo(phone));//car_list//2
            data.add(userInfoService.getUserInfo(phone));//user_Info//3
            data.add(userCarService.findUserCar(phone));//user_car//4
            System.out.println(data);
            return data;
    }
    //将支付信息传到pay界面
    @RequestMapping("car_bill_to_pay")
    public ArrayList<Object> car_bill_to_pay(HttpServletRequest request){
        String id=request.getParameter("id");
        String phone=request.getParameter("phone");
//        userInfoService.getUserInfo(phone);
        ArrayList<Object> list=new ArrayList<>();
        list.add(carBillService.getIdCarBill(id));
        list.add(userInfoService.getUserInfo(phone));
        return list;
    }
}
