package com.parking.controller_H5;

import com.parking.service.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserCarController_H5 {
    @Autowired
    private UserCarService userCarService;
    @RequestMapping("add_car_H5")
    public void AddCarH5(HttpServletRequest request){
        String phone =request.getParameter("phone");
        String city =request.getParameter("city");
        String area =request.getParameter("area");
        String number =request.getParameter("number");
        String point="·";
        System.out.println(city+area+point+number);
        String car_number=city+area+point+number;

        userCarService.addCar(phone,car_number);
    }
}
