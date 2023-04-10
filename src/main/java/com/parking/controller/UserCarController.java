package com.parking.controller;

import com.parking.entity.NumberPrefixInfo;
import com.parking.entity.UserCar;
import com.parking.service.NumberPrefixService;
import com.parking.service.UserCarService;
import com.parking.service.userInfoService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserCarController {
    @Autowired
    private NumberPrefixService numberPrefixService;
    @Autowired
    private userInfoService userInfoService;
    @Autowired
    private UserCarService userCarService;
    @RequestMapping("enter_add_car")
    public String enterAddCar(HttpServletRequest request, Model model){
        String phone =request.getParameter("phone");
        String password =request.getParameter("password");
        String level = request.getParameter("level");
        System.out.println(phone);
        List<NumberPrefixInfo> numberPrefixInfoList = numberPrefixService.getPrefix();
        model.addAttribute("phone",phone);
        model.addAttribute("password",password);
        model.addAttribute("level",level);
        model.addAttribute("numberPrefixInfoList",numberPrefixInfoList);
        return "addCar";
    }
    @RequestMapping("addCar")
    public String addCar(HttpServletRequest request,Model model){
        int car_shuliang;

        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        int level = Integer.parseInt(request.getParameter("level"));
        String city=request.getParameter("city");
        String area=request.getParameter("area");
        String point="·";
        String number=request.getParameter("number");
        //将字符串整合成正确的车牌号
        System.out.println(city+area+point+number);
        String car_number=city+area+point+number;
        System.out.println(phone+password);
//        List<UserCar> userCars=userCarService.findUserCar(phone);
        //判断该用户还有多少车牌可以添加
//        if (level==1){
//            car_shuliang=2-userCars.size();
//        }else{
//            car_shuliang=4-userCars.size();
//        }
//        model.addAttribute("car_shuliang",car_shuliang);
        userCarService.addCar(phone,car_number);
        return "redirect:user_check_login"+"?phone="+phone+"&"+"password="+password;
//        return "main";
    }
}
