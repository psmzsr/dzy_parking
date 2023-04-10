package com.parking.controller;

import com.parking.entity.CarBill;
import com.parking.entity.UserCar;
import com.parking.entity.UserInfo;
import com.parking.service.CarBillService;
import com.parking.service.UserCarService;
import com.parking.service.userInfoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class userInfoController {
    @Autowired
    private userInfoService userInfoService;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private CarBillService carBillService;
    @RequestMapping("user_login")
    public String userLogin(Model model){
        System.out.println("成功进入登陆界面");
        model.addAttribute("password_error",null);
        model.addAttribute("phone_error",null);
        return "login";
    }
    @RequestMapping("user_register")
    //注册
    public String userRegister(){
        System.out.println("进入注册界面");
        return "register";
    }
    //添加用户
    @RequestMapping("add_userinfo")
    public String addUserInfo(HttpServletRequest request, Model model){
        String user_name=request.getParameter("user_name");
        String  phone=request.getParameter("phone");
        String password=request.getParameter("password");
        String again_password=request.getParameter("again_password");
        System.out.println(user_name+"======"+phone+"====="+password+"========"+again_password);
        if (!password.equals(again_password)){
            System.out.println("密码有误");
            model.addAttribute("password_error","两次密码不一致,请重新填写");
            return "register";
        }
        if (userInfoService.addUserInfo(user_name,phone,password).equals("注册成功")){
            System.out.println("注册成功");
            return "login";
        }else{
            System.out.println("注册失败");
            model.addAttribute("phone_error","手机号已被注册，请重新填写！");
            return "register";
        }
//        userInfoService.addUserInfo(user_name,phone,password);
//        System.out.println("12312123123");
//        return "login";
    }

    @RequestMapping("user_check_login")
    public String userCheckLogin(HttpServletRequest request,Model model) throws ParseException {
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        String car_shuliang;
        System.out.println(phone+"------"+password);
        //用户还能新建几个车牌
        UserInfo userInfo=userInfoService.getUserInfo(phone);
        List<UserCar> userCars=userCarService.findUserCar(phone);
        //在这里判读用户是否还能添加车牌
//        if(userInfo.getLevel().equals("0")){
//            car_shuliang=String.valueOf(3-userCars.size());
//        }else{
//            car_shuliang=String.valueOf(5-userCars.size());
//        }
        if (userInfoService.checkLogin(phone,password)){
            if(userInfo.getLevel().equals("0")){
                car_shuliang=String.valueOf(3-userCars.size());
            }else{
                car_shuliang=String.valueOf(5-userCars.size());
            }
            carBillService.carNowShouldPayMoney(phone);
            carBillService.CheckTimeOut(phone);
//            for (int i=0;i<userCars.size();i++){
//                carBillService.carNowShouldPayMoney(userCars.get(i).getCar_number());
//            }
            //分页查询
            int page_size=3;
            String current_page=request.getParameter("current_page");
            int pageLimit;
            if (current_page == null){
                pageLimit=1;
            }
            else {
                pageLimit=Integer.parseInt(current_page);
            }
            List<CarBill> carBillsFenYe=carBillService.getUserAllCarFenYe(phone,pageLimit,page_size);
            model.addAttribute("user_Info",userInfoService.getUserInfo(phone));
            model.addAttribute("user_car",userCarService.findUserCar(phone));
            model.addAttribute("car_shuliang",car_shuliang);
            model.addAttribute("car_list",carBillService.userAllCarInfo(phone));
            model.addAttribute("car_fenye_list",carBillsFenYe);
            model.addAttribute("current_page",pageLimit);
            System.out.println("登陆成功");
            return "main";
        }else {
            System.out.println("登陆失败");
            model.addAttribute("login_error","error");
            return "login";
        }

    }
}
