package com.parking.controller;

import com.parking.service.CarBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
public class CarBillController {
    @Autowired
    private CarBillService carBillService;
    @RequestMapping("car_enter")
    public void car_enter(HttpServletRequest request){
        System.out.println("车辆进入中");
        String currentTime="";
        String car_number=request.getParameter("car_number");
        currentTime=carBillService.getCurrentTime();
        System.out.println("car_number:"+car_number+"currentTime:"+currentTime);
        carBillService.carEnter(car_number,currentTime);
        System.out.println("车辆进入了");
    }
    @RequestMapping("car_leave")
    public void car_leave(HttpServletRequest request) throws ParseException {
        System.out.println("车辆离场");
        String currentTime="";
        String car_number=request.getParameter("car_number");
        currentTime=carBillService.getCurrentTime();
        carBillService.carLeave(currentTime,car_number);
        System.out.println(car_number+"已出场");
    }
//    @RequestMapping("car_update_time")
////    public void car_update_time(HttpServletRequest request) throws ParseException {
////        String car_number=request.getParameter("car_number");
////        carBillService.carNowShouldPayMoney(car_number);
////    }
}
